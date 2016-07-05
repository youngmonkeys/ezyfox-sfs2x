package com.tvd12.ezyfox.sfs2x.command.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.smartfoxserver.v2.SmartFoxServer;
import com.smartfoxserver.v2.api.ISFSApi;
import com.smartfoxserver.v2.entities.data.ISFSObject;
import com.smartfoxserver.v2.entities.invitation.Invitation;
import com.smartfoxserver.v2.entities.invitation.InvitationCallback;
import com.smartfoxserver.v2.extensions.ISFSExtension;
import com.tvd12.ezyfox.core.command.SendInvitation;
import com.tvd12.ezyfox.core.config.APIKey;
import com.tvd12.ezyfox.core.entities.ApiBaseUser;
import com.tvd12.ezyfox.core.entities.ApiInvitation;
import com.tvd12.ezyfox.core.entities.ApiInvitationImpl;
import com.tvd12.ezyfox.sfs2x.content.impl.AppContextImpl;
import com.tvd12.ezyfox.sfs2x.data.impl.MapUtill;
import com.tvd12.ezyfox.sfs2x.data.impl.ParametersUtil;

/**
 * @author tavandung12
 * Created on Jul 1, 2016
 *
 */
public class SendInvitationImpl extends BaseCommandImpl implements SendInvitation {

    private ApiBaseUser inviter;
    private int expirySeconds;
    private Callback callback;
    private List<ApiBaseUser> invitees = new ArrayList<>();
    private Map<String, Object> params = new HashMap<>();
    
    
    /**
     * @param context
     * @param api
     * @param extension
     */
    public SendInvitationImpl(AppContextImpl context, ISFSApi api, ISFSExtension extension) {
        super(context, api, extension);
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.BaseCommand#execute()
     */
    @SuppressWarnings("unchecked")
    @Override
    public Boolean execute() {
        SmartFoxServer.getInstance()
                .getAPIManager()
                .getGameApi()
                .sendInvitation(CommandUtil.getSfsUser(inviter, api), 
                        CommandUtil.getSFSUserList(invitees, api), 
                        expirySeconds, 
                        createCallback(), 
                        MapUtill.map2SFSObject(context, params));
        return Boolean.TRUE;
    }
    
    private InvitationCallback createCallback() {
        return new InvitationCallback() {
            
            @Override
            public void onRefused(Invitation invitation, ISFSObject params) {
                callback.onRefused(createApiInvitation(invitation), 
                        ParametersUtil.sfsobject2parameters(params));
            }
            
            @Override
            public void onExpired(Invitation invitation) {
                callback.onExpired(createApiInvitation(invitation));
            }
            
            @Override
            public void onAccepted(Invitation invitation, ISFSObject params) {
                callback.onAccepted(createApiInvitation(invitation), 
                        ParametersUtil.sfsobject2parameters(params));
            }
            
            private ApiInvitation createApiInvitation(Invitation invitation) {
                ApiInvitationImpl inv = new ApiInvitationImpl();
                inv.setExpired(invitation.isExpired());
                inv.setExpiryTime(invitation.getExpiryTime());
                inv.setId(invitation.getId());
                inv.setInvitee((ApiBaseUser) invitation.getInvitee().getProperty(APIKey.USER));
                inv.setInviter((ApiBaseUser) invitation.getInviter().getProperty(APIKey.USER));
                inv.setSecondsForAnswer(invitation.getSecondsForAnswer());
                return inv;
            }
        };
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.SendInvitation#inviter(com.tvd12.ezyfox.core.entities.ApiBaseUser)
     */
    @Override
    public SendInvitation inviter(ApiBaseUser user) {
        this.inviter = user;
        return this;
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.SendInvitation#invitees(java.util.List)
     */
    @Override
    public SendInvitation invitees(List<ApiBaseUser> users) {
        this.invitees.addAll(users);
        return this;
    }
    
    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.SendInvitation#invitees(com.tvd12.ezyfox.core.entities.ApiBaseUser[])
     */
    @Override
    public SendInvitation invitees(ApiBaseUser... users) {
        this.invitees.addAll(Arrays.asList(users));
        return this;
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.SendInvitation#expirySeconds(int)
     */
    @Override
    public SendInvitation expirySeconds(int seconds) {
        this.expirySeconds = seconds;
        return this;
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.SendInvitation#callback(com.tvd12.ezyfox.core.command.SendInvitation.Callback)
     */
    @Override
    public SendInvitation callback(Callback callback) {
        this.callback = callback;
        return this;
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.SendInvitation#param(java.lang.String, java.lang.Object)
     */
    @Override
    public SendInvitation param(String name, Object value) {
        params.put(name, value);
        return this;
    }
    
}
