package com.tvd12.ezyfox.sfs2x.serverhandler;

import com.smartfoxserver.v2.buddylist.SFSBuddyEventParam;
import com.smartfoxserver.v2.core.ISFSEvent;
import com.smartfoxserver.v2.core.SFSEventParam;
import com.smartfoxserver.v2.entities.User;
import com.smartfoxserver.v2.exceptions.SFSException;
import com.tvd12.ezyfox.core.constants.APIKey;
import com.tvd12.ezyfox.core.constants.ServerEvent;
import com.tvd12.ezyfox.core.content.impl.BaseAppContext;
import com.tvd12.ezyfox.core.entities.ApiBaseUser;

/**
 * Support to handle BUDDY_ONLINE_STATE_UPDATE event
 * 
 * @author tavandung12
 * Created on Jun 7, 2016
 *
 */
public class BuddyOnlineStateUpdateEventHandler extends UserZoneEventHandler {
    /**
     * @param context the context
     */
    public BuddyOnlineStateUpdateEventHandler(BaseAppContext context) {
        super(context);
    }
    
    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.sfs2x.serverhandler.UserZoneEventHandler#handleServerEvent(com.smartfoxserver.v2.core.ISFSEvent)
     */
    @Override
    public void handleServerEvent(ISFSEvent event) throws SFSException {
        User user = (User) event.getParameter(SFSEventParam.USER);
        boolean online = (Boolean)event.getParameter(SFSBuddyEventParam.BUDDY_IS_ONLINE);
        updateBuddyProperties((ApiBaseUser) user.getProperty(APIKey.USER), online);
        super.handleServerEvent(event);
    }
    
    /**
     * Update buddy properties of user
     * 
     * @param user the user
     * @param online online state
     */
    private void updateBuddyProperties(ApiBaseUser user, boolean online) {
        user.getBuddyProperties().setOnline(online);
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.sfs2x.serverhandler.ServerEventHandler#eventName()
     */
    @Override
    public String eventName() {
        return ServerEvent.BUDDY_ONLINE_STATE_UPDATE;
    }
    
}
