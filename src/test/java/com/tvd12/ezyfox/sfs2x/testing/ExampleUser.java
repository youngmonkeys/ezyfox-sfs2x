package com.tvd12.ezyfox.sfs2x.testing;

import java.io.Serializable;
import java.util.List;

import com.tvd12.ezyfox.core.annotation.RequestParam;
import com.tvd12.ezyfox.core.annotation.UserAgent;
import com.tvd12.ezyfox.core.annotation.Variable;
import com.tvd12.ezyfox.core.annotation.VariableParam;
import com.tvd12.ezyfox.core.entities.ApiUser;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@SuppressWarnings("serial")
@Data
@UserAgent
@ToString
@EqualsAndHashCode(callSuper=false)
public class ExampleUser extends ApiUser implements Serializable {
	
	public ExampleUser() {}
	
	public ExampleUser(String name) {
		setName(name);
	}

	public void incrementMoney(Long addition) {
		this.money += addition;
	}
	
	@Variable(visible = true)
	private Long money = 123L;
	
	@Variable("fri")
	private List<ExampleUser> friends;

	@Variable("fstn")
	@RequestParam("fstn")
	@VariableParam("fstn")
	private String firstName = "Ta";
	
	@Variable("lstn")
	@RequestParam("lstn")
	@VariableParam("lstn")
	private String lastName = "Van Dung";
	
	@Variable
	private int age = 24;
	
}
