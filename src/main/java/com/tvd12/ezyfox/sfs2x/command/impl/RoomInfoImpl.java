package com.tvd12.ezyfox.sfs2x.command.impl;

import java.util.List;

import com.smartfoxserver.v2.api.ISFSApi;
import com.smartfoxserver.v2.entities.Room;
import com.smartfoxserver.v2.entities.variables.RoomVariable;
import com.smartfoxserver.v2.exceptions.SFSJoinRoomException;
import com.smartfoxserver.v2.exceptions.SFSRoomException;
import com.smartfoxserver.v2.extensions.ISFSExtension;
import com.tvd12.ezyfox.core.command.RoomInfo;
import com.tvd12.ezyfox.core.constants.APIKey;
import com.tvd12.ezyfox.core.entities.ApiBaseUser;
import com.tvd12.ezyfox.core.entities.ApiGameUser;
import com.tvd12.ezyfox.core.entities.ApiRoom;
import com.tvd12.ezyfox.core.entities.ApiUser;
import com.tvd12.ezyfox.core.entities.ApiZone;
import com.tvd12.ezyfox.sfs2x.content.impl.AppContextImpl;

/**
 * @see RoomInfo
 * 
 * @author tavandung12
 * Created on Jun 30, 2016
 *
 */
public class RoomInfoImpl extends BaseCommandImpl implements RoomInfo {

    private Room room;
    private ApiRoom apiRoom;
    
