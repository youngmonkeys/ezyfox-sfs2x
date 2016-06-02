package com.tvd12.ezyfox.sfs2x.command.impl;

import java.util.ArrayList;
import java.util.List;

import com.smartfoxserver.v2.api.ISFSApi;
import com.smartfoxserver.v2.entities.Room;
import com.smartfoxserver.v2.entities.User;
import com.smartfoxserver.v2.extensions.ISFSExtension;
import com.tvd12.ezyfox.core.command.FetchUserList;
import com.tvd12.ezyfox.core.config.APIKey;
import com.tvd12.ezyfox.core.model.ApiRoom;
import com.tvd12.ezyfox.core.model.ApiUser;
import com.tvd12.ezyfox.sfs2x.content.impl.AppContextImpl;

/**
 * @see FetchUserList
 * 
 * @author tavandung12
 * Created on May 27, 2016
 *
 */
public class FetchUserListImpl extends BaseCommandImpl implements FetchUserList {

    /**
     * @param context
     * @param api
     * @param extension
     */
    public FetchUserListImpl(AppContextImpl context, ISFSApi api, ISFSExtension extension) {
        super(context, api, extension);
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.FetchUserList#in(com.tvd12.ezyfox.core.model.ApiRoom)
     */
    @Override
    public List<ApiUser> in(ApiRoom room) {
        Room sfsRoom = CommandUtil.getSfsRoom(room, extension);
        List<ApiUser> answer = new ArrayList<>();
        if(sfsRoom == null)     return answer;
        List<User> sfsUsers = sfsRoom.getUserList();
        for(User sfsUser : sfsUsers) {
            if(sfsUser.containsProperty(APIKey.USER))
                answer.add((ApiUser) sfsUser.getProperty(APIKey.USER));
        }
        return answer;
        
    }

}
