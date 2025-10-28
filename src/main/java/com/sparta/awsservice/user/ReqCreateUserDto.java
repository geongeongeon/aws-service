package com.sparta.awsservice.user;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReqCreateUserDto implements Serializable {

	private String username;

	private String password;

}
