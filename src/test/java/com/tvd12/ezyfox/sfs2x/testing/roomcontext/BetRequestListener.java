package com.tvd12.ezyfox.sfs2x.testing.roomcontext;

import com.tvd12.ezyfox.core.annotation.ClientRequestListener;
import com.tvd12.ezyfox.core.annotation.ClientResponseHandler;
import com.tvd12.ezyfox.core.content.AppContext;
import com.tvd12.ezyfox.sfs2x.testing.context.AppUser;
import com.tvd12.ezyfox.sfs2x.testing.context.PokerRoom;

import lombok.Data;

/**
 * @author tavandung12
 * Created on Aug 16, 2016
 *
 */
@Data
@ClientResponseHandler
@ClientRequestListener(command = "abc")
public class BetRequestListener {

    private int id;
    
    public void execute(AppContext context, PokerRoom user, AppUser room) {
        
    }
    
}
