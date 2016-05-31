/**
 * 
 */
package com.tvd12.ezyfox.sfs2x.testing.responseparameterparser;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.tvd12.ezyfox.core.annotation.ResponseParam;
import com.tvd12.ezyfox.core.annotation.ResponseParams;
import com.tvd12.ezyfox.core.annotation.RoomAgent;
import com.tvd12.ezyfox.core.annotation.Variable;
import com.tvd12.ezyfox.core.model.ApiRoom;

import lombok.Getter;
import lombok.Setter;

/**
 * @author tavandung12
 *
 */
@RoomAgent
@ResponseParams
public class VideoPokerRoom extends ApiRoom {

    @Variable("1")
    @Setter @Getter
    private long jackpotMoney = 100000;
    
    private Map<Integer, BettingType> bettingTypes;
    
    public VideoPokerRoom() {
        bettingTypes = new HashMap<>();
        for(int i = 0 ; i < 3 ; i++) {
            bettingTypes.put(i, new BettingType(i, (i + 1) * 1000));
        }
    }
    
    @ResponseParam("1")
    public Collection<Integer> bettingTypIds() {
        return bettingTypes.keySet();
    }
    
}
