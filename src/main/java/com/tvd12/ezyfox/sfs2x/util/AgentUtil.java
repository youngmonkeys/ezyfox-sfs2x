package com.tvd12.ezyfox.sfs2x.util;

import com.smartfoxserver.v2.entities.Room;
import com.smartfoxserver.v2.entities.User;
import com.tvd12.ezyfox.core.config.APIKey;
import com.tvd12.ezyfox.core.model.ApiRoom;
import com.tvd12.ezyfox.core.model.ApiUser;

/**
 * Support to get user agent and room agent object from smartfox user and room object
 * 
 * @author tavandung12
 * Created on Jun 1, 2016
 *
 */
public class AgentUtil {

    // prevent new instance
    private AgentUtil() {}
    
    /**
     * Get user agent object mapped to smartfox user object
     * 
     * @param sfsUser smartfox user object
     * @return user agent object
     */
    public static ApiUser getUserAgent(User sfsUser) {
        Object userAgent = sfsUser.getProperty(APIKey.USER);
        if(userAgent == null) 
            throw new RuntimeException("Can not get user agent");
        return (ApiUser)userAgent;
    }
    
    /**
     * Get room agent object mapped to smartfox room object
     * 
     * @param sfsRoom smartfox room object
     * @return room agent object
     */
    public static ApiRoom getRoomAgent(Room sfsRoom) {
        Object roomAgent = sfsRoom.getProperty(APIKey.ROOM);
        if(roomAgent == null) 
            throw new RuntimeException("Can not get user agent");
        return (ApiRoom)roomAgent;
    }
    
}
