package handler.bbs.custom;

import org.slf4j.LoggerFactory;

import handler.bbs.ScriptsCommunityHandler;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import l2s.gameserver.Config;
import l2s.gameserver.data.htm.HtmCache;
import l2s.gameserver.handler.bbs.custom.mGeneratePage;
import l2s.gameserver.model.Player;
import l2s.gameserver.network.l2.s2c.ShowBoardPacket;

public class CommunityNews extends ScriptsCommunityHandler
{
	private static final org.slf4j.Logger _log = LoggerFactory.getLogger(CommunityNews.class);

	private long cache;

	Player player;

	private List<NewsData> news_data = new ArrayList<>();

	@Override
	public String[] getBypassCommands()
	{
		return new String[]
		{ "_bbs_news", };
	}

	@Override
  public void onBypassCommand(Player player, String bypass) {
      StringTokenizer st = new StringTokenizer(bypass, "_");
      st.nextToken();
      st.nextToken();
      if(!st.hasMoreTokens()) {
          player.unsetVar("CommPageArhive");
          ShowBoardPacket.separateAndSend(mGeneratePage.addToTemplateNews(getMain(), getTitle("main")), player);
      } else {
          switch(st.nextToken()) {
              case "arhive":
                  String page = st.nextToken();
                  player.setVar("CommPageArhive", page, -1);
                  ShowBoardPacket.separateAndSend(mGeneratePage.addToTemplateNews(getArhive(page), getTitle("arhive")), player);
                  break;
              case "show":
              	ShowBoardPacket.separateAndSend(mGeneratePage.addToTemplateNews(showNews(st.nextToken()), getTitle("show")), player);
                  break;
              case "return":
                  if(player.getVarInt("CommPageArhive") == 0)
                  	ShowBoardPacket.separateAndSend(mGeneratePage.addToTemplateNews(getMain(), getTitle("main")), player);
                  else
                  	ShowBoardPacket.separateAndSend(mGeneratePage.addToTemplateNews(getArhive(player.getVarInt("CommPageArhive")), getTitle("arhive")), player);
                  break;
          }
      }
  }

	@Override
	protected void doWriteCommand(Player player, String bypass, String arg1, String arg2, String arg3, String arg4, String arg5)
	{
		//
	}

	public String getTitle(String action)
	{
		String title = "";
		switch(action)
		{
			case "arhive":
				title = "Старые новости";
				break;
			case "show":
				title = "Просмотр новости";
				break;
			case "main":
				title = "Просмотр новости";
				break;
		}
		return Config.COMMUNITY_TITLE + " :: " + title;
	}

	public String getMain()
	{
		cache();

		String html = HtmCache.getInstance().getHtml("scripts/handler/bbs/pages/news/main.htm", player);
		NewsData nd = news_data.get(0);

		if(nd == null)
		{
			html = html.replace("{title}", "Новостей пока нет");
			html = html.replace("{description}", "На текущий момент у нашего проекта нет новостей");
			html = html.replace("{button}", "");
		}
		else
		{
			html = html.replace("{title}", nd.title);
			html = html.replace("{description}", nd.description.length() > (250 - 3) ? nd.description.substring(250) : (nd.description + " ..."));
			html = html.replace("{button}", "<button value=\"Читать далее\" action=\"bypass _bbs_news_show_" + 0 + "\" width=90 height=25 back=\"L2UI_CT1.Button_DF_Down\" fore=\"L2UI_CT1.Button_DF\"/>");
		}

		return html;
	}

	public String getArhive(String p)
	{
		return getArhive(Integer.parseInt(p));
	}

