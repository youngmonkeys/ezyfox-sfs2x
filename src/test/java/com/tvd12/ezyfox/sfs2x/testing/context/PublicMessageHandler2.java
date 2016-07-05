package com.tvd12.ezyfox.sfs2x.testing.context;

import com.tvd12.ezyfox.core.annotation.MessageParams;
import com.tvd12.ezyfox.core.annotation.ServerEventHandler;
import com.tvd12.ezyfox.core.config.ServerEvent;
import com.tvd12.ezyfox.core.content.AppContext;
import com.tvd12.ezyfox.core.entities.ApiMessage;

import lombok.Data;

/**
 * @author tavandung12
 * Created on May 26, 2016
 *
 */
@Data
@MessageParams
@ServerEventHandler(event = ServerEvent.PUBLIC_MESSAGE)
public class PublicMessageHandler2 {
    
    public void handle(AppContext context, ApiMessage message) {
        
    }

}
