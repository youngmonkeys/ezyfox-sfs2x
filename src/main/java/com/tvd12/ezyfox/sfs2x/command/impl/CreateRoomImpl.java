package com.tvd12.ezyfox.sfs2x.command.impl;

import com.smartfoxserver.v2.api.CreateRoomSettings;
import com.smartfoxserver.v2.api.ISFSApi;
import com.smartfoxserver.v2.entities.Room;
import com.smartfoxserver.v2.entities.SFSRoomRemoveMode;
import com.smartfoxserver.v2.exceptions.SFSCreateRoomException;
import com.smartfoxserver.v2.extensions.ISFSExtension;
import com.tvd12.ezyfox.core.annotation.RoomAgent;
import com.tvd12.ezyfox.core.command.CreateRoom;
import com.tvd12.ezyfox.core.command.RoomInfo;
import com.tvd12.ezyfox.core.constants.APIKey;
import com.tvd12.ezyfox.core.entities.ApiRoom;
import com.tvd12.ezyfox.core.structure.AgentClass;
import com.tvd12.ezyfox.sfs2x.content.impl.AppContextImpl;

/**
 * @see CreateRoom
 * 
 * @author tavandung12
 * Created on May 31, 2016
 *
 */
public class CreateRoomImpl extends BaseCommandImpl implements CreateRoom {

    /**
     * @param context
     * @param api
     * @param extension
     */
	public CreateRoomImpl(AppContextImpl context, ISFSApi api, ISFSExtension extension) {
		super(context, api, extension);
	}

	private ApiRoom[] agents;
	
	/**
	 * @see CreateRoom#agents(ApiRoom...)
	 */
    @Override
	public CreateRoom agents(ApiRoom... agents) {
		this.agents = agents;
		return this;
	}
	
    /**
     * Validate room agent class to check whether room agent be annotated with {@code RoomAgent}
     * annotation or not
     * 
     * @param agent room agent object
     * @return structure of room agent class
     * @throws IllegalStateException when agent class be not annotated with {@code RoomAgent}
     */
    private AgentClass validateRoomAgentClass(ApiRoom agent) {
	    AgentClass roomAgentClass = context
	            .getRoomAgentClasses().get(agent.getClass());
	    if(roomAgentClass == null)
	        throw new IllegalStateException("You mus annotate class " + agent.getClass()
	                + " with @" + RoomAgent.class.getSimpleName());
	    return roomAgentClass;
	}
	
    /**
     * Execute to create list of room
     */
    @SuppressWarnings("unchecked")
    @Override
    public Boolean execute() {
        for(int i = 0 ; i < agents.length ; i++) {
            createRoom(i);
        }
        return Boolean.TRUE;
    }
	
    /**
     * Create a room at index
     * 
     * @param index index
     */
    private void createRoom(int index) {
        ApiRoom agent = agents[index];
        try {
            CreateRoomSettings settings = createRoomSettings(agent);
            Room room = api.createRoom(extension.getParentZone(), settings, null);
            room.setProperty(APIKey.ROOM, agent);
            agent.setId(room.getId());
            agent.setPasswordProtected(room.isPasswordProtected());
            agent.setCommand(context.command(RoomInfo.class).room(room.getId()));
        }
        catch (SFSCreateRoomException e) {
            throw new IllegalStateException("Can not create room " + agent.getName(), e);
        }
    }
	
    /**
     * Create smartfox CreateRoomSettings object
     * 
     * @param agent room agent object
     * @return CreateRoomSettings object
     */
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
