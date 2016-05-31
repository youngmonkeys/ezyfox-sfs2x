package com.tvd12.ezyfox.sfs2x.testing.context;

import com.tvd12.ezyfox.core.annotation.ClientRequestListener;
import com.tvd12.ezyfox.core.content.AppContext;

@ClientRequestListener(command = "bet")
public class BettingRequestListener2 {

    public void execute(AppContext context, AppUser user) {
        
    }
    
}
