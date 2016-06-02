package com.tvd12.ezyfox.sfs2x.command.impl;

import com.smartfoxserver.v2.SmartFoxServer;
import com.smartfoxserver.v2.api.ISFSApi;
import com.smartfoxserver.v2.exceptions.SFSLoginException;
import com.smartfoxserver.v2.extensions.ISFSExtension;
import com.tvd12.ezyfox.core.command.CreateNPC;
import com.tvd12.ezyfox.core.model.ApiZone;
import com.tvd12.ezyfox.sfs2x.content.impl.AppContextImpl;

/**
 * @see CreateNPC
 * 
 * @author tavandung12
 * Created on May 27, 2016
 *
 */
public class CreateNPCImpl extends BaseCommandImpl implements CreateNPC {

    private String username;
    private String zone;
    private boolean forceLogin;
    
    /**
     * @param context
     * @param api
     * @param extension
     */
    public CreateNPCImpl(AppContextImpl context, ISFSApi api, ISFSExtension extension) {
        super(context, api, extension);
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.BaseCommand#execute()
     */
    @SuppressWarnings("unchecked")
    @Override
    public Boolean execute() {
        try {
            api.createNPC(username, 
                    SmartFoxServer.getInstance()
                        .getZoneManager().getZoneByName(zone), 
                    forceLogin);
        } catch (SFSLoginException e) {
            throw new IllegalStateException(e);
        }
        return Boolean.TRUE;
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.CreateNPC#username(java.lang.String)
     */
    @SuppressWarnings("unchecked")
    @Override
    public CreateNPC username(String username) {
        this.username = username;
        return this;
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.CreateNPC#zone(com.tvd12.ezyfox.core.model.ApiZone)
     */
    @SuppressWarnings("unchecked")
    @Override
    public CreateNPC zone(ApiZone zone) {
        this.zone = zone.getName();
        return this;
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.CreateNPC#zone(java.lang.String)
     */
    @SuppressWarnings("unchecked")
    @Override
    public CreateNPC zone(String zoneName) {
        this.zone = zoneName;
        return this;
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.CreateNPC#forceLogin(boolean)
     */
    @SuppressWarnings("unchecked")
    @Override
    public CreateNPC forceLogin(boolean value) {
        this.forceLogin = value;
        return this;
    }

}
