/**
 * 
 */
package com.tvd12.ezyfox.sfs2x.command.impl;

import java.util.ArrayList;
import java.util.List;

import com.smartfoxserver.v2.api.ISFSApi;
import com.smartfoxserver.v2.entities.Room;
import com.smartfoxserver.v2.entities.User;
import com.smartfoxserver.v2.extensions.ISFSExtension;
import com.tvd12.ezyfox.core.command.FindRoom;
import com.tvd12.ezyfox.core.config.APIKey;
import com.tvd12.ezyfox.core.model.ApiBaseUser;
import com.tvd12.ezyfox.sfs2x.content.impl.AppContextImpl;

/**
 * @author tavandung12
 *
 */
public class FindRoomImpl extends BaseCommandImpl implements FindRoom {

    public FindRoomImpl(AppContextImpl context, ISFSApi api, ISFSExtension extension) {
        super(context, api, extension);
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.GetRoom#by(java.lang.String)
     */
    @SuppressWarnings("unchecked")
    @Override
    public <T> T by(String name) {
        return (T) CommandUtil.getSfsRoom(name, extension);
    }
    
    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.GetRoom#by(int)
     */
    @SuppressWarnings("unchecked")
    @Override
    public <T> T by(int id) {
        Room sfsRoom = extension.getParentZone().getRoomById(id);
        if(sfsRoom == null)
            return null;
        return (T) sfsRoom.getProperty(APIKey.ROOM);
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.GetRoom#by(com.tvd12.ezyfox.core.model.ApiBaseUser)
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public List by(ApiBaseUser user) {
        User sfsUser = CommandUtil.getSfsUser(user, api);
        if(sfsUser == null) return null;
        List answer = new ArrayList();
        for(Room room : sfsUser.getJoinedRooms()) 
            answer.add(room.getProperty(APIKey.ROOM));
        return answer;
    }

}
