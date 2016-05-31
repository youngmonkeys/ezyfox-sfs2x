package com.tvd12.ezyfox.sfs2x.command.impl;

import com.smartfoxserver.v2.api.ISFSApi;
import com.smartfoxserver.v2.entities.managers.BanMode;
import com.smartfoxserver.v2.extensions.ISFSExtension;
import com.tvd12.ezyfox.core.command.BanUser;
import com.tvd12.ezyfox.core.model.ApiBaseUser;
import com.tvd12.ezyfox.sfs2x.content.impl.AppContextImpl;

/**
 * @author tavandung12
 * Created on May 26, 2016
 *
 */
public class BanUserImpl extends BaseCommandImpl implements BanUser {

    private String message;
    private boolean bandByAddressMode;
    private int durationMinutes;
    private int delaySeconds;
    private String modUser = "";
    private String userToBan = "";
    
    
    /**
     * @param context
     * @param api
     * @param extension
     */
    public BanUserImpl(AppContextImpl context, ISFSApi api, ISFSExtension extension) {
        super(context, api, extension);
        // TODO Auto-generated constructor stub
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.BaseCommand#execute()
     */
    @SuppressWarnings("unchecked")
    @Override
    public Boolean execute() {
        api.banUser(CommandUtil.getSfsUser(userToBan, api), 
                CommandUtil.getSfsUser(modUser, api), 
                message, 
                bandByAddressMode ? BanMode.BY_ADDRESS : BanMode.BY_NAME, 
                durationMinutes, 
                delaySeconds);
        return Boolean.TRUE;
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.BanUser#user(com.tvd12.ezyfox.core.model.ApiBaseUser)
     */
    @SuppressWarnings("unchecked")
    @Override
    public BanUser user(ApiBaseUser userToBan) {
        this.userToBan = userToBan.getName();
        return this;
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.BanUser#user(java.lang.String)
     */
    @SuppressWarnings("unchecked")
    @Override
    public BanUser user(String userToBan) {
        this.userToBan = userToBan;
        return this;
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.BanUser#modUser(java.lang.String)
     */
    @SuppressWarnings("unchecked")
    @Override
    public BanUser modUser(String modUser) {
        this.modUser = modUser;
        return this;
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.BanUser#message(java.lang.String)
     */
    @SuppressWarnings("unchecked")
    @Override
    public BanUser message(String banMessage) {
        this.message = banMessage;
        return this;
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.BanUser#byAddress()
     */
    @SuppressWarnings("unchecked")
    @Override
    public BanUser byAddress() {
        this.bandByAddressMode = true;
        return this;
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.BanUser#byName()
     */
    @SuppressWarnings("unchecked")
    @Override
    public BanUser byName() {
        this.bandByAddressMode = false;
        return this;
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.BanUser#duration(int)
     */
    @SuppressWarnings("unchecked")
    @Override
    public BanUser duration(int durationMinutes) {
        this.durationMinutes = durationMinutes;
        return this;
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.BanUser#delay(int)
     */
    @SuppressWarnings("unchecked")
    @Override
    public BanUser delay(int delaySeconds) {
        this.delaySeconds = delaySeconds;
        return this;
    }

}
