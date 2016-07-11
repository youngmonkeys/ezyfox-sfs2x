package com.tvd12.ezyfox.sfs2x.command.impl;

import com.smartfoxserver.v2.api.ISFSApi;
import com.smartfoxserver.v2.extensions.ISFSExtension;
import com.tvd12.ezyfox.core.command.UnsubscribeRoomGroup;
import com.tvd12.ezyfox.core.entities.ApiBaseUser;
import com.tvd12.ezyfox.sfs2x.content.impl.AppContextImpl;

/**
 * @see UnsubscribeRoomGroup
 * 
 * @author tavandung12
 * Created on May 27, 2016
 *
 */
public class UnsubscribeRoomGroupImpl extends BaseCommandImpl implements UnsubscribeRoomGroup {

    private String user;
    private String groupId;
    
    /**
     * @param context
     * @param api
     * @param extension
     */
    public UnsubscribeRoomGroupImpl(AppContextImpl context, ISFSApi api, ISFSExtension extension) {
        super(context, api, extension);
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.BaseCommand#execute()
     */
    @SuppressWarnings("unchecked")
    @Override
    public Boolean execute() {
        api.unsubscribeRoomGroup(CommandUtil.getSfsUser(user, api), groupId);
        return Boolean.TRUE;
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.UnsubscribeRoomGroup#user(com.tvd12.ezyfox.core.entities.ApiBaseUser)
     */
    @Override
    public UnsubscribeRoomGroup user(ApiBaseUser user) {
        this.user = user.getName();
        return this;
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.UnsubscribeRoomGroup#groupId(java.lang.String)
     */
    @Override
    public UnsubscribeRoomGroup groupId(String groupId) {
        this.groupId = groupId;
        return this;
    }

}
