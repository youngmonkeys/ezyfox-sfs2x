package com.tvd12.ezyfox.sfs2x.command.impl;

import com.smartfoxserver.v2.api.ISFSApi;
import com.smartfoxserver.v2.entities.Room;
import com.smartfoxserver.v2.entities.User;
import com.smartfoxserver.v2.extensions.ISFSExtension;
import com.tvd12.ezyfox.core.model.ApiBaseUser;
import com.tvd12.ezyfox.core.model.ApiRoom;

public class CommandUtil {

	private CommandUtil() {}
	
	public static User getSfsUser(ApiBaseUser agent, ISFSApi api) {
	    if(agent == null)
	        return null;
		return getSfsUser(agent.getName(), api);
	}
	
	public static Room getSfsRoom(ApiRoom agent, ISFSExtension extension) {
	    return getSfsRoom(agent.getName(), extension);
    }
	
	public static User getSfsUser(String username, ISFSApi api) {
        return api.getUserByName(username);
    }
    
    public static Room getSfsRoom(String roomName, ISFSExtension extension) {
        return extension
                .getParentZone()
                .getRoomByName(roomName);
    }
	
}
