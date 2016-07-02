package com.tvd12.ezyfox.sfs2x.testing.context;

import com.tvd12.ezyfox.core.annotation.ClientRequestListener;
import com.tvd12.ezyfox.core.config.ClientRequest;
import com.tvd12.ezyfox.core.content.AppContext;

/**
 * @author tavandung12
 * Created on Jul 2, 2016
 *
 */
@ClientRequestListener(command = ClientRequest.AddBuddy)
public class UserAddBuddyFilter {

    public void execute(AppContext context, AppUser user) {
        
    }
    
}
