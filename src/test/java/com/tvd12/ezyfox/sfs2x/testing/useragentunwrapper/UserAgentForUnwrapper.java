package com.tvd12.ezyfox.sfs2x.testing.useragentunwrapper;

import com.tvd12.ezyfox.core.annotation.Variable;

import lombok.Data;

@Data
public class UserAgentForUnwrapper {

	@Variable(name = "nam")
	private String name = "dzung";
	
	@Variable("age")
	private int age = 123;
	
	@Variable
	private GirlFriend girlFriend = new GirlFriend();
}
