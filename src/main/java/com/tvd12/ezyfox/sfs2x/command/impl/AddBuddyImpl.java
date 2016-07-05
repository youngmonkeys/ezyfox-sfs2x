/**
 * 
 */
package com.tvd12.ezyfox.sfs2x.command.impl;

import java.util.HashMap;
import java.util.Map;

import com.smartfoxserver.v2.SmartFoxServer;
import com.smartfoxserver.v2.api.ISFSApi;
import com.smartfoxserver.v2.api.ISFSBuddyResponseApi;
import com.smartfoxserver.v2.buddylist.BuddyList;
import com.smartfoxserver.v2.buddylist.BuddyListManager;
import com.smartfoxserver.v2.buddylist.SFSBuddyEventParam;
import com.smartfoxserver.v2.controllers.SystemRequest;
import com.smartfoxserver.v2.core.ISFSEventManager;
import com.smartfoxserver.v2.core.ISFSEventParam;
import com.smartfoxserver.v2.core.SFSEvent;
import com.smartfoxserver.v2.core.SFSEventParam;
import com.smartfoxserver.v2.core.SFSEventType;
import com.smartfoxserver.v2.entities.User;
import com.smartfoxserver.v2.exceptions.SFSBuddyListException;
import com.smartfoxserver.v2.extensions.ISFSExtension;
import com.tvd12.ezyfox.core.command.AddBuddy;
import com.tvd12.ezyfox.core.config.APIKey;
import com.tvd12.ezyfox.core.entities.ApiBaseUser;
import com.tvd12.ezyfox.core.entities.ApiBuddy;
import com.tvd12.ezyfox.core.entities.ApiUser;
import com.tvd12.ezyfox.core.entities.ApiZone;
import com.tvd12.ezyfox.sfs2x.content.impl.AppContextImpl;
import com.tvd12.ezyfox.sfs2x.entities.impl.ApiBuddyImpl;

/**
 * @see AddBuddy
 * 
 * @author tavandung12
 *
 */
public class AddBuddyImpl extends BaseCommandImpl implements AddBuddy {

    private String owner;
    private String target;
    private boolean temp = false;
    private boolean fireClientEvent = true;
    private boolean fireServerEvent = true;
    
    protected ApiZone zone;
    
    public AddBuddyImpl(AppContextImpl context, ISFSApi api, ISFSExtension extension) {
        super(context, api, extension);
    }
    
    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.AddBuddy#owner(com.tvd12.ezyfox.core.entities.ApiBaseUser)
     */
    @Override
    public AddBuddy owner(ApiBaseUser owner) {
        this.owner = owner.getName();
        return this;
    }



    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.AddBuddy#owner(java.lang.String)
     */
    @Override
    public AddBuddy owner(String ownerName) {
        this.owner = ownerName;
        return this;
    }



    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.AddBuddy#zone(com.tvd12.ezyfox.core.entities.ApiZone)
     */
    @Override
    public AddBuddy zone(ApiZone zone) {
        this.zone = zone;
        return this;
    }



    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.AddBuddy#buddy(java.lang.String)
     */
    @Override
    public AddBuddy buddy(String buddyName) {
        this.target = buddyName;
        return this;
    }



    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.AddBuddy#temp(boolean)
     */
    @Override
    public AddBuddy temp(boolean isTemp) {
        this.temp = isTemp;
        return this;
    }



    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.AddBuddy#fireClientEvent(boolean)
     */
    @Override
    public AddBuddy fireClientEvent(boolean fireClientEvent) {
        this.fireClientEvent = fireClientEvent;
        return this;
    }



    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.AddBuddy#fireServerEvent(boolean)
     */
    @Override
    public AddBuddy fireServerEvent(boolean fireServerEvent) {
        this.fireServerEvent = fireServerEvent;
        return this;
    }
    
    /**
     * Execute to add a buddy to list
     */
    @SuppressWarnings("unchecked")
    public ApiBuddy execute() {
        User sfsOwner = api.getUserByName(owner);
        ApiUser targetUser = getUser(target);
        ApiUser ownerUser = (ApiUser) sfsOwner.getProperty(APIKey.USER);
        ISFSBuddyResponseApi responseAPI = SmartFoxServer.getInstance().getAPIManager()
                .getBuddyApi().getResponseAPI();
        ISFSEventManager eventManager = SmartFoxServer.getInstance().getEventManager();
        BuddyList buddyList = sfsOwner.getZone()
                .getBuddyListManager().getBuddyList(owner);
        BuddyListManager buddyListManager = sfsOwner.getZone().getBuddyListManager();
        checkBuddyManagerIsActive(buddyListManager, sfsOwner);
        sfsOwner.updateLastRequestTime();
        ApiBuddyImpl buddy = new ApiBuddyImpl(target, temp);
        buddy.setOwner(ownerUser);
        buddy.setParentBuddyList(buddyList);
        if(targetUser != null)  buddy.setUser(targetUser);
        try {
            buddyList.addBuddy(buddy);
            if (fireClientEvent)
                responseAPI.notifyAddBuddy(buddy, sfsOwner);
            if (fireServerEvent) {
                Map<ISFSEventParam, Object> evtParams = new HashMap<>();
                evtParams.put(SFSEventParam.ZONE, sfsOwner.getZone());
                evtParams.put(SFSEventParam.USER, sfsOwner);
                evtParams.put(SFSBuddyEventParam.BUDDY, buddy);
                eventManager.dispatchEvent(new SFSEvent(SFSEventType.BUDDY_ADD, evtParams));
            }
        } catch (SFSBuddyListException e) {
            if (fireClientEvent) {
                api.getResponseAPI().notifyRequestError(e, sfsOwner, SystemRequest.AddBuddy);
            }
            return null;
        }
        return buddy;
    }
    
    /**
     * Check whether buddy manager is active
     * 
     * @param buddyListManager manager object
     * @param sfsOwner buddy's owner
     */
    private void checkBuddyManagerIsActive(BuddyListManager buddyListManager, User sfsOwner) {
        if (!buddyListManager.isActive()) {
            throw new IllegalStateException(
                    String.format("BuddyList operation failure. BuddyListManager is not active. Zone: %s, Sender: %s", new Object[] { sfsOwner.getZone(), sfsOwner }));
          }
    }

    /**
     * Get user agent reference
     * 
     * @param name name of user agent
     * @return user agent object
     */
    public ApiUser getUser(String name) {
        User sfsUser = CommandUtil.getSfsUser(name, api);
        if(sfsUser == null)
            return null;
        return (ApiUser) sfsUser.getProperty(APIKey.USER);
    }
}
