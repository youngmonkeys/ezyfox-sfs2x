package com.tvd12.ezyfox.sfs2x.command.impl;

import static com.tvd12.ezyfox.sfs2x.serializer.RoomAgentUnwrapper.roomAgentUnwrapper;

import java.util.List;

import com.smartfoxserver.v2.api.ISFSApi;
import com.smartfoxserver.v2.entities.Room;
import com.smartfoxserver.v2.entities.User;
import com.smartfoxserver.v2.entities.variables.RoomVariable;
import com.smartfoxserver.v2.extensions.ISFSExtension;
import com.tvd12.ezyfox.core.command.UpdateRoom;
import com.tvd12.ezyfox.core.model.ApiBaseUser;
import com.tvd12.ezyfox.core.model.ApiRoom;
import com.tvd12.ezyfox.core.structure.AgentClassUnwrapper;
import com.tvd12.ezyfox.sfs2x.content.impl.AppContextImpl;

public class UpdateRoomImpl extends BaseCommandImpl implements UpdateRoom {

	private ApiRoom agent;
	private ApiBaseUser user;
	private boolean toClient = true;
	
	public UpdateRoomImpl(AppContextImpl context, ISFSApi api, ISFSExtension extension) {
		super(context, api, extension);
	}

	@SuppressWarnings("unchecked")
	@Override
	public ApiRoom execute() {
		Room sfsRoom = CommandUtil.getSfsRoom(agent, extension);
		User sfsUser = CommandUtil.getSfsUser(user, api);
		
        //check null
        if(sfsRoom == null)  return null;
        
        //get variables from agent
        AgentClassUnwrapper unwrapper = context.getRoomAgentClass(
                agent.getClass()).getUnwrapper();
        List<RoomVariable> variables = 
                roomAgentUnwrapper().unwrap(unwrapper, agent);
        
        //notify to client and serverself
        if(toClient) api.setRoomVariables(sfsUser, sfsRoom, variables);
        
        //only for server
        else sfsRoom.setVariables(variables);
        return agent;
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public UpdateRoom toClient(boolean value) {
		this.toClient = value;
		return this;
	}

	@SuppressWarnings("unchecked")
	@Override
	public UpdateRoom room(ApiRoom room) {
		this.agent = room;
		return this;
	}

	@SuppressWarnings("unchecked")
	@Override
	public UpdateRoom user(ApiBaseUser user) {
		this.user = user;
		return this;
	}
	
}
