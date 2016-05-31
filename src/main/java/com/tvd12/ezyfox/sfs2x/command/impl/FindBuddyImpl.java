package com.tvd12.ezyfox.sfs2x.command.impl;

import com.smartfoxserver.v2.api.ISFSApi;
import com.smartfoxserver.v2.extensions.ISFSExtension;
import com.tvd12.ezyfox.core.command.FindBuddy;
import com.tvd12.ezyfox.core.model.ApiBaseUser;
import com.tvd12.ezyfox.core.model.ApiBuddy;
import com.tvd12.ezyfox.sfs2x.content.impl.AppContextImpl;

/**
 * @author tavandung12
 * Created on May 28, 2016
 *
 */
public class FindBuddyImpl extends BaseCommandImpl implements FindBuddy {

    private String owner;
    private String buddy;
    
    /**
     * @param context
     * @param api
     * @param extension
     */
    public FindBuddyImpl(AppContextImpl context, ISFSApi api, ISFSExtension extension) {
        super(context, api, extension);
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.BaseCommand#execute()
     */
    @SuppressWarnings("unchecked")
    @Override
    public ApiBuddy execute() {
        return (ApiBuddy) extension.getParentZone()
            .getBuddyListManager()
            .getBuddyList(owner)
            .getBuddy(buddy);
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.FindBuddy#owner(com.tvd12.ezyfox.core.model.ApiBaseUser)
     */
    @SuppressWarnings("unchecked")
    @Override
    public FindBuddy owner(ApiBaseUser owner) {
        this.owner = owner.getName();
        return this;
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.FindBuddy#owner(java.lang.String)
     */
    @SuppressWarnings("unchecked")
    @Override
    public FindBuddy owner(String ownerName) {
        this.owner = ownerName;
        return this;
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.FindBuddy#buddy(java.lang.String)
     */
    @SuppressWarnings("unchecked")
    @Override
    public FindBuddy buddy(String buddyName) {
        this.buddy = buddyName;
        return this;
    }

}
