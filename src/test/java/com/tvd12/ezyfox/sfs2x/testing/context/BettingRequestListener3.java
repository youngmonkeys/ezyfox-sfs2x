package com.tvd12.ezyfox.sfs2x.testing.context;

import com.tvd12.ezyfox.core.annotation.ClientRequestListener;
import com.tvd12.ezyfox.core.annotation.ClientResponseHandler;
import com.tvd12.ezyfox.core.content.AppContext;

@ClientResponseHandler
@ClientRequestListener(command = "bet")
public class BettingRequestListener3 {

    public void execute(AppContext context, AppUser user) {
        
    }
    
}
