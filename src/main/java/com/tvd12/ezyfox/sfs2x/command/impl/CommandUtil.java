package com.tvd12.ezyfox.sfs2x.command.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.smartfoxserver.v2.api.ISFSApi;
import com.smartfoxserver.v2.entities.Room;
import com.smartfoxserver.v2.entities.User;
import com.smartfoxserver.v2.extensions.ISFSExtension;
import com.tvd12.ezyfox.core.config.APIKey;
import com.tvd12.ezyfox.core.entities.ApiBaseUser;
import com.tvd12.ezyfox.core.entities.ApiGameUser;
import com.tvd12.ezyfox.core.entities.ApiRoom;
import com.tvd12.ezyfox.core.entities.ApiUser;

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
    public static <T extends ApiUser> List<T> getApiUserList(Collection<User> users) {
        List<T> answer = new ArrayList<>();
        for(User user : users) 
            if(user.containsProperty(APIKey.USER))
                answer.add((T) user.getProperty(APIKey.USER));
        return answer;
    }
    
    /**
     * Get user agent list from smartfox user list
     * 
     * @param clazz class of game user
     * @param users smartfox room list
     * @return list of user agents
     */
    @SuppressWarnings("unchecked")
    public static <T extends ApiGameUser> List<T> getApiGameUserList(Collection<User> users, Class<?> clazz) {
        List<T> answer = new ArrayList<>();
        for(User user : users) {
            if(!user.containsProperty(APIKey.USER)) continue;
            ApiUser apiUser = (ApiUser)user.getProperty(APIKey.USER);
            answer.add((T) apiUser.getChild(clazz));
        }
        return answer;
    }
    
    /**
     * Get list of smartfox users from collection of user agents
     * 
     * @param users the collection of user agents
     * @param api the api
     * @return list of smartfox users
     */
    public static <T extends ApiBaseUser> List<User> getSFSUserList(Collection<T> users, ISFSApi api) {
        List<User> answer = new ArrayList<>();
        for(T user : users) answer.add(getSfsUser(user, api));
        return answer;
    }
    
    /**
     * Get list of smartfox users from collection of user names
     * 
     * @param usernames the collection of user agents
     * @param api the api
     * @return list of smartfox users
     */
    public static List<User> getSFSUserListByNames(Collection<String> usernames, ISFSApi api) {
        List<User> answer = new ArrayList<>();
        for(String username : usernames) answer.add(getSfsUser(username, api));
        return answer;
    }
	
}
