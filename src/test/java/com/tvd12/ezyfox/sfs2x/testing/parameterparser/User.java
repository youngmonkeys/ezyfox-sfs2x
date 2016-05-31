package com.tvd12.ezyfox.sfs2x.testing.parameterparser;

import java.util.List;

import com.tvd12.ezyfox.core.annotation.RequestParam;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class User {

	public User() {
	}

	public User(String name, String address) {
		this.name = name;
		this.address = address;
	}

	public User(String name, String address, User user) {
		this.name = name;
		this.address = address;
		this.user = user;
	}

	@RequestParam
	private String name;

	@RequestParam("adr")
	private String address;

	@RequestParam
	private User user;

	@RequestParam
	private List<User> users;

	@Override
	public boolean equals(Object obj) {
	    if(obj == null) return false;
		User other = (User) obj;
		return getName().equals(other.getName()) && getAddress().equals(other.getAddress());
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}
}
