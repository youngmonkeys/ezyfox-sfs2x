package com.tvd12.ezyfox.sfs2x.command.impl;

import com.smartfoxserver.v2.api.ISFSApi;
import com.smartfoxserver.v2.entities.User;
import com.smartfoxserver.v2.entities.data.ISFSObject;
import com.smartfoxserver.v2.entities.data.SFSObject;
import com.smartfoxserver.v2.extensions.ISFSExtension;
import com.tvd12.ezyfox.core.command.SendPrivateMessage;
import com.tvd12.ezyfox.core.entities.ApiBaseUser;
import com.tvd12.ezyfox.core.structure.MessageParamsClass;
import com.tvd12.ezyfox.sfs2x.content.impl.AppContextImpl;
import com.tvd12.ezyfox.sfs2x.serializer.ResponseParamSerializer;

/**
 * @see SendPrivateMessage
 * 
 * @author tavandung12
 * Created on May 26, 2016
 *
 */
public class SendPrivateMessageImpl extends BaseCommandImpl implements SendPrivateMessage {

    private String sender;
    private String message;
    private String recipient;
    private Object params;
    
    /**
     * @param context the context
     * @param api the api
     * @param extension the extension
     */
    public SendPrivateMessageImpl(AppContextImpl context, ISFSApi api, ISFSExtension extension) {
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
        ISFSObject sfsParams = null;
        if(params != null) {
            MessageParamsClass clazz = context.getMessageParamsClass(params.getClass());
            if(clazz != null) 
                sfsParams = new ResponseParamSerializer().object2params(clazz.getUnwrapper(), params);
        }
        if(sfsParams == null) sfsParams = new SFSObject();
        api.sendPrivateMessage(sfsSender, sfsRecipient, message, sfsParams);
        return Boolean.TRUE;
    }
    
    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.SendObjectMessage#sender(com.tvd12.ezyfox.core.entities.ApiBaseUser)
     */
    @Override
    public SendPrivateMessage sender(ApiBaseUser sender) {
        this.sender = sender.getName();
        return this;
    }
    
    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.SendPrivateMessage#sender(java.lang.String)
     */
    @Override
    public SendPrivateMessage sender(String senderName) {
        this.sender = senderName;
        return this;
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.SendObjectMessage#recipient(com.tvd12.ezyfox.core.entities.ApiBaseUser)
     */
    @Override
    public SendPrivateMessage recipient(ApiBaseUser recipient) {
        this.recipient = recipient.getName();
        return this;
    }
    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.SendObjectMessage#message(java.lang.String)
     */
    @Override
    public SendPrivateMessage message(String message) {
        this.message = message;
        return this;
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.SendObjectMessage#recipient(java.lang.String)
     */
    @Override
    public SendPrivateMessage recipient(String recipientName) {
        this.recipient = recipientName;
        return this;
    }
    
    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.SendPrivateMessage#params(java.lang.Object)
     */
    @Override
    public SendPrivateMessage params(Object params) {
        this.params = params;
        return this;
    }

}
