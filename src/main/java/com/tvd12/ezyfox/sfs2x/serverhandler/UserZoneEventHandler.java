package com.tvd12.ezyfox.sfs2x.serverhandler;

import java.lang.reflect.Method;
import java.util.List;

import com.smartfoxserver.v2.core.ISFSEvent;
import com.smartfoxserver.v2.core.SFSEventParam;
import com.smartfoxserver.v2.entities.User;
import com.smartfoxserver.v2.entities.Zone;
import com.smartfoxserver.v2.exceptions.SFSException;
import com.tvd12.ezyfox.core.config.ZoneEventHandlerCenter;
import com.tvd12.ezyfox.core.constants.APIKey;
import com.tvd12.ezyfox.core.content.impl.BaseAppContext;
import com.tvd12.ezyfox.core.entities.ApiUser;
import com.tvd12.ezyfox.core.entities.ApiZone;
import com.tvd12.ezyfox.core.reflect.ReflectMethodUtil;
import com.tvd12.ezyfox.core.structure.ZoneHandlerClass;

/**
 * Support to handle user join zone or leave zone event
 * 
 * @author tavandung12 Created on Jun 1, 2016
 *
 */
public abstract class UserZoneEventHandler extends ServerUserEventHandler {

    // list of structures of handler classes
    private List<ZoneHandlerClass> handlers;

    /**
     * @param context
     */
    public UserZoneEventHandler(BaseAppContext context) {
        super(context);

    }

    /*
     * (non-Javadoc)
     * 
     * @see com.tvd12.ezyfox.sfs2x.serverhandler.ServerUserEventHandler#init()
     */
    @Override
    protected void init() {
        handlers = new ZoneEventHandlerCenter().addHandlers(handlerClasses, context.getUserClass(),
                context.getGameUserClasses());
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.smartfoxserver.v2.extensions.IServerEventHandler#handleServerEvent(
     * com.smartfoxserver.v2.core.ISFSEvent)
     */
    @Override
    public void handleServerEvent(ISFSEvent event) throws SFSException {
        User sfsUser = (User) event.getParameter(SFSEventParam.USER);
        Zone sfsZone = (Zone) event.getParameter(SFSEventParam.ZONE);
        ApiZone apiZone = getApiZone(sfsZone);
        notifyHandlers(apiZone, sfsUser);

    }

    /**
     * Propagate event to handlers
     * 
     * @param apiZone
     *            api zone reference
     * @param sfsUser
     *            smartfox user object
     */
    private void notifyHandlers(ApiZone apiZone, User sfsUser) {
        ApiUser apiUser = fetchUserAgent(sfsUser);
        for (ZoneHandlerClass handler : handlers) {
            Object userAgent = checkUserAgent(handler, apiUser);
            if (!checkHandler(handler, apiZone))
                continue;
            notifyHandler(handler, apiZone, userAgent);
        }
    }

    /**
     * Fetch user agent object
     * 
     * @param sfsUser
     *            smartfox user object
     * @return user agent object
     */
    protected ApiUser fetchUserAgent(User sfsUser) {
        return (ApiUser) sfsUser.getProperty(APIKey.USER);
    }

    /**
     * Propagate event to handler
     * 
     * @param handler
     *            structure of handler class
     * @param apiZone
     *            api zone reference
     * @param userAgent
     *            user agent reference
     */
    private void notifyHandler(ZoneHandlerClass handler, ApiZone apiZone, Object userAgent) {
        Object instance = handler.newInstance();
        callHandleMethod(handler.getHandleMethod(), instance, apiZone, userAgent);
    }

    /**
     * Check zone name
     * 
     * @param handler
     *            structure of handle class
     * @param apiZone
     *            api zone reference
     * @return true of false
     */
    protected boolean checkHandler(ZoneHandlerClass handler, ApiZone apiZone) {
        return apiZone.getName().startsWith(handler.getZoneName());
    }

    /**
     * Call handle method
     * 
     * @param method
     *            handle method
     * @param instance
     *            object to call method
     * @param apiZone
     *            api zone reference
     * @param userAgent
     *            user agent reference
     */
    private void callHandleMethod(Method method, Object instance, ApiZone apiZone,
            Object userAgent) {
        ReflectMethodUtil.invokeHandleMethod(method, instance, context, apiZone, userAgent);
    }

    /**
     * Get api zone reference mapped to smartfox zone object
     * 
     * @param sfsZone
     *            smartfox zone object
     * @return api zone reference
     */
    private ApiZone getApiZone(Zone sfsZone) {
        return (ApiZone) sfsZone.getProperty(APIKey.ZONE);
    }

}
