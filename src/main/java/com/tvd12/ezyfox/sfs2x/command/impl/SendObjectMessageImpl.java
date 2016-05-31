package com.tvd12.ezyfox.sfs2x.command.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import com.smartfoxserver.v2.api.ISFSApi;
import com.smartfoxserver.v2.entities.Room;
import com.smartfoxserver.v2.entities.User;
import com.smartfoxserver.v2.entities.data.ISFSObject;
import com.smartfoxserver.v2.entities.data.SFSObject;
import com.smartfoxserver.v2.extensions.ISFSExtension;
import com.tvd12.ezyfox.core.command.SendObjectMessage;
import com.tvd12.ezyfox.core.config.APIKey;
import com.tvd12.ezyfox.core.model.ApiBaseUser;
import com.tvd12.ezyfox.core.model.ApiRoom;
import com.tvd12.ezyfox.core.structure.MessageParamsClass;
import com.tvd12.ezyfox.sfs2x.content.impl.AppContextImpl;
import com.tvd12.ezyfox.sfs2x.serializer.ResponseParamParser;

/**
 * @author tavandung12
 * Created on May 26, 2016
 *
 */
public class SendObjectMessageImpl extends BaseCommandImpl implements SendObjectMessage {

    private String sender;
    private String roomName;
    private String messageString;
    private String jsonMessage;
    private int roomId;
    private Object messageObject;
    private Set<String> recipients
            = new HashSet<>();
    
    /**
     * @param context
     * @param api
     * @param extension
     */
    public SendObjectMessageImpl(AppContextImpl context, ISFSApi api, ISFSExtension extension) {
        super(context, api, extension);
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.BaseCommand#execute()
     */
    @SuppressWarnings("unchecked")
    @Override
    public Boolean execute() {
        User sfsSender = CommandUtil.getSfsUser(sender, api);
        api.sendObjectMessage(getSfsRoom(), sfsSender, getMessage(), getSFSRecipients());
        return Boolean.TRUE;
    }
    
    private Room getSfsRoom() {
        if(StringUtils.isEmpty(roomName))
            return extension.getParentZone().getRoomById(roomId);
        return CommandUtil.getSfsRoom(roomName, extension);
    }
    
    private ISFSObject getMessage() {
        if(messageObject != null) {
            MessageParamsClass clazz = context.getMessageParamsClass(messageObject.getClass());
            if(clazz != null) 
                return ResponseParamParser.getInstance().parse(clazz.getUnwrapper(), messageObject);
        }
        if(jsonMessage != null)
            return SFSObject.newFromJsonData(jsonMessage);
        if(messageString == null)
            return new SFSObject();
        ISFSObject answer = new SFSObject();
        answer.putUtfString(APIKey.MESSAGE, messageString);
        return answer;
            
    }
    
    private Collection<User> getSFSRecipients() {
        List<User> answer = new ArrayList<>();
        for(String recipient : recipients) {
            User sfsUser = CommandUtil.getSfsUser(recipient, api);
            if(sfsUser != null)
                answer.add(sfsUser);
        }
        return answer;
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.SendObjectMessage#room(com.tvd12.ezyfox.core.model.ApiRoom)
     */
    @SuppressWarnings("unchecked")
    @Override
    public SendObjectMessage room(ApiRoom room) {
        this.roomName = room.getName();
        return this;
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.SendObjectMessage#room(java.lang.String)
     */
    @SuppressWarnings("unchecked")
    @Override
    public SendObjectMessage room(String roomName) {
        this.roomName = roomName;
        return this;
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.SendObjectMessage#room(int)
     */
    @SuppressWarnings("unchecked")
    @Override
    public SendObjectMessage room(int roomId) {
        this.roomId = roomId;
        return this;
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.SendObjectMessage#sender(com.tvd12.ezyfox.core.model.ApiBaseUser)
     */
    @SuppressWarnings("unchecked")
    @Override
    public SendObjectMessage sender(ApiBaseUser sender) {
        this.sender = sender.getName();
        return this;
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.SendObjectMessage#recipient(com.tvd12.ezyfox.core.model.ApiBaseUser)
     */
    @SuppressWarnings("unchecked")
    @Override
    public SendObjectMessage recipient(ApiBaseUser recipient) {
        this.recipients.add(recipient.getName());
        return this;
    }

    

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.SendObjectMessage#message(java.lang.String)
     */
    @SuppressWarnings("unchecked")
    @Override
    public SendObjectMessage message(String message) {
        this.messageString = message;
        return this;
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.SendObjectMessage#message(java.lang.Object)
     */
    @SuppressWarnings("unchecked")
    @Override
    public SendObjectMessage message(Object data) {
        this.messageObject = data;
        return this;
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.SendObjectMessage#jsonMessage(java.lang.String)
     */
    @SuppressWarnings("unchecked")
    @Override
    public SendObjectMessage jsonMessage(String jsonMessage) {
        this.jsonMessage = jsonMessage;
        return this;
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.SendObjectMessage#recipients(java.util.List)
     */
    @SuppressWarnings("unchecked")
    @Override
    public <T extends SendObjectMessage> T recipients(List<ApiBaseUser> recipients) {
        for(ApiBaseUser user : recipients) {
            this.recipients.add(user.getName());
        }
        return (T) this;
    }
    
    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.SendObjectMessage#recipients(java.lang.String[])
     */
    @SuppressWarnings("unchecked")
    @Override
    public SendObjectMessage recipients(String... recipients) {
        this.recipients.addAll(Arrays.asList(recipients));
        return this;
    }
    
    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.SendObjectMessage#recipient(java.lang.String)
     */
    @SuppressWarnings("unchecked")
    @Override
    public SendObjectMessage recipient(String recipientName) {
        this.recipients.add(recipientName);
        return this;
    }

}
