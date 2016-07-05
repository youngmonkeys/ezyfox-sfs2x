/**
 * 
 */
package com.tvd12.ezyfox.sfs2x.command.impl;

import com.smartfoxserver.v2.SmartFoxServer;
import com.smartfoxserver.v2.api.ISFSApi;
import com.smartfoxserver.v2.api.ISFSBuddyApi;
import com.smartfoxserver.v2.entities.User;
import com.smartfoxserver.v2.extensions.ISFSExtension;
import com.tvd12.ezyfox.core.command.RemoveBuddy;
import com.tvd12.ezyfox.core.entities.ApiBaseUser;
import com.tvd12.ezyfox.core.entities.ApiZone;
import com.tvd12.ezyfox.sfs2x.content.impl.AppContextImpl;

/**
 * @see RemoveBuddy
 * 
 * @author tavandung12
 *
 */
public class RemoveBuddyImpl extends BaseCommandImpl implements RemoveBuddy {

    private String owner;
    private String buddy;
    private boolean fireClientEvent = true;
    private boolean fireServerEvent = true;
    
    protected ApiZone zone;
    
    /**
     * @param context
     * @param api
     * @param extension
     */
    public RemoveBuddyImpl(AppContextImpl context, ISFSApi api, ISFSExtension extension) {
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
        buddyApi.removeBuddy(sfsOwner, buddy, fireClientEvent, fireServerEvent);
        return Boolean.TRUE;
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.RemoveBuddy#owner(com.tvd12.ezyfox.core.entities.ApiBaseUser)
     */
    @Override
    public RemoveBuddy owner(ApiBaseUser owner) {
        this.owner = owner.getName();
        return this;
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.RemoveBuddy#owner(java.lang.String)
     */
    @Override
    public RemoveBuddy owner(String ownerName) {
        this.owner = ownerName;
        return this;
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.RemoveBuddy#zone(com.tvd12.ezyfox.core.entities.ApiZone)
     */
    @Override
    public RemoveBuddy zone(ApiZone zone) {
        this.zone = zone;
        return this;
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.RemoveBuddy#buddy(java.lang.String)
     */
    @Override
    public RemoveBuddy buddy(String buddyName) {
        this.buddy = buddyName;
        return this;
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.RemoveBuddy#fireClientEvent(boolean)
     */
    @Override
    public RemoveBuddy fireClientEvent(boolean fireClientEvent) {
        this.fireClientEvent = fireClientEvent;
        return this;
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.RemoveBuddy#fireServerEvent(boolean)
     */
    @Override
    public RemoveBuddy fireServerEvent(boolean fireServerEvent) {
        this.fireServerEvent = fireServerEvent;
        return this;
    }

}
