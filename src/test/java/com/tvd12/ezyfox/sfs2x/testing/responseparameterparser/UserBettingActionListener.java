package com.tvd12.ezyfox.sfs2x.testing.responseparameterparser;

import com.tvd12.ezyfox.core.annotation.ClientRequestListener;
import com.tvd12.ezyfox.core.annotation.ClientResponseHandler;
import com.tvd12.ezyfox.core.annotation.ResponseParam;

import lombok.Data;

@Data
@ClientResponseHandler(command = "result")
@ClientRequestListener(command = "betting")
public class UserBettingActionListener {

	@ResponseParam("msg")
	private String message = "hello";
	
	@ResponseParam
	private int result = 100;
	
	@ResponseParam
	private Value value = new Value();
	
	@ResponseParam
	byte[] xyz = {1, 2, 3};
	
	@ResponseParam
	int[] abc = {4, 5, 6};
	
}
