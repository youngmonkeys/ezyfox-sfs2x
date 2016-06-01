package com.tvd12.ezyfox.sfs2x.command.impl;

import com.smartfoxserver.v2.api.ISFSApi;
import com.smartfoxserver.v2.exceptions.SFSRoomException;
import com.smartfoxserver.v2.extensions.ISFSExtension;
import com.tvd12.ezyfox.core.command.ChangeRoomPassword;
import com.tvd12.ezyfox.core.model.ApiBaseUser;
import com.tvd12.ezyfox.core.model.ApiRoom;
import com.tvd12.ezyfox.sfs2x.content.impl.AppContextImpl;

/**
 * @see ChangeRoomPassword
 * 
 * @author tavandung12
 * Created on May 27, 2016
 *
 */
public class ChangeRoomPasswordImpl extends BaseCommandImpl implements ChangeRoomPassword {

    private String owner;
    private String targetRoom;
    private String password;
    
    /**
     * @param context
     * @param api
     * @param extension
     */
    public ChangeRoomPasswordImpl(AppContextImpl context, ISFSApi api, ISFSExtension extension) {
        super(context, api, extension);
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.BaseCommand#execute()
     */
    @SuppressWarnings("unchecked")
    @Override
    public Boolean execute() {
        try {
            api.changeRoomPassword(CommandUtil.getSfsUser(owner, api), 
                    CommandUtil.getSfsRoom(targetRoom, extension), 
                    password);
        } catch (SFSRoomException e) {
            throw new IllegalStateException(e);
        }
        return Boolean.TRUE;
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.ChangeRoomPassword#owner(com.tvd12.ezyfox.core.model.ApiBaseUser)
     */
    @SuppressWarnings("unchecked")
    @Override
    public ChangeRoomPassword owner(ApiBaseUser owner) {
        this.owner = owner.getName();
        return this;
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.ChangeRoomPassword#room(com.tvd12.ezyfox.core.model.ApiRoom)
     */
    @SuppressWarnings("unchecked")
    @Override
    public ChangeRoomPassword room(ApiRoom targetRoom) {
        this.targetRoom = targetRoom.getName();
        return this;
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.ChangeRoomPassword#password(com.tvd12.ezyfox.core.model.ApiRoom)
     */
    @SuppressWarnings("unchecked")
    @Override
    public ChangeRoomPassword password(String newPassword) {
        this.password = newPassword;
        return this;
    }

}
