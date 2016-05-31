package com.tvd12.ezyfox.sfs2x.serverhandler;

import com.smartfoxserver.v2.core.ISFSEvent;
import com.smartfoxserver.v2.core.SFSEventParam;
import com.smartfoxserver.v2.entities.User;
import com.smartfoxserver.v2.entities.Zone;
import com.smartfoxserver.v2.entities.data.ISFSObject;
import com.smartfoxserver.v2.exceptions.SFSException;
import com.tvd12.ezyfox.core.config.APIKey;
import com.tvd12.ezyfox.core.config.ServerEvent;
import com.tvd12.ezyfox.core.config.ServerEventHandlerCenter;
import com.tvd12.ezyfox.core.model.ApiBuddyMessage;
import com.tvd12.ezyfox.core.model.ApiUser;
import com.tvd12.ezyfox.core.model.ApiZone;
import com.tvd12.ezyfox.sfs2x.content.impl.AppContextImpl;
import com.tvd12.ezyfox.sfs2x.model.impl.ApiBuddyImpl;
import com.tvd12.ezyfox.sfs2x.model.impl.ApiBuddyMessageImpl;

/**
 * @author tavandung12
 * Created on May 26, 2016
 *
 */
public class BuddyMessageEventHandler extends MessageEventHandler {

    /**
     * @param context
     */
    public BuddyMessageEventHandler(AppContextImpl context) {
        super(context);
    }
    
    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.sfs2x.serverhandler.ServerBaseEventHandler#init()
     */
    @Override
    protected void init() {
        handlers = new ServerEventHandlerCenter()
                .addListeners(handlerClasses, ApiBuddyMessage.class);
    }

    /* (non-Javadoc)
     * @see com.smartfoxserver.v2.extensions.IServerEventHandler#handleServerEvent(com.smartfoxserver.v2.core.ISFSEvent)
     */
    @Override
    public void handleServerEvent(ISFSEvent event) throws SFSException {
        Zone sfsZone = (Zone) event.getParameter(SFSEventParam.ZONE);
        User sfsUser = (User) event.getParameter(SFSEventParam.USER);
        ApiBuddyImpl recipient = (ApiBuddyImpl)event.getParameter(SFSEventParam.RECIPIENT);
        String message = (String)event.getParameter(SFSEventParam.MESSAGE);
        ISFSObject params = (ISFSObject)event.getParameter(SFSEventParam.OBJECT);
        ApiBuddyMessageImpl buddyMessage = new ApiBuddyMessageImpl();
        buddyMessage.setContent(message);
        buddyMessage.setSender((ApiUser)sfsUser.getProperty(APIKey.USER));
        buddyMessage.setZone((ApiZone)sfsZone.getProperty(APIKey.ZONE));
        buddyMessage.setRecipient(recipient.getUser());
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
