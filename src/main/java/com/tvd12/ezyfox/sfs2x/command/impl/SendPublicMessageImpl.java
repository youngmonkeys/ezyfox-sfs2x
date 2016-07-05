package com.tvd12.ezyfox.sfs2x.command.impl;

import com.smartfoxserver.v2.api.ISFSApi;
import com.smartfoxserver.v2.entities.Room;
import com.smartfoxserver.v2.entities.User;
import com.smartfoxserver.v2.entities.data.ISFSObject;
import com.smartfoxserver.v2.entities.data.SFSObject;
import com.smartfoxserver.v2.extensions.ISFSExtension;
import com.tvd12.ezyfox.core.command.SendPublicMessage;
import com.tvd12.ezyfox.core.entities.ApiBaseUser;
import com.tvd12.ezyfox.core.entities.ApiRoom;
import com.tvd12.ezyfox.core.structure.MessageParamsClass;
import com.tvd12.ezyfox.sfs2x.content.impl.AppContextImpl;
import com.tvd12.ezyfox.sfs2x.serializer.ResponseParamSerializer;

/**
 * @see SendPublicMessage
 * 
 * @author tavandung12
 * Created on May 26, 2016
 *
 */
public class SendPublicMessageImpl extends BaseCommandImpl implements SendPublicMessage {
    
    private String sender;
    private String room;
    private String message;
    private Object params;
    
    /**
     * @param context
     * @param api
     * @param extension
     */
    public SendPublicMessageImpl(AppContextImpl context, ISFSApi api, ISFSExtension extension) {
        super(context, api, extension);
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.BaseCommand#execute()
     */
    @SuppressWarnings("unchecked")
    @Override
    public Boolean execute() {
        User sfsSender = CommandUtil.getSfsUser(sender, api);
        Room sfsRoom = CommandUtil.getSfsRoom(room, extension);
        if(sfsSender == null || sfsRoom == null)
            return Boolean.FALSE;
        message = (message == null) ? "" : message;
        ISFSObject sfsParams = null;
        if(params != null) {
            MessageParamsClass clazz = context.getMessageParamsClass(params.getClass());
            if(clazz != null) 
                sfsParams = ResponseParamSerializer.getInstance().object2params(clazz.getUnwrapper(), params);
        }
        if(sfsParams == null) sfsParams = new SFSObject();
        api.sendPublicMessage(sfsRoom, sfsSender, message, sfsParams);
        return Boolean.TRUE;
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.SendBuddyMessage#sender(com.tvd12.ezyfox.core.entities.ApiBaseUser)
     */
    @Override
    public SendPublicMessage sender(ApiBaseUser sender) {
        this.sender = sender.getName();
        return this;
    }
    
    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.SendBuddyMessage#sender(java.lang.String)
     */
    @Override
    public SendPublicMessage sender(String senderName) {
        this.sender = senderName;
        return this;
    }
    
    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.SendPublicMessage#room(com.tvd12.ezyfox.core.entities.ApiRoom)
     */
    @Override
    public SendPublicMessage room(ApiRoom room) {
        this.room = room.getName();
        return this;
    }
    
    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.SendPublicMessage#room(java.lang.String)
     */
    @Override
    public SendPublicMessage room(String roomName) {
        this.room = roomName;
        return this;
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.SendBuddyMessage#message(java.lang.String)
     */
    @Override
    public SendPublicMessage message(String message) {
        this.message = message;
        return this;
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.SendBuddyMessage#params(java.lang.Object)
     */
    @Override
    public SendPublicMessage params(Object params) {
        this.params = params;
        return this;
    }

}