	public String getArhive(int page)
	{
		cache();

		StringBuilder sb = new StringBuilder();
		int limit = Config.COMMUNITY_NEWS_LIMIT;

		String html = HtmCache.getInstance().getHtml("scripts/handler/bbs/pages/news/item.htm", player);
		int i = 1;
		int begin = page * limit - limit;
		int end = page * limit;

		for(NewsData nd : news_data)
		{
			if(i <= begin || i >= end)
			{
				i++;
				continue;
			}

			String description = (nd.description.length() > (250 - 3) ? nd.description.substring(0, 250) + "..." : nd.description);
			String button = "<button value=\"Читать далее\" action=\"bypass _bbs_news_show_" + (i - 1) + "\" width=90 height=25 back=\"L2UI_CT1.Button_DF_Down\" fore=\"L2UI_CT1.Button_DF\"/>";

			sb.append(html.replace("{title}", nd.title).replace("{description}", description).replace("{button}", button));
			i++;
		}

		int num = (int) ((Math.ceil(news_data.size() / limit) < ((double) news_data.size() / limit)) ? (Math.ceil(news_data.size() / limit) + 1) : Math.ceil(news_data.size() / limit));

		String pages_item = HtmCache.getInstance().getHtml("scripts/handler/bbs/pages/news/pages_item.htm", player);
		StringBuilder pages_sb = new StringBuilder();
		for(int j = 1; j <= num; j++)
		{
			if(page == j)
			{
				pages_sb.append(pages_item.replace("{url}", "<button value=\"" + j + "\" action=\"bypass _bbs_news_arhive_" + j + "\" width=27 height=25 back=\"L2UI_CT1.Button_DF\" fore=\"L2UI_CT1.Button_DF_Down\"/>"));
			}
			else
			{
				pages_sb.append(pages_item.replace("{url}", "<button value=\"" + j + "\" action=\"bypass _bbs_news_arhive_" + j + "\" width=27 height=25 back=\"L2UI_CT1.Button_DF_Down\" fore=\"L2UI_CT1.Button_DF\"/>"));
			}
		}
		return HtmCache.getInstance().getHtml("scripts/handler/bbs/pages/news/arhive.htm", player).replace("{items}", sb.toString()).replace("{pages}", HtmCache.getInstance().getHtml("scripts/handler/bbs/pages/news/pages.htm", player).replace("{items}", pages_sb.toString()));
	}

	public String showNews(String id)
	{
		cache();

		int news_id = Integer.parseInt(id);

		String html = HtmCache.getInstance().getHtml("scripts/handler/bbs/pages/news/show.htm", player);
		NewsData nd = news_data.get(news_id);

		if(nd == null)
		{
			html = html.replace("{title}", "Новостей пока нет");
			html = html.replace("{description}", "На текущий момент у нашего проекта нет новостей");
		}
		else
		{
			html = html.replace("{title}", nd.title);
			html = html.replace("{description}", nd.description);
		}

		return html;
	}

	private void cache()
	{
		if(cache <= System.currentTimeMillis())
		{
			load();
			cache = System.currentTimeMillis() + (Config.COMMUNITY_NEWS_CACHE * 1000);
		}
	}

	private void load()
	{
		news_data.clear();
		try
		{
			DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			URL rssURL = new URL(Config.COMMUNITY_NEWS_URL);
			Document doc = builder.parse(rssURL.openStream());

			NodeList items = doc.getElementsByTagName("item");

			for(int i = 0; i < items.getLength(); i++)
			{
				Element item = (Element) items.item(i);
				String title = getValue(item, "title");
				String description = Config.COMMUNITY_NEWS_ENGINE.equalsIgnoreCase("XenForo") ? getValue(item, "content:encoded") : getValue(item, "description");
				news_data.add(new NewsData(title, stripTags(description)));
			}
		}
		catch(Exception ex)
		{
			_log.error("CommunityCustom->News->Error: ", ex);
		}
	}

	public String stripTags(String str)
	{
		str = str.replace("<p>&nbsp;</p>", "");
		str = str.replace("<p>", "");
		str = str.replace("<br>", "%br1%");
		str = str.replace("</p>", "%br%");

		str = str.replaceAll("<(.)+?>", "");
		str = str.replaceAll("<(\n)+?>", "");

		str = str.replace("%br1%", "<br1>");
		str = str.replace("%br%", "<br>");
		return str;
	}

	public String getValue(Element parent, String nodeName)
	{
		try
		{
			return parent.getElementsByTagName(nodeName).item(0).getFirstChild().getNodeValue();
		}
		catch(Exception e)
		{
			return "";
		}
	}

	class NewsData
	{

		public String title;
		public String description;

		public NewsData(String title, String description)
		{
			this.title = title;
			this.description = description;
		}
	}

	@Override
	public void onWriteCommand(Player player, String bypass, String arg1, String arg2, String arg3, String arg4, String arg5)
	{}

}
