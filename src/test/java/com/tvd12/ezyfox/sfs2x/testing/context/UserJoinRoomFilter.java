package com.tvd12.ezyfox.sfs2x.testing.context;

import com.tvd12.ezyfox.core.annotation.ClientRequestListener;
import com.tvd12.ezyfox.core.config.ClientRequest;
import com.tvd12.ezyfox.core.content.AppContext;

/**
 * @author tavandung12
 * Created on Jul 2, 2016
 *
 */
@ClientRequestListener(command = ClientRequest.JoinRoom)
public class UserJoinRoomFilter {

    public void execute(AppContext context, AppUser user) {
        throw new NullPointerException();
    }
    
}
