package com.technivaaran.coe.services;

import java.util.List;
import java.util.Optional;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.technivaaran.coe.dtos.UserDto;
import com.technivaaran.coe.dtos.UserResponseDto;
import com.technivaaran.coe.entities.User;
import com.technivaaran.coe.exception.CoeException;
import com.technivaaran.coe.exception.UserNotFoundException;
import com.technivaaran.coe.mapper.UserMapper;
import com.technivaaran.coe.repositories.UserRepository;

@Service
public class UserService {

	//@Autowired
	private final UserRepository userRepository;

	//@Autowired
	private final UserMapper userMapper;

	public UserService(UserRepository userRepository, UserMapper userMapper) {
		this.userRepository = userRepository;
		this.userMapper = userMapper;
	}

	public User saveUser(UserDto userDto) {
		try {
			User user = userMapper.convertToEntity(userDto);
			return userRepository.save(user);

		} catch (DataIntegrityViolationException integrityViolationException) {
			throw new CoeException("User already exists with user name: " + userDto.getUserName());
		}
	}

	public List<User> getAllUsers() {

		return userRepository.findAll();
	}

	public User getUserById(long userId) {
		Optional<User> userOp = userRepository.findById(userId);

		if (userOp.isPresent()) {
			return userOp.get();
		} else {
			throw new CoeException("User not found for id : " + userId);
		}
	}

	public Optional<User> getUserOptionalByEmailAndPassword(String email, String password) {
		return userRepository.findUserByEmailAndPassword(email, password);
	}

	public UserResponseDto getUserByEmailAndPassword(String email, String password) throws UserNotFoundException{
		Optional<User> userOp = userRepository.findUserByEmailAndPassword(email, password);
		if (userOp.isPresent()) {
			return userMapper.convertToUserResponseDto(userOp.get());
		} else {
			throw new UserNotFoundException("Invalid User name or password provided");
		}
	}

	public User updateUserById(long userId, UserDto userDto) {
		Optional<User> userOp = userRepository.findById(userId);

		if (userOp.isPresent()) {
			User user = userOp.get();
			user.setPassword(userDto.getPassword());
			user.setStatus(StringUtils.hasLength(userDto.getStatus()) ? userDto.getStatus() : user.getStatus());

			return userRepository.save(user);
		} else {
			throw new CoeException("User not found for id : " + userId);
		}
	}

}
