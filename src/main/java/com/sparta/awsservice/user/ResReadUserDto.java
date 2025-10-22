package com.sparta.awsservice.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResReadUserDto {

	private Long id;

	private String username;

	private String password;

	private ResReadUserDto(User user) {
		this.id = user.getId();
		this.username = user.getUsername();
		this.password = user.getPassword();
	}

	public static ResReadUserDto from(User user) {
		return new ResReadUserDto(user);
	}

}
