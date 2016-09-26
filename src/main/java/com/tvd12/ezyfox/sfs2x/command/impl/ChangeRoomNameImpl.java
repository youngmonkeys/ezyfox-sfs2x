package com.tvd12.ezyfox.sfs2x.command.impl;

import com.smartfoxserver.v2.api.ISFSApi;
import com.smartfoxserver.v2.exceptions.SFSRoomException;
import com.smartfoxserver.v2.extensions.ISFSExtension;
import com.tvd12.ezyfox.core.command.ChangeRoomName;
import com.tvd12.ezyfox.core.entities.ApiBaseUser;
import com.tvd12.ezyfox.core.entities.ApiRoom;
import com.tvd12.ezyfox.sfs2x.content.impl.AppContextImpl;

/**
 * @see ChangeRoomName
 * 
 * @author tavandung12
 * Created on May 27, 2016
 *
 */
public class ChangeRoomNameImpl extends BaseCommandImpl implements ChangeRoomName {

    private String owner;
    private String targetRoom;
    private String roomName;
    
    /**
     * @param context the context
     * @param api the api
     * @param extension the extension
     */
    public ChangeRoomNameImpl(AppContextImpl context, ISFSApi api, ISFSExtension extension) {
        super(context, api, extension);
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.BaseCommand#execute()
     */
    @SuppressWarnings("unchecked")
    @Override
    public Boolean execute() {
        try {
            api.changeRoomName(CommandUtil.getSfsUser(owner, api), 
                    CommandUtil.getSfsRoom(targetRoom, extension), 
                    roomName);
        } catch (SFSRoomException e) {
            throw new IllegalStateException(e);
        }
        return Boolean.TRUE;
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.ChangeRoomName#owner(com.tvd12.ezyfox.core.entities.ApiBaseUser)
     */
    @Override
    public ChangeRoomName owner(ApiBaseUser owner) {
        this.owner = owner.getName();
        return this;
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.ChangeRoomName#room(com.tvd12.ezyfox.core.entities.ApiRoom)
     */
    @Override
    public ChangeRoomName room(ApiRoom targetRoom) {
        this.targetRoom = targetRoom.getName();
        return this;
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.ChangeRoomName#name(java.lang.String)
     */
    @Override
    public ChangeRoomName name(String newName) {
        this.roomName = newName;
        return this;
    }

}
