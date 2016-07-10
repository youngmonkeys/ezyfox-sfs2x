package com.tvd12.ezyfox.sfs2x.command.impl;

import static com.tvd12.ezyfox.sfs2x.command.impl.CommandUtil.getApiRoomList;
import static com.tvd12.ezyfox.sfs2x.command.impl.CommandUtil.getSfsRoom;
import static com.tvd12.ezyfox.sfs2x.command.impl.CommandUtil.getSfsUser;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.smartfoxserver.v2.api.ISFSApi;
import com.smartfoxserver.v2.entities.Room;
import com.smartfoxserver.v2.entities.User;
import com.smartfoxserver.v2.entities.variables.UserVariable;
import com.smartfoxserver.v2.extensions.ISFSExtension;
import com.tvd12.ezyfox.core.command.UserInfo;
import com.tvd12.ezyfox.core.constants.APIKey;
import com.tvd12.ezyfox.core.entities.ApiBaseUser;
import com.tvd12.ezyfox.core.entities.ApiRoom;
import com.tvd12.ezyfox.core.entities.ApiZone;
import com.tvd12.ezyfox.sfs2x.content.impl.AppContextImpl;

/**
 * 
 * @see UserInfo
 * 
 * @author tavandung12
 * Created on Jun 9, 2016
 *
 */
public class UserInfoImpl extends BaseCommandImpl implements UserInfo {

    private User sfsUser;
    
