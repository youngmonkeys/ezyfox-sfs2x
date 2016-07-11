package com.tvd12.ezyfox.sfs2x.command.impl;

import static com.tvd12.ezyfox.sfs2x.serializer.RoomAgentSerializer.roomAgentSerializer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.smartfoxserver.v2.api.ISFSApi;
import com.smartfoxserver.v2.entities.Room;
import com.smartfoxserver.v2.entities.User;
import com.smartfoxserver.v2.entities.variables.RoomVariable;
import com.smartfoxserver.v2.extensions.ISFSExtension;
import com.tvd12.ezyfox.core.command.UpdateRoom;
import com.tvd12.ezyfox.core.entities.ApiBaseUser;
import com.tvd12.ezyfox.core.entities.ApiRoom;
import com.tvd12.ezyfox.core.structure.AgentClassUnwrapper;
import com.tvd12.ezyfox.sfs2x.content.impl.AppContextImpl;

/**
 * @see UpdateRoom
 * 
 * @author tavandung12
 * Created on May 31, 2016
 *
 */
public class UpdateRoomImpl extends BaseCommandImpl implements UpdateRoom {

	private ApiRoom agent;
	private ApiBaseUser user;
	private boolean toClient = true;
	private List<String> includedVars = new ArrayList<>();
    private List<String> excludedVars = new ArrayList<>();
	
	/**
	 * @param context
	 * @param api
	 * @param extension
	 */
	public UpdateRoomImpl(AppContextImpl context, ISFSApi api, ISFSExtension extension) {
		super(context, api, extension);
	}

	/**
	 * Execute update room variables
	 */
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
                roomAgentSerializer().serialize(unwrapper, agent);
        
        List<RoomVariable> answer = variables;
        if(includedVars.size() > 0) 
            answer = getVariables(variables, includedVars);
        answer.removeAll(getVariables(answer, excludedVars));
        
        //notify to client and serverself
        if(toClient) api.setRoomVariables(sfsUser, sfsRoom, answer);
        
        //only for server
        else sfsRoom.setVariables(answer);
        return agent;
		
	}
	
	private List<RoomVariable> getVariables(List<RoomVariable> variables, List<String> varnames) {
        List<RoomVariable> answer = new ArrayList<>();
        for(String ic : varnames) {
            RoomVariable var = null;
            for(RoomVariable v : variables) {
                if(v.getName().equals(ic)) {
                    var = v; break;
                }
            }
            if(var != null) answer.add(var);
        }
        return answer;
    }

	/**
	 * @see UpdateRoom#toClient(boolean)
	 */
	@Override
	public UpdateRoom toClient(boolean value) {
		this.toClient = value;
		return this;
	}

	/**
	 * @see UpdateRoom#room(ApiRoom)
	 */
	@Override
	public UpdateRoom room(ApiRoom room) {
		this.agent = room;
		return this;
	}

	/**
	 * @see UpdateRoom#user(ApiBaseUser)
	 */
	@Override
	public UpdateRoom user(ApiBaseUser user) {
		this.user = user;
		return this;
	}
	
	/* (non-Javadoc)
	 * @see com.tvd12.ezyfox.core.command.UpdateRoom#include(java.lang.String[])
	 */
    @Override
	public UpdateRoom include(String... varnames) {
	    includedVars.addAll(Arrays.asList(varnames));
	    return this;
	}
	
	/* (non-Javadoc)
	 * @see com.tvd12.ezyfox.core.command.UpdateRoom#exclude(java.lang.String[])
	 */
    @Override
	public UpdateRoom exclude(String... varnames) {
	    excludedVars.addAll(Arrays.asList(varnames));
	    return this;
	}
	
}
