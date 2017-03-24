package com.tvd12.ezyfox.sfs2x.entities.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.smartfoxserver.v2.entities.Room;
import com.smartfoxserver.v2.entities.User;
import com.smartfoxserver.v2.entities.Zone;
import com.tvd12.ezyfox.core.constants.APIKey;
import com.tvd12.ezyfox.core.entities.ApiRoom;
import com.tvd12.ezyfox.core.entities.ApiUser;
import com.tvd12.ezyfox.core.entities.ApiZone;

/**
 * This a proxy class
 * 
 * @see ApiZone
 * 
 * @author tavandung12
 * Created on Jun 1, 2016
 *
 */
public class ApiZoneImpl implements ApiZone {

    // smartfox zone object
	private Zone sfsZone;
	
	public ApiZoneImpl(Zone sfsZone) {
		this.sfsZone = sfsZone;
	}

	/**
	 * @see ApiZone#getId()
	 */
	@Override
	public int getId() {
		return getZone().getId();
	}

	/**
	 * @see ApiZone#getMaxAllowedRooms()
	 */
	@Override
	public int getMaxAllowedRooms() {
		return getZone().getMaxAllowedRooms();
	}

	/**
	 * @see ApiZone#getMaxAllowedUsers()
	 */
	@Override
	public int getMaxAllowedUsers() {
		return getZone().getMaxAllowedUsers();
	}

	/**
	 * @see ApiZone#getMaxRoomNameChars()
	 */
	@Override
	public int getMaxRoomNameChars() {
		return getZone().getMaxRoomNameChars();
	}

	/**
	 * @see ApiZone#getMaxRoomsCreatedPerUserLimit()
	 */
	@Override
	public int getMaxRoomsCreatedPerUserLimit() {
		return getZone().getMaxRoomsCreatedPerUserLimit();
	}

	/**
	 * @see ApiZone#getMaxRoomVariablesAllowed()
	 */
	@Override
	public int getMaxRoomVariablesAllowed() {
		return getZone().getMaxRoomVariablesAllowed();
	}

	/**
	 * @see ApiZone#getMaxUserIdleTime()
	 */
	@Override
	public int getMaxUserIdleTime() {
		return getZone().getMaxUserIdleTime();
	}

	/**
	 * @see ApiZone#getMaxUserVariablesAllowed()
	 */
	@Override
	public int getMaxUserVariablesAllowed() {
		return getZone().getMaxUserVariablesAllowed();
	}

	/**
	 * @see ApiZone#getMinRoomNameChars()
	 */
	@Override
	public int getMinRoomNameChars() {
		return getZone().getMinRoomNameChars();
	}

	/**
	 * @see ApiZone#getTotalRoomCount()
	 */
	@Override
	public int getTotalRoomCount() {
		return getZone().getTotalRoomCount();
	}

	/**
	 * @see ApiZone#getUserCount()
	 */
	@Override
	public int getUserCount() {
		return getZone().getUserCount();
	}

	/**
	 * @see ApiZone#getUserReconnectionSeconds()
	 */
	@Override
	public int getUserReconnectionSeconds() {
		return getZone().getUserReconnectionSeconds();
	}

	/**
	 * @see ApiZone#isActive()
	 */
	@Override
	public boolean isActive() {
		return getZone().isActive();
	}

	/**
	 * @see ApiZone#getName()
	 */
	@Override
	public String getName() {
		return getZone().getName();
	}

	/* (non-Javadoc)
	 * @see com.tvd12.ezyfox.core.entities.ApiZone#getUserById(int)
	 */
	@SuppressWarnings("unchecked")
    @Override
	public <T extends ApiUser> T getUserById(int id) {
	    User sfsUser = getZone().getUserById(id);
	    if(sfsUser != null && sfsUser.containsProperty(APIKey.USER))
	        return (T) sfsUser.getProperty(APIKey.USER);
	    return null;
	}
	
