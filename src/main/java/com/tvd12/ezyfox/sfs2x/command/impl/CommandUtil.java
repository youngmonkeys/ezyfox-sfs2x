package com.tvd12.ezyfox.sfs2x.command.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.smartfoxserver.v2.api.ISFSApi;
import com.smartfoxserver.v2.entities.Room;
import com.smartfoxserver.v2.entities.User;
import com.smartfoxserver.v2.extensions.ISFSExtension;
import com.tvd12.ezyfox.core.config.APIKey;
import com.tvd12.ezyfox.core.model.ApiBaseUser;
import com.tvd12.ezyfox.core.model.ApiRoom;

/**
 * Support to get smartfox user or room by name or agent object
 * 
 * @author tavandung12
 * Created on May 31, 2016
 *
 */

public class CommandUtil {

    // prevent new instance
	private CommandUtil() {}
	
	/**
	 * Get smartfox user by agent object
	 * 
	 * @param agent user agent object
	 * @param api smartfox api
	 * @return smartfox user
	 */
	public static User getSfsUser(ApiBaseUser agent, ISFSApi api) {
	    if(agent == null)
	        return null;
		return getSfsUser(agent.getName(), api);
	}
	
	/**
	 * Get smartfox room by agent object
	 * 
	 * @param agent room agent object
	 * @param extension smartfox extension
	 * @return smartfox room object
	 */
	public static Room getSfsRoom(ApiRoom agent, ISFSExtension extension) {
	    return getSfsRoom(agent.getName(), extension);
    }
	
	/**
	 * Get smartfox user by name
	 * 
	 * @param username user's name
	 * @param api smartfox api
	 * @return smartfox user object
	 */
	public static User getSfsUser(String username, ISFSApi api) {
        return api.getUserByName(username);
    }
    
	/**
	 * Get smartfox room by name
	 * 
	 * @param roomName room's name
	 * @param extension smartfox extension
	 * @return smartfox room object
	 */
    public static Room getSfsRoom(String roomName, ISFSExtension extension) {
        return extension
                .getParentZone()
                .getRoomByName(roomName);
    }
    
    /**
     * Get room agent list from smartfox room list
     * 
     * @param sfsRooms smartfox room list
     * @return list of room agents
     */
    public static List<ApiRoom> getApiRoomList(List<Room> sfsRooms) {
        List<ApiRoom> answer = new ArrayList<>();
        for(Room room : sfsRooms)
            answer.add((ApiRoom) room.getProperty(APIKey.ROOM));
        return answer;
    }
    
    /**
     * Get user agent list from smartfox user list
     * 
     * @param users smartfox room list
     * @return list of user agents
     */
    @SuppressWarnings("unchecked")
    public static <T extends ApiBaseUser> List<T> getApiUserList(Collection<User> users) {
        List<T> answer = new ArrayList<>();
        for(User user : users) 
            if(user.containsProperty(APIKey.USER))
                answer.add((T) user.getProperty(APIKey.USER));
        return answer;
    }
	
}
