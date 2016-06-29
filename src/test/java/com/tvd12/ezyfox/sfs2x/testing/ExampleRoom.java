package com.tvd12.ezyfox.sfs2x.testing;

import com.tvd12.ezyfox.core.annotation.RoomAgent;
import com.tvd12.ezyfox.core.annotation.Variable;
import com.tvd12.ezyfox.core.model.ApiRoom;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@RoomAgent
@EqualsAndHashCode(callSuper=false)
public class ExampleRoom extends ApiRoom {
	
	@Variable(name="pgb", visible = true)
	private long piggybank = 10;

}
