package com.tvd12.ezyfox.sfs2x.testing.context;

import com.tvd12.ezyfox.core.annotation.ClientRequestListener;
import com.tvd12.ezyfox.core.annotation.ClientResponseHandler;
import com.tvd12.ezyfox.core.content.AppContext;

import lombok.Data;

@Data
@ClientResponseHandler
@ClientRequestListener(command = "bet", priority = -1000)
public class BettingRequestListener {
    
    private int id;

    public void execute(AppContext context, PokerUser user) {
        
    }
    
}
