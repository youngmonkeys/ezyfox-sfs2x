package com.tvd12.ezyfox.sfs2x.command.impl;

import com.smartfoxserver.v2.api.CreateRoomSettings;
import com.smartfoxserver.v2.api.ISFSApi;
import com.smartfoxserver.v2.entities.Room;
import com.smartfoxserver.v2.entities.SFSRoomRemoveMode;
import com.smartfoxserver.v2.exceptions.SFSCreateRoomException;
import com.smartfoxserver.v2.extensions.ISFSExtension;
import com.tvd12.ezyfox.core.annotation.RoomAgent;
import com.tvd12.ezyfox.core.command.CreateRoom;
import com.tvd12.ezyfox.core.config.APIKey;
import com.tvd12.ezyfox.core.model.ApiRoom;
import com.tvd12.ezyfox.core.structure.AgentClass;
import com.tvd12.ezyfox.sfs2x.content.impl.AppContextImpl;

public class CreateRoomImpl extends BaseCommandImpl implements CreateRoom {

	public CreateRoomImpl(AppContextImpl context, ISFSApi api, ISFSExtension extension) {
		super(context, api, extension);
	}

	private ApiRoom[] agents;
	
    @Override
	public CreateRoom agents(ApiRoom... agents) {
		this.agents = agents;
		return this;
	}
	
    private AgentClass validateRoomAgentClass(ApiRoom agent) {
	    AgentClass roomAgentClass = context
	            .getRoomAgentClasses().get(agent.getClass());
	    if(roomAgentClass == null)
	        throw new RuntimeException("You mus annotate class " + agent.getClass()
	                + " with @" + RoomAgent.class.getSimpleName());
	    return roomAgentClass;
	}
	
    @SuppressWarnings("unchecked")
    public Boolean execute() {
        for(int i = 0 ; i < agents.length ; i++) {
            createRoom(i);
        }
        return Boolean.TRUE;
    }
	
    private void createRoom(int index) {
        ApiRoom agent = agents[index];
        try {
            CreateRoomSettings settings = createRoomSettings(agent);
            Room room = api.createRoom(extension.getParentZone(), settings, null);
            room.setProperty(APIKey.ROOM, agent);
            agent.setId(room.getId());
            agent.setPasswordProtected(room.isPasswordProtected());
        }
        catch (SFSCreateRoomException e) {
            throw new IllegalStateException("Can not create room " + agent.getName(), e);
        }
    }
	
    private CreateRoomSettings createRoomSettings(ApiRoom agent) {
        validateRoomAgentClass(agent);
	    CreateRoomSettings settings = new CreateRoomSettings();
        settings.setName(agent.getName());
        settings.setPassword(agent.getPassword());
        settings.setDynamic(agent.isDynamic());
        settings.setGame(agent.isGame());
        settings.setHidden(agent.isHidden());
        settings.setMaxSpectators(agent.getMaxSpectators());
        settings.setMaxUsers(agent.getMaxUsers());
        settings.setMaxVariablesAllowed(agent.getMaxRoomVariablesAllowed());
        settings.setRoomProperties(agent.getProperties());
        settings.setGroupId(agent.getGroupdId());
        settings.setUseWordsFilter(agent.isUseWordsFilter());
        settings.setAutoRemoveMode(SFSRoomRemoveMode.fromString(agent.getRemoveMode().name()));
        return settings;
	}
    
}
