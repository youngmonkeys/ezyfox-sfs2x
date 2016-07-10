package com.tvd12.ezyfox.sfs2x.testing.serverhandler;

import java.util.Properties;

import org.testng.annotations.Test;

import com.smartfoxserver.v2.core.ISFSEvent;
import com.smartfoxserver.v2.exceptions.SFSException;
import com.smartfoxserver.v2.extensions.SFSExtension;
import com.tvd12.ezyfox.core.annotation.ServerEventHandler;
import com.tvd12.ezyfox.core.constants.ServerEvent;
import com.tvd12.ezyfox.core.content.AppContext;
import com.tvd12.ezyfox.core.structure.ServerHandlerClass;
import com.tvd12.ezyfox.sfs2x.content.impl.AppContextImpl;
import com.tvd12.ezyfox.sfs2x.serverhandler.ServerBaseEventHandler;

/**
 * @author tavandung12
 * Created on May 30, 2016
 *
 */
public class ServerBaseEventHandlerTest extends BaseZoneHandlerTest {
    
    @Test
    public void test() throws SFSException {
        ExServerBaseEventHandler handler = new ExServerBaseEventHandler(context);
        handler.handleServerEvent(null);
    }

    public static class ExServerBaseEventHandler extends ServerBaseEventHandler {

        /**
         * @param context
         */
        public ExServerBaseEventHandler(AppContextImpl context) {
            super(context);
        }

        /* (non-Javadoc)
         * @see com.smartfoxserver.v2.extensions.IServerEventHandler#handleServerEvent(com.smartfoxserver.v2.core.ISFSEvent)
         */
        @Override
        public void handleServerEvent(ISFSEvent event) throws SFSException {
            notifyHandler(new ServerHandlerClass(ClassA.class));
        }

        /* (non-Javadoc)
         * @see com.tvd12.ezyfox.sfs2x.serverhandler.ServerEventHandler#eventName()
         */
        @Override
        public String eventName() {
            return ServerEvent.SERVER_READY;
        }
        
        /* (non-Javadoc)
         * @see com.smartfoxserver.v2.extensions.BaseServerEventHandler#getParentExtension()
         */
        @Override
        public SFSExtension getParentExtension() {
            return new SFSExtension() {
                
                @Override
                public void init() {
                }
                /* (non-Javadoc)
                 * @see com.smartfoxserver.v2.extensions.BaseSFSExtension#getConfigProperties()
                 */
                @Override
                public Properties getConfigProperties() {
                    return new Properties();
                }
            };
        }
        
    }
    
    @ServerEventHandler(event = "abc")
    public static class ClassA {
        public void handle(AppContext context) {
            
        }
    }

}
