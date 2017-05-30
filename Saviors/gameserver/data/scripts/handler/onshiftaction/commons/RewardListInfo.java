package handler.onshiftaction.commons;

import studio.lineage2.gameserver.Config;
import studio.lineage2.gameserver.data.Champion;
import studio.lineage2.gameserver.data.RewardGroup;
import studio.lineage2.gameserver.data.RewardItem;
import studio.lineage2.gameserver.data.customdropspoilnpctype.CustomDropSpoilNpcType;
import studio.lineage2.gameserver.data.customdropspoilnpctype.CustomDropSpoilNpcTypeHolder;
import studio.lineage2.gameserver.data.htm.HtmCache;
import studio.lineage2.gameserver.data.xml.holder.ChampionHolder;
import studio.lineage2.gameserver.data.xml.holder.ItemHolder;
import studio.lineage2.gameserver.data.xml.holder.NpcHolder;
import studio.lineage2.gameserver.data.xml.holder.RewardHolder;
import studio.lineage2.gameserver.model.CustomWorldRates;
import studio.lineage2.gameserver.model.Player;
import studio.lineage2.gameserver.model.base.Experience;
import studio.lineage2.gameserver.model.instances.NpcInstance;
import studio.lineage2.gameserver.network.l2.components.HtmlMessage;
import studio.lineage2.gameserver.stats.Stats;
import studio.lineage2.gameserver.templates.npc.NpcTemplate;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Modified by Averen on 16.03.2017.
 */
public class RewardListInfo
{
    public static void SendRewardInfo(Player player, NpcInstance npc, String[] params)
    {
        if(npc != null)
        {
            genPages(player, npc);
            send(player, params);
        }
    }

    private static void send(Player player, String[] params)
    {
        HtmlMessage msg = new HtmlMessage(0);

        if(params.length > 0)
        {
            player.setCurrentPage(Integer.parseInt(params[0]));
            msg.setHtml(player.getPages().get(Integer.parseInt(params[0])));
        }
        else
        {
            msg.setHtml(player.getPages().get(0));
        }

        if(player.getPages().size() > 1)
        {
            StringBuilder sb = new StringBuilder();
            sb.append("<table><tr>");
            sb.append("<td width=75>Страницы: </td>");
            for(int i = 0; i < player.getPages().size(); i++)
            {
                sb.append("<td>");

                if(player.getCurrentPage() == i)
                {
                    sb.append("<button action=\"bypass -h htmbypass_actions.OnActionShift:droplist ").append(i).append("\" value=\"").append(i + 1).append("\" width=32 height=32 back=\"L2UI_CT1.Button_DF_emphasize_Down\" fore=\"L2UI_CT1.Button_DF_emphasize\"/>");
                }
                else
                {
                    sb.append("<button action=\"bypass -h htmbypass_actions.OnActionShift:droplist ").append(i).append("\" value=\"").append(i + 1).append("\" width=32 height=32 back=\"L2UI_ct1.button_df\" fore=\"L2UI_ct1.button_df\"/>");
                }

                sb.append("</td>");
            }
            sb.append("</tr></table>");

            StringBuilder sb2 = new StringBuilder();
            sb2.append("<table><tr>");
            sb2.append("<td><button action=\"bypass -hactions.OnActionShift:droplist $name true").append("\" value=\"Выбрать страницу (1 .. ").append(player.getPages().size()).append(")\" width=150 height=21 back=\"L2UI_CT1.Button_DF_emphasize_Down\" fore=\"L2UI_CT1.Button_DF_emphasize\"/></td>");
            sb2.append("<td>").append("<edit var=\"name\" type=\"number\" width=75 length=20>").append("</td>");
            sb2.append("</tr></table>");

            msg = msg.replace("%pages%", sb.toString());
            msg = msg.replace("%pages2%", sb2.toString());
        }
        else
        {
            msg = msg.replace("%pages%", "");
            msg = msg.replace("%pages2%", "");
        }

        player.sendPacket(msg);
    }

    private static String genText(String name)
    {
        StringBuilder sb = new StringBuilder();
        sb.append("<table width=280 height=30>");
        sb.append("<tr>");
        sb.append("<td fixwidth=280 align=center>");
        sb.append("<font color=\"00BFFF\">");
        sb.append(name);
        sb.append("</font>");
        sb.append("<img src=\"L2UI.SquareWhite\" width=280 height=1>");
        sb.append("</td>");
        sb.append("</tr>");
        sb.append("</table>");
        return sb.toString();
    }