    /**
     * @param context
     * @param api
     * @param extension
     */
    public UserInfoImpl(AppContextImpl context, ISFSApi api, ISFSExtension extension) {
        super(context, api, extension);
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.UpdateUserInfo#addCreatedRoom(com.tvd12.ezyfox.core.entities.ApiRoom)
     */
    @Override
    public void addCreatedRoom(ApiRoom room) {
        sfsUser.addCreatedRoom(getSfsRoom(room, extension));
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.UpdateUserInfo#addJoinedRoom(com.tvd12.ezyfox.core.entities.ApiRoom)
     */
    @Override
    public void addJoinedRoom(ApiRoom room) {
        sfsUser.addJoinedRoom(getSfsRoom(room, extension));
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.UpdateUserInfo#removeCreatedRoom(com.tvd12.ezyfox.core.entities.ApiRoom)
     */
    @Override
    public void removeCreatedRoom(ApiRoom room) {
        sfsUser.removeCreatedRoom(getSfsRoom(room, extension));
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.UpdateUserInfo#removeJoinedRoom(com.tvd12.ezyfox.core.entities.ApiRoom)
     */
    @Override
    public void removeJoinedRoom(ApiRoom room) {
        sfsUser.removeJoinedRoom(getSfsRoom(room, extension));
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.UpdateUserInfo#setBadWordsWarnings(int)
     */
    @Override
    public void setBadWordsWarnings(int count) {
        sfsUser.setBadWordsWarnings(count);
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.UpdateUserInfo#setBeingKicked(boolean)
     */
    @Override
    public void setBeingKicked(boolean flag) {
        sfsUser.setBeingKicked(flag);
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.UpdateUserInfo#setConnected(boolean)
     */
    @Override
    public void setConnected(boolean flag) {
        sfsUser.setConnected(flag);
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.UpdateUserInfo#setFloodWarnings(int)
     */
    @Override
    public void setFloodWarnings(int count) {
        sfsUser.setFloodWarnings(count);
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.UpdateUserInfo#setJoining(boolean)
     */
    @Override
    public void setJoining(boolean flag) {
        sfsUser.setJoining(flag);
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.UpdateUserInfo#setLastLoginTime(long)
     */
    @Override
    public void setLastLoginTime(long lastLoginTime) {
        sfsUser.setLastLoginTime(lastLoginTime);
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.UpdateUserInfo#setLastRequestTime(long)
     */
    @Override
    public void setLastRequestTime(long millis) {
        sfsUser.setLastRequestTime(millis);
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.UpdateUserInfo#setMaxAllowedVariables(int)
     */
    @Override
    public void setMaxAllowedVariables(int max) {
        sfsUser.setMaxAllowedVariables(max);
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.UpdateUserInfo#setPlayerId(int, com.tvd12.ezyfox.core.entities.ApiRoom)
     */
    @Override
    public void setPlayerId(int id, ApiRoom room) {
        sfsUser.setPlayerId(id, getSfsRoom(room, extension));
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.UpdateUserInfo#setPlayerId(int)
     */
    @Override
    public void setPlayerId(int id) {
        sfsUser.setPlayerId(id, sfsUser.getLastJoinedRoom());
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.UpdateUserInfo#setPrivilegeId(short)
     */
    @Override
    public void setPrivilegeId(short id) {
        sfsUser.setPrivilegeId(id);
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.UpdateUserInfo#setReconnectionSeconds(int)
     */
    @Override
    public void setReconnectionSeconds(int seconds) {
        sfsUser.setReconnectionSeconds(seconds);
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.UpdateUserInfo#subscribeGroup(java.lang.String)
     */
    @Override
    public void subscribeGroup(String groupId) {
        sfsUser.subscribeGroup(groupId);
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.UpdateUserInfo#unsubscribeGroup(java.lang.String)
     */
    @Override
    public void unsubscribeGroup(String groupId) {
        sfsUser.unsubscribeGroup(groupId);
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.UpdateUserInfo#updateLastRequestTime()
     */
    @Override
    public void updateLastRequestTime() {
        sfsUser.updateLastRequestTime();
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.FetchUserInfo#isBeingKicked()
     */
    @Override
    public boolean isBeingKicked() {
        return sfsUser.isBeingKicked();
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.FetchUserInfo#isConnected()
     */
    @Override
    public boolean isConnected() {
        return sfsUser.isConnected();
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.FetchUserInfo#isJoinedInRoom(com.tvd12.ezyfox.core.entities.ApiRoom)
     */
    @Override
    public boolean isJoinedInRoom(ApiRoom room) {
        return sfsUser.isJoinedInRoom(getSfsRoom(room, extension));
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.FetchUserInfo#isJoining()
     */
    @Override
    public boolean isJoining() {
        return sfsUser.isJoining();
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.FetchUserInfo#isLocal()
     */
    @Override
    public boolean isLocal() {
        return sfsUser.isLocal();
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.FetchUserInfo#isNPC()
     */
    @Override
    public boolean isNPC() {
        return sfsUser.isNpc();
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.FetchUserInfo#isPlayer()
     */
    @Override
    public boolean isPlayer() {
        return sfsUser.isPlayer();
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.FetchUserInfo#isPlayer(com.tvd12.ezyfox.core.entities.ApiRoom)
     */
    @Override
    public boolean isPlayer(ApiRoom room) {
        return sfsUser.isPlayer(getSfsRoom(room, extension));
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.FetchUserInfo#isSpectator()
     */
    @Override
    public boolean isSpectator() {
        return sfsUser.isSpectator();
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.FetchUserInfo#isSpectator(com.tvd12.ezyfox.core.entities.ApiRoom)
     */
    @Override
    public boolean isSpectator(ApiRoom room) {
        return sfsUser.isSpectator(getSfsRoom(room, extension));
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.FetchUserInfo#isSubscribedToGroup(java.lang.String)
     */
    @Override
    public boolean isSubscribedToGroup(String groupId) {
        return sfsUser.isSubscribedToGroup(groupId);
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.FetchUserInfo#isSuperUser()
     */
    @Override
    public boolean isSuperUser() {
        return sfsUser.isSuperUser();
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.FetchUserInfo#getPrivilegeId()
     */
    @Override
    public short getPrivilegeId() {
        return sfsUser.getPrivilegeId();
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.FetchUserInfo#getBadWordsWarnings()
     */
    @Override
    public int getBadWordsWarnings() {
        return sfsUser.getBadWordsWarnings();
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.FetchUserInfo#getCreatedRooms()
     */
    @Override
    public List<ApiRoom> getCreatedRooms() {
        return getApiRoomList(sfsUser.getCreatedRooms());
    }
    
    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.FetchUserInfo#getFloodWarnings()
     */
    @Override
    public int getFloodWarnings() {
        return sfsUser.getFloodWarnings();
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.FetchUserInfo#getJoinedRooms()
     */
    @Override
    public List<ApiRoom> getJoinedRooms() {
        return getApiRoomList(sfsUser.getJoinedRooms());
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.FetchUserInfo#getLastJoinedRoom()
     */
    @SuppressWarnings("unchecked")
    @Override
    public <T extends ApiRoom> T getLastJoinedRoom() {
        return (T)sfsUser.getLastJoinedRoom().getProperty(APIKey.ROOM);
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.FetchUserInfo#getLastRequestTime()
     */
    @Override
    public long getLastRequestTime() {
        return sfsUser.getLastRequestTime();
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.FetchUserInfo#getLoginTime()
     */
    @Override
    public long getLoginTime() {
        return sfsUser.getLoginTime();
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.FetchUserInfo#getMaxAllowedVariables()
     */
    @Override
    public int getMaxAllowedVariables() {
        return sfsUser.getMaxAllowedVariables();
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.FetchUserInfo#getOwnedRoomsCount()
     */
    @Override
    public int getOwnedRoomsCount() {
        return sfsUser.getOwnedRoomsCount();
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.FetchUserInfo#getPlayerId()
     */
    @Override
    public int getPlayerId() {
        return sfsUser.getPlayerId();
    }
    
    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.FetchUserInfo#getPlayerId(com.tvd12.ezyfox.core.entities.ApiRoom)
     */
    @Override
    public int getPlayerId(ApiRoom room) {
        return sfsUser.getPlayerId(getSfsRoom(room, extension));
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.FetchUserInfo#getPlayerIds()
     */
    @Override
    public Map<ApiRoom, Integer> getPlayerIds() {
        Map<ApiRoom, Integer> anwser = new HashMap<>();
        Map<Room, Integer> ids = sfsUser.getPlayerIds();
        for(Entry<Room, Integer> entry : ids.entrySet())
            anwser.put((ApiRoom) entry.getKey().getProperty(APIKey.ROOM), 
                    entry.getValue());
        return anwser;
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.FetchUserInfo#getReconnectionSeconds()
     */
    @Override
    public int getReconnectionSeconds() {
        return sfsUser.getReconnectionSeconds();
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.FetchUserInfo#getSubscribedGroups()
     */
    @Override
    public List<String> getSubscribedGroups() {
        return sfsUser.getSubscribedGroups();
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.FetchUserInfo#getVariablesCount()
     */
    @Override
    public int getVariablesCount() {
        return sfsUser.getVariablesCount();
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.FetchUserInfo#getZone()
     */
    @Override
    public ApiZone getZone() {
        return (ApiZone) sfsUser.getZone().getProperty(APIKey.ZONE);
    }
    
    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.UpdateUserInfo#removeAllVariables()
     */
    @Override
    public void removeAllVariables() {
        List<UserVariable> vars = sfsUser.getVariables();
        for(UserVariable var : vars) var.setNull();
        api.setUserVariables(sfsUser, vars);
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.UserInfo#user(com.tvd12.ezyfox.core.entities.ApiBaseUser)
     */
    @Override
    public UserInfo user(ApiBaseUser user) {
        sfsUser = getSfsUser(user, api);
        return this;
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.UserInfo#user(java.lang.String)
     */
    @Override
    public UserInfo user(String username) {
        sfsUser = getSfsUser(username, api);
        return this;
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.UserInfo#user(int)
     */
    @Override
    public UserInfo user(int userId) {
        sfsUser = api.getUserById(userId);
        return this;
    }

    
}
