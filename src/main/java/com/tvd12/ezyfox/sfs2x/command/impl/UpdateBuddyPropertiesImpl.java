package com.tvd12.ezyfox.sfs2x.command.impl;

import static com.tvd12.ezyfox.sfs2x.serializer.BuddyAgentUnwrapper.buddyAgentUnwrapper;

import java.util.List;

import com.smartfoxserver.v2.SmartFoxServer;
import com.smartfoxserver.v2.api.ISFSApi;
import com.smartfoxserver.v2.buddylist.BuddyVariable;
import com.smartfoxserver.v2.entities.User;
import com.smartfoxserver.v2.exceptions.SFSBuddyListException;
import com.smartfoxserver.v2.extensions.ISFSExtension;
import com.tvd12.ezyfox.core.command.UpdateBuddyProperties;
import com.tvd12.ezyfox.core.model.ApiBaseUser;
import com.tvd12.ezyfox.core.structure.BuddyClassUnwrapper;
import com.tvd12.ezyfox.sfs2x.content.impl.AppContextImpl;

/**
 * @author tavandung12
 * Created on May 27, 2016
 *
 */
public class UpdateBuddyPropertiesImpl extends BaseCommandImpl implements UpdateBuddyProperties {

    private ApiBaseUser owner;
    private boolean fireClientEvent = true;
    private boolean fireServerEvent = true;
    
    /**
     * @param context
     * @param api
     * @param extension
     */
    public UpdateBuddyPropertiesImpl(AppContextImpl context, ISFSApi api, ISFSExtension extension) {
        super(context, api, extension);
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.BaseCommand#execute()
     */
    @SuppressWarnings("unchecked")
    @Override
    public Boolean execute() {
        BuddyClassUnwrapper unwrapper = context.getUserAgentClass(owner.getClass())
                .getBuddyClass().getUnwrapper();
        List<BuddyVariable> variables = buddyAgentUnwrapper().unwrap(unwrapper, owner);
        try {
            User sfsUser = CommandUtil.getSfsUser(owner, api);
            SmartFoxServer.getInstance().getAPIManager().getBuddyApi()
                    .setBuddyVariables(sfsUser, variables, 
                            fireClientEvent, fireServerEvent);
        } catch (SFSBuddyListException e) {
            throw new IllegalStateException(e);
        }
        return Boolean.TRUE;
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.UpdateBuddyProperties#owner(com.tvd12.ezyfox.core.model.ApiBaseUser)
     */
    @SuppressWarnings("unchecked")
    @Override
    public UpdateBuddyProperties owner(ApiBaseUser user) {
        this.owner = user;
        return this;
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.UpdateBuddyProperties#fireClientEvent(boolean)
     */
    @SuppressWarnings("unchecked")
    @Override
    public UpdateBuddyProperties fireClientEvent(boolean value) {
        this.fireClientEvent = value;
        return this;
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.UpdateBuddyProperties#fireServerEvent(boolean)
     */
    @SuppressWarnings("unchecked")
    @Override
    public UpdateBuddyProperties fireServerEvent(boolean value) {
        this.fireServerEvent = value;
        return this;
    }

}