    private static String genItem(Player player, NpcInstance npc, RewardItem rewardItem, boolean isSpoil, boolean rate)
    {
        double penaltyMod = Experience.penaltyModifierDrop(npc, player);

        Champion champion = null;
        if(npc.getChampionId() > 0)
        {
            champion = ChampionHolder.getInstance().get(npc.getChampionId());
        }

        NpcTemplate template = NpcHolder.getInstance().getTemplate(npc.getNpcId());

        double countRate = 1;
        double chanceRate = 1;

        if(isSpoil)
        {
            countRate *= Config.RATE_COUNT_SPOIL * CustomWorldRates.getCountSpoil();
            countRate *= player.getPremiumAccount().getRates().getSpoilCount();

            chanceRate *= Config.RATE_CHANCE_SPOIL * CustomWorldRates.getChanceSpoil();
            chanceRate *= player.calcStat(Stats.SPOIL_RATE_MULTIPLIER);
            chanceRate *= player.getPremiumAccount().getRates().getSpoilChance();

            if(champion != null)
            {
                countRate *= champion.getSpoilRateCount();
                chanceRate *= champion.getSpoilRateChance();
            }
        }
        else if(template.isBoss())
        {
            countRate *= Config.RATE_COUNT_DROP_GRAND_RAID * CustomWorldRates.getCountDropGrandRaid();
            countRate *= player.getPremiumAccount().getRates().getGrandRaidDropCount();

            chanceRate *= Config.RATE_CHANCE_DROP_GRAND_RAID * CustomWorldRates.getChanceDropGrandRaid();
            chanceRate *= player.getPremiumAccount().getRates().getGrandRaidDropChance();

            if(champion != null)
            {
                countRate *= champion.getGrandRaidRateCount();
                chanceRate *= champion.getGrandRaidRateChance();
            }
        }
        else if(template.isRaid())
        {
            countRate *= Config.RATE_COUNT_DROP_RAID * CustomWorldRates.getCountDropRaid();
            countRate *= player.getPremiumAccount().getRates().getRaidDropCount();

            chanceRate *= Config.RATE_CHANCE_DROP_RAID * CustomWorldRates.getChanceDropRaid();
            chanceRate *= player.getPremiumAccount().getRates().getRaidDropChance();

            if(champion != null)
            {
                countRate *= champion.getRaidRateCount();
                chanceRate *= champion.getRaidRateChance();
            }
        }
        else
        {
            if(rewardItem.getId() == 57)
            {
                countRate *= Config.RATE_COUNT_ADENA * CustomWorldRates.getCountAdena();
                countRate *= player.calcStat(Stats.ADENA_RATE_MULTIPLIER);
                countRate *= player.getPremiumAccount().getRates().getAdena();

                if(champion != null)
                {
                    countRate *= champion.getDropAdenaRateCount();
                }
            }
            else if(rewardItem.getId() == 6360 || rewardItem.getId() == 6361 || rewardItem.getId() == 6362)
            {
                countRate *= player.getPremiumAccount().getRates().getStoneCount();

                chanceRate *= player.getPremiumAccount().getRates().getStoneChance();
            }
            else
            {
                countRate *= Config.RATE_COUNT_DROP * CustomWorldRates.getCountDrop();
                countRate *= player.getPremiumAccount().getRates().getDropCount();

                chanceRate *= Config.RATE_CHANCE_DROP * CustomWorldRates.getChanceDrop();
                chanceRate *= player.getPremiumAccount().getRates().getDropChance();

                if(champion != null)
                {
                    countRate *= champion.getDropItemRateCount();
                    chanceRate *= champion.getDropItemRateChance();
                }
            }
        }

        NumberFormat format = NumberFormat.getInstance(new Locale("ru"));

        StringBuilder sb = new StringBuilder();
        sb.append("<table width=240 height=40>");
        sb.append("<tr>");

        sb.append("<td align=left fixwidth=32>");
        sb.append("<img src=").append(ItemHolder.getInstance().getIcon(rewardItem.getId())).append(" width=32 height=32>");
        sb.append("</td>");

        sb.append("<td align=left fixwidth=238>");
        sb.append("<font color=LEVEL>").append(ItemHolder.getInstance().getName(rewardItem.getId())).append("</font>");
        sb.append("</td>");

        sb.append("<td align=left fixwidth=225>");
        sb.append(format.format(rewardItem.getCountMin()));
        sb.append(" - ");
        sb.append(format.format(rewardItem.getCountMax()));

        BigDecimal decimalMin = new BigDecimal(rewardItem.getChanceMin());
        decimalMin = decimalMin.setScale(4, BigDecimal.ROUND_UP);

        BigDecimal decimalMax = new BigDecimal(rewardItem.getChanceMax());
        decimalMax = decimalMax.setScale(4, BigDecimal.ROUND_UP);

        DecimalFormat df = new DecimalFormat("#.#####");

        if(rewardItem.getChanceMin() == rewardItem.getChanceMax())
        {
            sb.append(" (").append(df.format(decimalMin.doubleValue())).append("%) ");
        }
        else
        {
            sb.append(" (").append(df.format(decimalMin.doubleValue())).append(" - ").append(df.format(decimalMax.doubleValue())).append("%) ");
        }


        sb.append("</td>");

        sb.append("<td align=left fixwidth=225>");
        if(rate)
        {
            sb.append("<font color=LEVEL>");
            sb.append(format.format(rewardItem.getCountMin() * (rewardItem.getId() == 57 ? countRate : 1)));
            sb.append(" - ");
            if(rewardItem.getId() < 0)
            {
                sb.append((long) (rewardItem.getCountMax() * countRate));
            }
            else if(ItemHolder.getInstance().getTemplate(rewardItem.getId()).isStackable())
            {
                sb.append(format.format((long) (rewardItem.getCountMax() * countRate)));
            }
            else
            {
                sb.append(1);
            }

            double chance = chanceRate * penaltyMod;

            decimalMin = new BigDecimal(rewardItem.getChanceMin() * chance);
            decimalMin = decimalMin.setScale(4, BigDecimal.ROUND_UP);

            decimalMax = new BigDecimal(rewardItem.getChanceMax() * chance);
            decimalMax = decimalMax.setScale(4, BigDecimal.ROUND_UP);

            df = new DecimalFormat("#.#####");

            if(rewardItem.getChanceMin() == rewardItem.getChanceMax())
            {
                sb.append(" (").append(df.format(decimalMin.doubleValue())).append("%) ");
            }
            else
            {
                sb.append(" (").append(df.format(decimalMin.doubleValue())).append(" - ").append(df.format(decimalMax.doubleValue())).append("%) ");
            }

            sb.append("</font>");
        }
        sb.append("</td>");

        sb.append("</tr>");
        sb.append("</table>");

        return sb.toString();
    }

