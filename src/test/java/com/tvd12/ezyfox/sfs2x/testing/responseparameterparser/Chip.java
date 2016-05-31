package com.tvd12.ezyfox.sfs2x.testing.responseparameterparser;

import com.tvd12.ezyfox.core.annotation.ResponseParam;

import lombok.Data;

@Data
public class Chip {

	@ResponseParam
	private int money = 456;
	
}
