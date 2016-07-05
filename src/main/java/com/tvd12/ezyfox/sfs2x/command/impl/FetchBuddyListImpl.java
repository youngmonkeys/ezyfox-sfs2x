package com.tvd12.ezyfox.sfs2x.command.impl;

import java.util.List;

import com.smartfoxserver.v2.api.ISFSApi;
import com.smartfoxserver.v2.buddylist.BuddyList;
import com.smartfoxserver.v2.extensions.ISFSExtension;
import com.tvd12.ezyfox.core.command.FetchBuddyList;
import com.tvd12.ezyfox.core.entities.ApiBaseUser;
import com.tvd12.ezyfox.core.entities.ApiBuddy;
import com.tvd12.ezyfox.sfs2x.content.impl.AppContextImpl;

/**
 * @see FetchBuddyList
 * 
 * @author tavandung12 Created on May 27, 2016
 *
 */
public class FetchBuddyListImpl extends BaseCommandImpl implements FetchBuddyList {

    private String user;

    /**
     * @param context
     * @param api
     * @param extension
     */
    public FetchBuddyListImpl(AppContextImpl context, ISFSApi api, ISFSExtension extension) {
        super(context, api, extension);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.tvd12.ezyfox.core.command.BaseCommand#execute()
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public List<ApiBuddy> execute() {
        BuddyList buddyList = extension.getParentZone()
                .getBuddyListManager()
                .getBuddyList(user);
        return (List) buddyList.getBuddies();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.tvd12.ezyfox.core.command.FetchBuddyList#user(com.tvd12.ezyfox.core.
     * entities.ApiBaseUser)
     */
    @Override
    public FetchBuddyList user(ApiBaseUser user) {
        this.user = user.getName();
        return this;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.tvd12.ezyfox.core.command.FetchBuddyList#user(java.lang.String)
     */
    @Override
    public FetchBuddyList user(String username) {
        this.user = username;
        return this;
    }

}
