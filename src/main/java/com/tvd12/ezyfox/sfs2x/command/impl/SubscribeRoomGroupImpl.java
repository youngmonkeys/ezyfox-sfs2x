package com.tvd12.ezyfox.sfs2x.command.impl;

import com.smartfoxserver.v2.api.ISFSApi;
import com.smartfoxserver.v2.extensions.ISFSExtension;
import com.tvd12.ezyfox.core.command.SubscribeRoomGroup;
import com.tvd12.ezyfox.core.entities.ApiBaseUser;
import com.tvd12.ezyfox.sfs2x.content.impl.AppContextImpl;

/**
 * @see SubscribeRoomGroup
 * 
 * @author tavandung12
 * Created on May 27, 2016
 *
 */
public class SubscribeRoomGroupImpl extends BaseCommandImpl implements SubscribeRoomGroup {

    private String user;
    private String groupId;
    
    /**
     * @param context
     * @param api
     * @param extension
     */
    public SubscribeRoomGroupImpl(AppContextImpl context, ISFSApi api, ISFSExtension extension) {
        super(context, api, extension);
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.BaseCommand#execute()
     */
    @SuppressWarnings("unchecked")
    @Override
    public Boolean execute() {
        api.subscribeRoomGroup(CommandUtil.getSfsUser(user, api), groupId);
        return Boolean.TRUE;
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.SubscribeRoomGroup#user(com.tvd12.ezyfox.core.entities.ApiBaseUser)
     */
    @Override
    public SubscribeRoomGroup user(ApiBaseUser user) {
        this.user = user.getName();
        return this;
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.SubscribeRoomGroup#groupId(java.lang.String)
     */
    @Override
    public SubscribeRoomGroup groupId(String groupId) {
        this.groupId = groupId;
        return this;
    }

}
