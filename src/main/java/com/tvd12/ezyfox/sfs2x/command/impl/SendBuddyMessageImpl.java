package com.tvd12.ezyfox.sfs2x.command.impl;

import com.smartfoxserver.v2.api.ISFSApi;
import com.smartfoxserver.v2.entities.User;
import com.smartfoxserver.v2.entities.data.ISFSObject;
import com.smartfoxserver.v2.entities.data.SFSObject;
import com.smartfoxserver.v2.exceptions.SFSBuddyListException;
import com.smartfoxserver.v2.extensions.ISFSExtension;
import com.tvd12.ezyfox.core.command.SendBuddyMessage;
import com.tvd12.ezyfox.core.entities.ApiBaseUser;
import com.tvd12.ezyfox.core.structure.MessageParamsClass;
import com.tvd12.ezyfox.sfs2x.content.impl.AppContextImpl;
import com.tvd12.ezyfox.sfs2x.serializer.ResponseParamSerializer;

/**
 * @see SendBuddyMessage
 * 
 * @author tavandung12
 * Created on May 26, 2016
 *
 */
public class SendBuddyMessageImpl extends BaseCommandImpl implements SendBuddyMessage {
    
    private String sender;
    private String recipient;
    private String message;
    private Object params;
    
    /**
     * @param context the context
     * @param api the api
     * @param extension the extension
     */
    public SendBuddyMessageImpl(AppContextImpl context, ISFSApi api, ISFSExtension extension) {
        super(context, api, extension);
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.BaseCommand#execute()
     */
    @SuppressWarnings("unchecked")
    @Override
    public Boolean execute() {
        User sfsSender = CommandUtil.getSfsUser(sender, api);
        User sfsRecipient = CommandUtil.getSfsUser(recipient, api);
        if(sfsSender == null || sfsRecipient == null)
            return Boolean.FALSE;
        message = (message == null) ? "" : message;
        ISFSObject sfsParams = null;
        if(params != null) {
            MessageParamsClass clazz = context.getMessageParamsClass(params.getClass());
            if(clazz != null) 
                sfsParams = ResponseParamSerializer.getInstance().object2params(clazz.getUnwrapper(), params);
        }
        if(sfsParams == null) sfsParams = new SFSObject();
        try {
            api.sendBuddyMessage(sfsSender, sfsRecipient, message, sfsParams);
        } catch (SFSBuddyListException e) {
            throw new IllegalStateException(e);
        }
        return Boolean.TRUE;
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.SendBuddyMessage#sender(com.tvd12.ezyfox.core.entities.ApiBaseUser)
     */
    @Override
    public SendBuddyMessage sender(ApiBaseUser sender) {
        this.sender = sender.getName();
        return this;
    }
    
    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.SendBuddyMessage#sender(java.lang.String)
     */
    @Override
    public SendBuddyMessage sender(String senderName) {
        this.sender = senderName;
        return this;
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.SendBuddyMessage#recipient(com.tvd12.ezyfox.core.entities.ApiBaseUser)
     */
    @Override
    public SendBuddyMessage recipient(ApiBaseUser recipient) {
        this.recipient = recipient.getName();
        return this;
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.SendBuddyMessage#recipient(java.lang.String)
     */
    @Override
    public SendBuddyMessage recipient(String recipientName) {
        this.recipient = recipientName;
        return this;
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.SendBuddyMessage#message(java.lang.String)
     */
    @Override
    public SendBuddyMessage message(String message) {
        this.message = message;
        return this;
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.SendBuddyMessage#params(java.lang.Object)
     */
    @Override
    public SendBuddyMessage params(Object params) {
        this.params = params;
        return this;
    }

}
