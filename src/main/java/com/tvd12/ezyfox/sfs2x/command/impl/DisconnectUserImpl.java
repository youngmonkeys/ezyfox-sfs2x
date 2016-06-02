/**
 * 
 */
package com.tvd12.ezyfox.sfs2x.command.impl;

import com.smartfoxserver.v2.api.ISFSApi;
import com.smartfoxserver.v2.entities.User;
import com.smartfoxserver.v2.extensions.ISFSExtension;
import com.smartfoxserver.v2.util.IDisconnectionReason;
import com.tvd12.ezyfox.core.command.DisconnectUser;
import com.tvd12.ezyfox.core.model.ApiBaseUser;
import com.tvd12.ezyfox.sfs2x.content.impl.AppContextImpl;

/**
 * @see DisconnectUser
 * 
 * @author tavandung12
 *
 */
public class DisconnectUserImpl extends BaseCommandImpl implements DisconnectUser {

    private String username;
    private byte reasonId = -1;
    
    /**
     * @param context
     * @param api
     * @param extension
     */
    public DisconnectUserImpl(AppContextImpl context, ISFSApi api, ISFSExtension extension) {
        super(context, api, extension);
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.DisconnectUser#user(com.tvd12.ezyfox.core.model.ApiBaseUser)
     */
    @SuppressWarnings("unchecked")
    @Override
    public DisconnectUser user(ApiBaseUser userToDisconnect) {
        this.username = userToDisconnect.getName();
        return this;
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.DisconnectUser#user(java.lang.String)
     */
    @SuppressWarnings("unchecked")
    @Override
    public DisconnectUser user(String usernameToDisconnect) {
        this.username = usernameToDisconnect;
        return this;
    }
    
    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.DisconnectUser#reasonId(byte)
     */
    @SuppressWarnings("unchecked")
    @Override
    public DisconnectUser reasonId(byte id) {
        this.reasonId = id;
        return this;
    }
    
    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.BaseCommand#execute()
     */
    @SuppressWarnings("unchecked")
    @Override
    public Boolean execute() {
        User user = CommandUtil.getSfsUser(username, api);
        if(reasonId == -1) {
            api.disconnectUser(user);
            return Boolean.TRUE;
        }
        api.disconnectUser(user, new IDisconnectionReason() {
            
            @Override
            public int getValue() {
                return reasonId;
            }
            
            @Override
            public byte getByteValue() {
                return (byte)getValue();
            }
        });
        return null;
    }

}
