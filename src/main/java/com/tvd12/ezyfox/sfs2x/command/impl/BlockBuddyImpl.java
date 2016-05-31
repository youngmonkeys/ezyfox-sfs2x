package com.tvd12.ezyfox.sfs2x.command.impl;

import com.smartfoxserver.v2.SmartFoxServer;
import com.smartfoxserver.v2.api.ISFSApi;
import com.smartfoxserver.v2.api.ISFSBuddyApi;
import com.smartfoxserver.v2.entities.User;
import com.smartfoxserver.v2.extensions.ISFSExtension;
import com.tvd12.ezyfox.core.command.BlockBuddy;
import com.tvd12.ezyfox.core.model.ApiBaseUser;
import com.tvd12.ezyfox.sfs2x.content.impl.AppContextImpl;

/**
 * @author tavandung12
 * 
 * Created on May 26, 2016
 *
 */
public class BlockBuddyImpl extends BaseCommandImpl implements BlockBuddy {

    private String owner;
    private String buddy;
    private boolean blocked;
    private boolean fireClientEvent = true;
    private boolean fireServerEvent = true;
    
    /**
     * @param context
     * @param api
     * @param extension
     */
    public BlockBuddyImpl(AppContextImpl context, ISFSApi api, ISFSExtension extension) {
        super(context, api, extension);
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.BaseCommand#execute()
     */
    @SuppressWarnings("unchecked")
    @Override
    public Boolean execute() {
        User sfsOwner = CommandUtil.getSfsUser(owner, api);
        ISFSBuddyApi buddyApi = SmartFoxServer.getInstance()
                .getAPIManager().getBuddyApi();
        buddyApi.blockBuddy(sfsOwner, buddy, blocked, fireClientEvent, fireServerEvent);
        return Boolean.TRUE;
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.BlockBuddy#owner(com.tvd12.ezyfox.core.model.ApiBaseUser)
     */
    @SuppressWarnings("unchecked")
    @Override
    public BlockBuddy owner(ApiBaseUser owner) {
        this.owner = owner.getName();
        return this;
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.BlockBuddy#owner(java.lang.String)
     */
    @SuppressWarnings("unchecked")
    @Override
    public BlockBuddy owner(String ownerName) {
        this.owner = ownerName;
        return this;
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.BlockBuddy#buddy(java.lang.String)
     */
    @SuppressWarnings("unchecked")
    @Override
    public BlockBuddy buddy(String buddyName) {
        this.buddy = buddyName;
        return this;
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.BlockBuddy#blocked(boolean)
     */
    @SuppressWarnings("unchecked")
    @Override
    public BlockBuddy blocked(boolean isBlock) {
        this.blocked = isBlock;
        return this;
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.BlockBuddy#fireClientEvent(boolean)
     */
    @SuppressWarnings("unchecked")
    @Override
    public BlockBuddy fireClientEvent(boolean fireClientEvent) {
        this.fireClientEvent = fireClientEvent;
        return this;
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.BlockBuddy#fireServerEvent(boolean)
     */
    @SuppressWarnings("unchecked")
    @Override
    public BlockBuddy fireServerEvent(boolean fireServerEvent) {
        this.fireServerEvent = fireServerEvent;
        return this;
    }

}
