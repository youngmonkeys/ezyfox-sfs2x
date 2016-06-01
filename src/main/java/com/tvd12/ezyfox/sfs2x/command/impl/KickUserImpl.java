package com.tvd12.ezyfox.sfs2x.command.impl;

import com.smartfoxserver.v2.api.ISFSApi;
import com.smartfoxserver.v2.entities.User;
import com.smartfoxserver.v2.extensions.ISFSExtension;
import com.tvd12.ezyfox.core.command.KickUser;
import com.tvd12.ezyfox.core.model.ApiBaseUser;
import com.tvd12.ezyfox.sfs2x.content.impl.AppContextImpl;

/**
 * @see KickUser
 * 
 * @author tavandung12
 * Created on May 31, 2016
 *
 */
public class KickUserImpl extends BaseCommandImpl implements KickUser {

    private String userToKick;
    private String modUser;
    private String kickMessage = "";
    private int delaySeconds = 0;
    
    /**
     * @param context
     * @param api
     * @param extension
     */
    public KickUserImpl(AppContextImpl context, ISFSApi api, ISFSExtension extension) {
        super(context, api, extension);
    }
    
    /* (non-Javadoc)
     * @see com.lagente.core.command.KickUser#user(com.lagente.core.model.ApiBaseUser)
     */
    @SuppressWarnings("unchecked")
    @Override
    public KickUser user(ApiBaseUser user) {
        this.userToKick = user.getName();
        return this;
    }
    
    /* (non-Javadoc)
     * @see com.lagente.core.command.KickUser#user(java.lang.String)
     */
    @SuppressWarnings("unchecked")
    @Override
    public KickUser user(String username) {
        this.userToKick = username;
        return this;
    }
    
    /* (non-Javadoc)
     * @see com.lagente.core.command.KickUser#modUser(com.lagente.core.model.ApiBaseUser)
     */
    @SuppressWarnings("unchecked")
    @Override
    public KickUser modUser(ApiBaseUser user) {
        this.modUser = user.getName();
        return this;
    }
    
    /* (non-Javadoc)
     * @see com.lagente.core.command.KickUser#modUser(java.lang.String)
     */
    @SuppressWarnings("unchecked")
    @Override
    public KickUser modUser(String username) {
        this.modUser = username; 
        return this;
    }
    
    /* (non-Javadoc)
     * @see com.lagente.core.command.KickUser#message(java.lang.String)
     */
    @SuppressWarnings("unchecked")
    @Override
    public KickUser message(String message) {
        this.kickMessage = message;
        return this;
    }
    
    /* (non-Javadoc)
     * @see com.lagente.core.command.KickUser#delay(int)
     */
    @SuppressWarnings("unchecked")
    @Override
    public KickUser delay(int delaySeconds) {
        this.delaySeconds = delaySeconds;
        return this;
    }
    /* (non-Javadoc)
     * @see com.lagente.core.command.BaseCommand#execute()
     */
    @SuppressWarnings("unchecked")
    @Override
    public String execute() {
        User sfsUser = CommandUtil.getSfsUser(userToKick, api);
        User sfsMod = (modUser != null)
                ? CommandUtil.getSfsUser(modUser, api) : null;
        api.kickUser(sfsUser, sfsMod, kickMessage, delaySeconds);
        return userToKick;
    }

}