    private static void genPages(Player player, NpcInstance npc)
    {
        player.getPages().clear();

        List<String> elementsAll = new ArrayList<>();

        if(RewardHolder.getInstance().get(npc.getNpcId()) != null)
        {
            boolean isSpoil;
            int i = 0;
            for(RewardGroup group : RewardHolder.getInstance().get(npc.getNpcId()))
            {
                i++;

                switch(group.getType())
                {
                    case "SWEEP":
                        elementsAll.add(genText("Спойл"));
                        isSpoil = true;
                        break;
                    default:
                        elementsAll.add(genText("Группа " + i));
                        isSpoil = false;
                        break;
                }

                for(RewardItem rewardItem : group.getRewardItems())
                {
                    elementsAll.add(genItem(player, npc, rewardItem, isSpoil, true));
                }
            }
        }

        CustomDropSpoilNpcType type = CustomDropSpoilNpcTypeHolder.getInstance().get(npc.getClass(), npc.getLevel(), npc.getNpcId());
        if(type != null)
        {
            if(!type.getDrop().isEmpty())
            {
                elementsAll.add(genText("Дополнительная награда"));
                for(RewardItem rewardItem : type.getDrop())
                {
                    elementsAll.add(genItem(player, npc, rewardItem, false, false));
                }
            }

            if(!type.getSpoil().isEmpty())
            {
                elementsAll.add(genText("Дополнительная награда (Спойл)"));
                for(RewardItem rewardItem : type.getSpoil())
                {
                    elementsAll.add(genItem(player, npc, rewardItem, true, false));
                }
            }
        }

        String htm = HtmCache.getInstance().getHtml("scripts/actions/player.L2NpcInstance.onActionShift.DropList.htm", player);

        genPagesByElements(player, htm, elementsAll, 11, 1);
    }

    private static void genPagesByElements(Player player, String htm, List<String> elementsAll, int elementsOnPageCount, int columnsOnPage)
    {
        int elementsAllCount = elementsAll.size();

        int pagesCount = (int) Math.ceil(elementsAllCount / elementsOnPageCount) + (elementsAllCount % elementsOnPageCount == 0 && elementsAllCount > 1 ? 0 : 1);

        String[][] pages = new String[pagesCount][elementsOnPageCount];

        int k = 0;

        for(int i = 0; i < pagesCount; i++)
        {
            for(int j = 0; j < elementsOnPageCount; j++)
            {
                if(k < elementsAllCount)
                {
                    pages[i][j] = elementsAll.get(k);
                    k++;
                }
            }
        }

        int columns = columnsOnPage;
        for(String[] elementsOnPage : pages)
        {
            StringBuilder sb = new StringBuilder();
            sb.append("<center><table><tr>");
            for(String element : elementsOnPage)
            {
                if(element != null)
                {
                    sb.append("<td>");
                    sb.append(element);
                    sb.append("</td>");
                }
                columns--;
                if(columns == 0)
                {
                    columns = columnsOnPage;
                    sb.append("</tr>");
                    sb.append("<tr>");
                }
            }
            sb.append("</tr></table></center>");
            String page = sb.toString();
            page = page.replace("<tr></tr>", "");
            player.getPages().add(htm.replace("%page%", page));
        }
    }
}