    /**
     * @param context the context
     * @param api the api
     * @param extension the extension
     */
    public RoomInfoImpl(AppContextImpl context, ISFSApi api, ISFSExtension extension) {
        super(context, api, extension);
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.FetchRoomInfo#containsUser(java.lang.String)
     */
    @Override
    public boolean containsUser(String username) {
        return room.containsUser(username);
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.FetchRoomInfo#containsUser(com.tvd12.ezyfox.core.entities.ApiBaseUser)
     */
    @Override
    public boolean containsUser(ApiBaseUser user) {
        return room.containsUser(user.getName());
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.FetchRoomInfo#containsVariable(java.lang.String)
     */
    @Override
    public boolean containsVariable(String varname) {
        return room.containsVariable(varname);
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.FetchRoomInfo#getLifeTime()
     */
    @Override
    public long getLifeTime() {
        return room.getLifeTime();
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.FetchRoomInfo#getSpectatorsList()
     */
    @Override
    public <T extends ApiUser> List<T> getSpectatorsList() {
        return CommandUtil.getApiUserList(room.getSpectatorsList());
    }
    
    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.FetchRoomInfo#getSpectatorsList(java.lang.Class)
     */
    @Override
    public <T extends ApiGameUser> List<T> getSpectatorsList(Class<?> clazz) {
        return CommandUtil.getApiGameUserList(room.getSpectatorsList(), clazz);
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.FetchRoomInfo#getPlayersList()
     */
    @Override
    public <T extends ApiUser> List<T> getPlayersList() {
        return CommandUtil.getApiUserList(room.getPlayersList());
    }
    
    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.FetchRoomInfo#getPlayersList(java.lang.Class)
     */
    @Override
    public <T extends ApiGameUser> List<T> getPlayersList(Class<?> clazz) {
        return CommandUtil.getApiGameUserList(room.getPlayersList(), clazz);
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.FetchRoomInfo#getUserList()
     */
    @Override
    public <T extends ApiUser> List<T> getUserList() {
        return CommandUtil.getApiUserList(room.getUserList());
    }
    
    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.FetchRoomInfo#getUserList(java.lang.Class)
     */
    @Override
    public <T extends ApiGameUser> List<T> getUserList(Class<?> clazz) {
        return CommandUtil.getApiGameUserList(room.getUserList(), clazz);
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.FetchRoomInfo#getVariablesCount()
     */
    @Override
    public int getVariablesCount() {
        return room.getVariablesCount();
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.FetchRoomInfo#getZone()
     */
    @Override
    public ApiZone getZone() {
        return (ApiZone) room.getZone().getProperty(APIKey.ZONE);
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.FetchRoomInfo#isEmpty()
     */
    @Override
    public boolean isEmpty() {
        return room.isEmpty();
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.FetchRoomInfo#isFull()
     */
    @Override
    public boolean isFull() {
        return room.isFull();
    }
    
    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.FetchRoomInfo#isActive()
     */
    @Override
    public boolean isActive() {
        return room.isActive();
    }
    
    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.FetchRoomInfo#isPasswordProtected()
     */
    @Override
    public boolean isPasswordProtected() {
        return room.isPasswordProtected();
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.UpdateRoomInfo#addUser(com.tvd12.ezyfox.core.entities.ApiBaseUser)
     */
    @Override
    public void addUser(ApiBaseUser user) {
        try {
            room.addUser(CommandUtil.getSfsUser(user, api));
        } catch (SFSJoinRoomException e) {
            throw new IllegalStateException(e);
        }
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.UpdateRoomInfo#addUser(com.tvd12.ezyfox.core.entities.ApiBaseUser, boolean)
     */
    @Override
    public void addUser(ApiBaseUser user, boolean asSpectator) {
        try {
            room.addUser(CommandUtil.getSfsUser(user, api), asSpectator);
        } catch (SFSJoinRoomException e) {
            throw new IllegalStateException(e);
        }
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.UpdateRoomInfo#destroy()
     */
    @Override
    public void destroy() {
        room.destroy();
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.UpdateRoomInfo#removeUser(com.tvd12.ezyfox.core.entities.ApiBaseUser)
     */
    @Override
    public void removeUser(ApiBaseUser user) {
        room.removeUser(CommandUtil.getSfsUser(user, api));
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.UpdateRoomInfo#removeVariable(java.lang.String)
     */
    @Override
    public void removeVariable(String varname) {
        room.removeVariable(varname);
    }
    
    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.UpdateRoomInfo#removeAllVariables()
     */
    @Override
    public void removeAllVariables() {
        List<RoomVariable> vars = room.getVariables();
        for(RoomVariable var : vars) var.setNull();
        api.setRoomVariables(null, room, vars, true, true, false);
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.UpdateRoomInfo#setActive(boolean)
     */
    @Override
    public void setActive(boolean flag) {
        room.setActive(flag);
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.UpdateRoomInfo#setCapacity(int, int)
     */
    @Override
    public void setCapacity(int maxUser, int maxSpectators) {
        room.setCapacity(maxUser, maxSpectators);
        apiRoom.setMaxUsers(maxUser);
        apiRoom.setMaxSpectators(maxSpectators);
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.UpdateRoomInfo#setDynamic(boolean)
     */
    @Override
    public void setDynamic(boolean dynamic) {
        room.setDynamic(dynamic);
        apiRoom.setDynamic(dynamic);
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.UpdateRoomInfo#setGame(boolean)
     */
    @Override
    public void setGame(boolean game) {
        room.setGame(game);
        apiRoom.setGame(game);
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.UpdateRoomInfo#setGroupId(java.lang.String)
     */
    @Override
    public void setGroupId(String groupId) {
        room.setGroupId(groupId);
        apiRoom.setGroupdId(groupId);
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.UpdateRoomInfo#setHidden(boolean)
     */
    @Override
    public void setHidden(boolean hidden) {
        room.setHidden(hidden);
        apiRoom.setHidden(hidden);
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.UpdateRoomInfo#setMaxRoomVariablesAllowed(int)
     */
    @Override
    public void setMaxRoomVariablesAllowed(int max) {
        room.setMaxRoomVariablesAllowed(max);
        apiRoom.setMaxRoomVariablesAllowed(max);
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.UpdateRoomInfo#setMaxSpectators(int)
     */
    @Override
    public void setMaxSpectators(int maxSpectators) {
        room.setMaxSpectators(maxSpectators);
        apiRoom.setMaxSpectators(maxSpectators);
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.UpdateRoomInfo#setMaxUsers(int)
     */
    @Override
    public void setMaxUsers(int maxUsers) {
        room.setMaxUsers(maxUsers);
        apiRoom.setMaxUsers(maxUsers);
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.UpdateRoomInfo#setName(java.lang.String)
     */
    @Override
    public void setName(String name) {
        room.setName(name);
        apiRoom.setName(name);
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.UpdateRoomInfo#setOwner(com.tvd12.ezyfox.core.entities.ApiBaseUser)
     */
    @Override
    public void setOwner(ApiBaseUser owner) {
        room.setOwner(CommandUtil.getSfsUser(owner, api));
        apiRoom.setOwner(owner);
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.UpdateRoomInfo#setPassword(java.lang.String)
     */
    @Override
    public void setPassword(String password) {
        room.setPassword(password);
        apiRoom.setPassword(password);
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.UpdateRoomInfo#setUseWordsFilter(boolean)
     */
    @Override
    public void setUseWordsFilter(boolean useWordsFilter) {
        room.setUseWordsFilter(useWordsFilter);
        apiRoom.setUseWordsFilter(useWordsFilter);
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.UpdateRoomInfo#switchPlayerToSpectator(com.tvd12.ezyfox.core.entities.ApiBaseUser)
     */
    @Override
    public void switchPlayerToSpectator(ApiBaseUser user) {
        try {
            room.switchPlayerToSpectator(CommandUtil.getSfsUser(user, api));
        } catch (SFSRoomException e) {
            throw new IllegalStateException(e);
        }
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.UpdateRoomInfo#switchSpectatorToPlayer(com.tvd12.ezyfox.core.entities.ApiBaseUser)
     */
    @Override
    public void switchSpectatorToPlayer(ApiBaseUser user) {
        try {
            room.switchSpectatorToPlayer(CommandUtil.getSfsUser(user, api));
        } catch (SFSRoomException e) {
            throw new IllegalStateException(e);
        }
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.RoomInfo#room(com.tvd12.ezyfox.core.entities.ApiRoom)
     */
    @Override
    public RoomInfo room(ApiRoom room) {
        this.apiRoom = room;
        this.room = CommandUtil.getSfsRoom(room, extension);
        return this;
    }
    
    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.RoomInfo#room(int)
     */
    @Override
    public RoomInfo room(int id) {
        this.room = extension.getParentZone().getRoomById(id);
        return this;
    }
    
    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.RoomInfo#room(java.lang.String)
     */
    @Override
    public RoomInfo room(String name) {
        this.room = CommandUtil.getSfsRoom(name, extension);
        return this;
    }

    
}
