package com.tvd12.ezyfox.sfs2x.command.impl;

import java.util.List;

import com.smartfoxserver.v2.api.ISFSApi;
import com.smartfoxserver.v2.entities.Room;
import com.smartfoxserver.v2.extensions.ISFSExtension;
import com.tvd12.ezyfox.core.command.FetchUserList;
import com.tvd12.ezyfox.core.entities.ApiGameUser;
import com.tvd12.ezyfox.core.entities.ApiRoom;
import com.tvd12.ezyfox.core.entities.ApiUser;
import com.tvd12.ezyfox.sfs2x.content.impl.AppContextImpl;

/**
 * @see FetchUserList
 * 
 * @author tavandung12
 * Created on May 27, 2016
 *
 */
public class FetchUserListImpl extends BaseCommandImpl implements FetchUserList {

    /**
     * @param context the context
     * @param api the api
     * @param extension the extension
     */
    public FetchUserListImpl(AppContextImpl context, ISFSApi api, ISFSExtension extension) {
        super(context, api, extension);
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.FetchUserList#in(com.tvd12.ezyfox.core.entities.ApiRoom)
     */
    @Override
    public <T extends ApiUser> List<T> in(ApiRoom room) {
        Room sfsRoom = CommandUtil.getSfsRoom(room, extension);
        validateRoom(sfsRoom, room);
        return CommandUtil.getApiUserList(sfsRoom.getUserList());
    }
    
    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.FetchUserList#in(com.tvd12.ezyfox.core.entities.ApiRoom, java.lang.Class)
     */
    @Override
    public <T extends ApiGameUser> List<T> in(ApiRoom room, Class<?> clazz) {
        Room sfsRoom = CommandUtil.getSfsRoom(room, extension);
        validateRoom(sfsRoom, room);
        return CommandUtil.getApiGameUserList(sfsRoom.getUserList(), clazz);
    }
    
    private void validateRoom(Room sfsRoom, ApiRoom room) {
        if(sfsRoom == null)
            throw new IllegalStateException("Has no room with name " + room.getName());
    }

}
