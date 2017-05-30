package services.community.custom.CommunityServices.handler;

import studio.lineage2.gameserver.Config;
import studio.lineage2.gameserver.listener.script.OnInitScriptListener;
import studio.lineage2.gameserver.model.Player;

/**
 * Created by Averen on 23.03.2017.
 */
public abstract class CommunityServicesHandler implements OnInitScriptListener
{
    public void onInit()
    {
        if(Config.COMMUNITYBOARD_ENABLED)
        {
            CommunityServicesHolder.getInstance().registerHandler(this);
        }
    }

    public abstract String[] getServicesCommands();

    public abstract void onServiceCommand(String bypass, Player player);

}
