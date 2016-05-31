package com.tvd12.ezyfox.sfs2x.testing.context;

import com.tvd12.ezyfox.core.annotation.GameUser;
import com.tvd12.ezyfox.core.annotation.Variable;
import com.tvd12.ezyfox.core.model.ApiGameUser;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@GameUser
@EqualsAndHashCode(callSuper = false)
public class PokerUser extends ApiGameUser {

    @Variable("btm")
    private long bettedMoney;
    
}