	/* (non-Javadoc)
	 * @see com.tvd12.ezyfox.core.entities.ApiZone#getUserByName(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
    @Override
	public <T extends ApiUser> T getUserByName(String username) {
	    User sfsUser = getZone().getUserByName(username);
        if(sfsUser != null && sfsUser.containsProperty(APIKey.USER))
            return (T) sfsUser.getProperty(APIKey.USER);
        return null;
	}
	
	/* (non-Javadoc)
	 * @see com.tvd12.ezyfox.core.entities.ApiZone#getUsersInGroup(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
    @Override
	public <T extends ApiUser> List<T> getUsersInGroup(String groupId) {
	    Collection<User> sfsUsers = getZone().getUsersInGroup(groupId);
	    List<T> anwser = new ArrayList<>();
	    for(User sfsUser : sfsUsers)
	        if(sfsUser.containsProperty(APIKey.USER))
	            anwser.add((T) sfsUser.getProperty(APIKey.USER));
	    return anwser;
	}
	
	/* (non-Javadoc)
	 * @see com.tvd12.ezyfox.core.entities.ApiZone#getUserList()
	 */
	@SuppressWarnings("unchecked")
    @Override
	public <T extends ApiUser> List<T> getUserList() {
	    Collection<User> sfsUsers = getZone().getUserList();
        List<T> anwser = new ArrayList<>();
        for(User sfsUser : sfsUsers)
            if(sfsUser.containsProperty(APIKey.USER))
                anwser.add((T) sfsUser.getProperty(APIKey.USER));
        return anwser;
	}
	
	/* (non-Javadoc)
	 * @see com.tvd12.ezyfox.core.entities.ApiZone#isNPC(java.lang.String)
	 */
	@Override
	public boolean isNPC(String username) {
	    User sfsUser = getZone().getUserByName(username);
        if(sfsUser != null)
            return sfsUser.isNpc();
        return false;
	}
	
	/* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.entities.ApiZone#getRoomById(int)
     */
    @SuppressWarnings("unchecked")
    @Override
    public <T extends ApiRoom> T getRoomById(int id) {
        Room sfsRoom = getZone().getRoomById(id);
        if(sfsRoom != null && sfsRoom.containsProperty(APIKey.ROOM))
            return (T) sfsRoom.getProperty(APIKey.ROOM);
        return null;
    }
	
	/* (non-Javadoc)
	 * @see com.tvd12.ezyfox.core.entities.ApiZone#getRoomByName(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
    @Override
	public <T extends ApiRoom> T getRoomByName(String name) {
	    Room sfsRoom = getZone().getRoomByName(name);
	    if(sfsRoom != null && sfsRoom.containsProperty(APIKey.ROOM))
	        return (T) sfsRoom.getProperty(APIKey.ROOM);
	    return null;
	}
	
	/* (non-Javadoc)
	 * @see com.tvd12.ezyfox.core.entities.ApiZone#getRoomsInGroup(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
    @Override
	public <T extends ApiRoom> List<T> getRoomsInGroup(String groupId) {
	    List<Room> sfsRooms = getZone().getRoomListFromGroup(groupId);
	    List<T> answer = new ArrayList<>();
	    for(Room sfsRoom : sfsRooms) {
	        if(sfsRoom.containsProperty(APIKey.ROOM))
	            answer.add((T) sfsRoom.getProperty(APIKey.ROOM));
	    }
	    return answer;
	}
	
	/* (non-Javadoc)
	 * @see com.tvd12.ezyfox.core.entities.ApiZone#getRoomList()
	 */
	@SuppressWarnings("unchecked")
    @Override
	public <T extends ApiRoom> List<T> getRoomList() {
	    List<Room> sfsRooms = getZone().getRoomList();
        List<T> answer = new ArrayList<>();
        for(Room sfsRoom : sfsRooms) {
            if(sfsRoom.containsProperty(APIKey.ROOM))
                answer.add((T) sfsRoom.getProperty(APIKey.ROOM));
        }
        return answer;
	}
	
	/* (non-Javadoc)
	 * @see com.tvd12.ezyfox.core.entities.ApiProperties#setProperty(java.lang.Object, java.lang.Object)
	 */
	@Override
	public void setProperty(Object key, Object value) {
	    if(key.equals(APIKey.ZONE))    
	    	return;
	    getZone().setProperty(key, value);
	}
	
	/* (non-Javadoc)
	 * @see com.tvd12.ezyfox.core.entities.ApiProperties#getProperty(java.lang.Object)
	 */
	@SuppressWarnings("unchecked")
    @Override
	public <T> T getProperty(Object key) {
	    return (T) getZone().getProperty(key);
	}
	
	/* (non-Javadoc)
	 * @see com.tvd12.ezyfox.core.entities.ApiProperties#getProperty(java.lang.Object, java.lang.Class)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <T> T getProperty(Object key, Class<T> clazz) {
	    return (T)getProperty(key);
	}
	
	/* (non-Javadoc)
	 * @see com.tvd12.ezyfox.core.entities.ApiProperties#removeProperty(java.lang.Object)
	 */
	@Override
	public void removeProperty(Object key) {
	    if(key.equals(APIKey.ZONE))    
	    	return;
	    getZone().removeProperty(key);
	}
	
	protected Zone getZone() {
		return sfsZone;
	}
}
