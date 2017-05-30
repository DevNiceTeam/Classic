package services.community.custom.CommunityServices.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Averen on 23.03.2017.
 */
public class CommunityServicesHolder
{
    private static final Logger _log = LoggerFactory.getLogger(CommunityServicesHolder.class);

    private static final CommunityServicesHolder _instance = new CommunityServicesHolder();

    private final Map<String, CommunityServicesHandler> _handlers;

    private CommunityServicesHolder()
    {
        _handlers = new HashMap<>();
    }

    public static CommunityServicesHolder getInstance()
    {
        return _instance;
    }

    public void registerHandler(CommunityServicesHandler communityServicesHandler)
    {
        for(String bypass : communityServicesHandler.getServicesCommands())
        {
            if(_handlers.containsKey(bypass))
            {
                _log.warn("Duplicate community services handler!");
            }
            _handlers.put(bypass, communityServicesHandler);
        }
    }

    public CommunityServicesHandler getHandler(String bypass)
    {
        for(Map.Entry<String, CommunityServicesHandler> entry : _handlers.entrySet())
        {
            if(entry.getKey().equals(bypass.split(" ")[0]))
            {
                return entry.getValue();
            }
        }
        return null;
    }

}
