package com.tvd12.ezyfox.sfs2x.command.impl;

import com.smartfoxserver.v2.api.ISFSApi;
import com.smartfoxserver.v2.exceptions.SFSRoomException;
import com.smartfoxserver.v2.extensions.ISFSExtension;
import com.tvd12.ezyfox.core.command.ChangeRoomCapacity;
import com.tvd12.ezyfox.core.entities.ApiBaseUser;
import com.tvd12.ezyfox.core.entities.ApiRoom;
import com.tvd12.ezyfox.sfs2x.content.impl.AppContextImpl;

/**
 * @see ChangeRoomCapacity
 * 
 * @author tavandung12
 * Created on May 26, 2016
 *
 */
public class ChangeRoomCapacityImpl extends BaseCommandImpl implements ChangeRoomCapacity {

    private String owner;
    private String targetRoom;
    private int maxUsers;
    private int maxSpectators;
    
    /**
     * @param context
     * @param api
     * @param extension
     */
    public ChangeRoomCapacityImpl(AppContextImpl context, ISFSApi api, ISFSExtension extension) {
        super(context, api, extension);
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.BaseCommand#execute()
     */
    @SuppressWarnings("unchecked")
    @Override
    public Boolean execute() {
        try {
            api.changeRoomCapacity(CommandUtil.getSfsUser(owner, api),
                    CommandUtil.getSfsRoom(targetRoom, extension), 
                    maxUsers, maxSpectators);
        } catch (SFSRoomException e) {
            throw new IllegalStateException(e);
        }
        return Boolean.TRUE;
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.ChangeRoomCapacity#owner(com.tvd12.ezyfox.core.entities.ApiBaseUser)
     */
    @Override
    public ChangeRoomCapacity owner(ApiBaseUser owner) {
        this.owner = owner.getName();
        return this;
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.ChangeRoomCapacity#room(com.tvd12.ezyfox.core.entities.ApiRoom)
     */
    @Override
    public ChangeRoomCapacity room(ApiRoom targetRoom) {
        this.targetRoom = targetRoom.getName();
        return this;
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.ChangeRoomCapacity#maxUsers(int)
     */
    @Override
    public ChangeRoomCapacity maxUsers(int newMaxUsers) {
        this.maxUsers = newMaxUsers;
        return this;
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.ChangeRoomCapacity#maxSpectators(int)
     */
    @Override
    public ChangeRoomCapacity maxSpectators(int newMaxSpactators) {
        this.maxSpectators = newMaxSpactators;
        return this;
    }

}
