package com.tvd12.ezyfox.sfs2x.testing.roomcontext;

import com.tvd12.ezyfox.core.annotation.ClientRequestListener;
import com.tvd12.ezyfox.core.content.AppContext;
import com.tvd12.ezyfox.sfs2x.testing.context.AppUser;
import com.tvd12.ezyfox.sfs2x.testing.context.PokerRoom;

/**
 * @author tavandung12
 * Created on Aug 16, 2016
 *
 */
@ClientRequestListener(command = "abc")
public class BetRequestListener {

    public void execute(AppContext context, PokerRoom user, AppUser room) {
        
    }
    
}
