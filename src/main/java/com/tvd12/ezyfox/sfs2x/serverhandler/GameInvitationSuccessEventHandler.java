package com.tvd12.ezyfox.sfs2x.serverhandler;

import com.tvd12.ezyfox.core.config.ServerEvent;
import com.tvd12.ezyfox.sfs2x.content.impl.AppContextImpl;

/**
 * @author tavandung12
 * Created on Jul 3, 2016
 *
 */
public class GameInvitationSuccessEventHandler extends ZoneRoomBaseEventHandler {

    /**
     * @param context
     */
    public GameInvitationSuccessEventHandler(AppContextImpl context) {
        super(context);
    }
    
    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.sfs2x.serverhandler.ServerEventHandler#eventName()
     */
    @Override
    public String eventName() {
        return ServerEvent.GAME_INVITATION_SUCCESS;
    }

}
