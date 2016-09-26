package com.tvd12.ezyfox.sfs2x.serverhandler;

import com.smartfoxserver.v2.core.ISFSEvent;
import com.smartfoxserver.v2.core.SFSEventParam;
import com.smartfoxserver.v2.entities.User;
import com.smartfoxserver.v2.entities.Zone;
import com.smartfoxserver.v2.entities.data.ISFSObject;
import com.smartfoxserver.v2.exceptions.SFSException;
import com.tvd12.ezyfox.core.config.ServerEventHandlerCenter;
import com.tvd12.ezyfox.core.constants.APIKey;
import com.tvd12.ezyfox.core.constants.ServerEvent;
import com.tvd12.ezyfox.core.content.impl.BaseAppContext;
import com.tvd12.ezyfox.core.entities.ApiBaseUser;
import com.tvd12.ezyfox.core.entities.ApiBuddyMessage;
import com.tvd12.ezyfox.core.entities.ApiZone;
import com.tvd12.ezyfox.sfs2x.entities.impl.ApiBuddyMessageImpl;

/**
 * Support to handle buddy message event
 * 
 * @author tavandung12
 * Created on May 26, 2016
 *
 */
public class BuddyMessageEventHandler extends MessageEventHandler {

    /**
     * @param context the context
     */
    public BuddyMessageEventHandler(BaseAppContext context) {
        super(context);
    }
    
    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.sfs2x.serverhandler.ServerBaseEventHandler#init()
     */
    @Override
    protected void init() {
        handlers = new ServerEventHandlerCenter()
                .addHandlers(handlerClasses, ApiBuddyMessage.class);
    }

    /* (non-Javadoc)
     * @see com.smartfoxserver.v2.extensions.IServerEventHandler#handleServerEvent(com.smartfoxserver.v2.core.ISFSEvent)
     */
    @Override
    public void handleServerEvent(ISFSEvent event) throws SFSException {
        Zone sfsZone = (Zone) event.getParameter(SFSEventParam.ZONE);
        User sfsUser = (User) event.getParameter(SFSEventParam.USER);
        User recipient = (User)event.getParameter(SFSEventParam.RECIPIENT);
        String message = (String)event.getParameter(SFSEventParam.MESSAGE);
        ISFSObject params = (ISFSObject)event.getParameter(SFSEventParam.OBJECT);
        ApiBuddyMessageImpl buddyMessage = new ApiBuddyMessageImpl();
        buddyMessage.setContent(message);
        buddyMessage.setSender((ApiBaseUser)sfsUser.getProperty(APIKey.USER));
        buddyMessage.setZone((ApiZone)sfsZone.getProperty(APIKey.ZONE));
        buddyMessage.setRecipient((ApiBaseUser)recipient.getProperty(APIKey.USER));
        notifyToHandlers(buddyMessage, params);
    }
    
    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.sfs2x.serverhandler.ServerEventHandler#eventName()
     */
    @Override
    public String eventName() {
        return ServerEvent.BUDDY_MESSAGE;
    }
}
