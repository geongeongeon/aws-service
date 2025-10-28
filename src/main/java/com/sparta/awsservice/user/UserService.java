package com.sparta.awsservice.user;

import java.util.List;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

	private final UserRepository userRepository;

	@Transactional
	@CacheEvict(cacheNames = "users", allEntries = true)
	public void createUser(ReqCreateUserDto requestDto) {
		userRepository.save(User.builder()
			.username(requestDto.getUsername())
			.password(requestDto.getPassword())
			.build());
	}

	@Transactional(readOnly = true)
	@Cacheable(cacheNames = "users", key = "'all'")
	public List<ResReadUserDto> getAllUsers() {
		return userRepository.findAll().stream()
			.map(ResReadUserDto::from)
			.toList();
	}

}
