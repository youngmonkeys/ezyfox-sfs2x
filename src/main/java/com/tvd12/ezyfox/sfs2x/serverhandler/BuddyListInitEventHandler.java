/**
 * 
 */
package com.tvd12.ezyfox.sfs2x.serverhandler;

import com.smartfoxserver.v2.buddylist.BuddyProperties;
import com.smartfoxserver.v2.core.ISFSEvent;
import com.smartfoxserver.v2.core.SFSEventParam;
import com.smartfoxserver.v2.entities.User;
import com.smartfoxserver.v2.exceptions.SFSException;
import com.tvd12.ezyfox.core.config.APIKey;
import com.tvd12.ezyfox.core.config.ServerEvent;
import com.tvd12.ezyfox.core.model.ApiBuddyProperties;
import com.tvd12.ezyfox.core.model.ApiUser;
import com.tvd12.ezyfox.sfs2x.content.impl.AppContextImpl;

/**
 * @author tavandung12
 *
 */
public class BuddyListInitEventHandler extends UserZoneEventHandler {

    /**
     * @param context
     */
    public BuddyListInitEventHandler(AppContextImpl context) {
        super(context);
    }
    
    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.sfs2x.serverhandler.UserZoneEventHandler#handleServerEvent(com.smartfoxserver.v2.core.ISFSEvent)
     */
    @Override
    public void handleServerEvent(ISFSEvent event) throws SFSException {
        User sfsUser = (User)event.getParameter(SFSEventParam.USER);
        ApiUser apiUser = (ApiUser) sfsUser.getProperty(APIKey.USER);
        if(apiUser != null && apiUser.getBuddyProperties() != null) 
            updateBuddyProperties(sfsUser.getBuddyProperties(), 
                    apiUser.getBuddyProperties());
        super.handleServerEvent(event);
    }
    
    private void updateBuddyProperties(BuddyProperties buddyProperties,
            ApiBuddyProperties apiBuddyProperties) {
        apiBuddyProperties.setInited(buddyProperties.isInited());
        apiBuddyProperties.setNickName(buddyProperties.getNickName());
        apiBuddyProperties.setOnline(buddyProperties.isOnline());
        apiBuddyProperties.setState(buddyProperties.getState());
    }
    
    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.sfs2x.serverhandler.ServerEventHandler#eventName()
     */
    @Override
    public String eventName() {
        return ServerEvent.BUDDY_LIST_INIT;
    }

}
