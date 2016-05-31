package com.tvd12.ezyfox.sfs2x.testing.responseparameterparser;

import com.tvd12.ezyfox.core.annotation.ResponseParam;

import lombok.Data;

@Data
public class Value {

	public Value() {
		chips = new Chip[3];
		nexts = new Value[3];
		for(int i = 0 ; i < 3 ; i++) {
			chips[i] = new Chip();
			chips[i].setMoney(10 + i);
			nexts[i] = new Value(10 + i);
		}
	}
	
	public Value(int value) {
		this.value = value;
	}
	
	@ResponseParam
	private int value = 100;
	
	@ResponseParam
	private Chip chips[];
	
	@ResponseParam
	private Value nexts[];
	
	@ResponseParam
	private Integer noValue;
	
}
