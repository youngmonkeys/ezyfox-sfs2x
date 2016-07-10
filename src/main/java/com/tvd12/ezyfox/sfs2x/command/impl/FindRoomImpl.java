/**
 * 
 */
package com.tvd12.ezyfox.sfs2x.command.impl;

import com.smartfoxserver.v2.api.ISFSApi;
import com.smartfoxserver.v2.entities.Room;
import com.smartfoxserver.v2.entities.User;
import com.smartfoxserver.v2.extensions.ISFSExtension;
import com.tvd12.ezyfox.core.command.FindRoom;
import com.tvd12.ezyfox.core.constants.APIKey;
import com.tvd12.ezyfox.core.entities.ApiBaseUser;
import com.tvd12.ezyfox.sfs2x.content.impl.AppContextImpl;

/**
 * @see FindRoom
 * 
 * @author tavandung12
 *
 */
public class FindRoomImpl extends BaseCommandImpl implements FindRoom {

    /**
     * @param context
     * @param api
     * @param extension
     */
    public FindRoomImpl(AppContextImpl context, ISFSApi api, ISFSExtension extension) {
        super(context, api, extension);
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.GetRoom#by(java.lang.String)
     */
    @SuppressWarnings("unchecked")
    @Override
    public <T> T by(String name) {
        Room room = CommandUtil.getSfsRoom(name, extension);
        if(room == null) return null; 
        return (T)room.getProperty(APIKey.ROOM);
    }
    
    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.GetRoom#by(int)
     */
    @SuppressWarnings("unchecked")
    @Override
    public <T> T by(int id) {
        Room sfsRoom = extension.getParentZone().getRoomById(id);
        if(sfsRoom == null) return null;
        return (T) sfsRoom.getProperty(APIKey.ROOM);
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.GetRoom#by(com.tvd12.ezyfox.core.entities.ApiBaseUser)
     */
    @SuppressWarnings({ "unchecked"})
    @Override
    public <T> T by(ApiBaseUser user) {
        User sfsUser = CommandUtil.getSfsUser(user, api);
        if(sfsUser == null) return null;
        Room sfsRoom = sfsUser.getLastJoinedRoom();
        if(sfsRoom == null) return null;
        return (T)sfsRoom.getProperty(APIKey.ROOM);
    }

}
