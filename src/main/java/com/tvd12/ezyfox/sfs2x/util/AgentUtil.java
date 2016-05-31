package com.tvd12.ezyfox.sfs2x.util;

import com.smartfoxserver.v2.entities.Room;
import com.smartfoxserver.v2.entities.User;
import com.tvd12.ezyfox.core.config.APIKey;
import com.tvd12.ezyfox.core.model.ApiRoom;
import com.tvd12.ezyfox.core.model.ApiUser;

public class AgentUtil {

    private AgentUtil() {}
    
    public static ApiUser getUserAgent(User sfsUser) {
        Object userAgent = sfsUser.getProperty(APIKey.USER);
        if(userAgent == null) 
            throw new RuntimeException("Can not get user agent");
        return (ApiUser)userAgent;
    }
    
    public static ApiRoom getRoomAgent(Room sfsRoom) {
        Object roomAgent = sfsRoom.getProperty(APIKey.ROOM);
        if(roomAgent == null) 
            throw new RuntimeException("Can not get user agent");
        return (ApiRoom)roomAgent;
    }
    
}
