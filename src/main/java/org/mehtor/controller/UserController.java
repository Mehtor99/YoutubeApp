package org.mehtor.controller;

import org.mehtor.dto.request.UserSaveRequestDTO;
import org.mehtor.dto.request.UserUpdateRequestDTO;
import org.mehtor.dto.response.UserResponseDTO;
import org.mehtor.entity.User;
import org.mehtor.service.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserController {
	private final UserService userService;

	public UserController() {
		this.userService = new UserService();
	}

	public Optional<UserResponseDTO> save (UserSaveRequestDTO userSaveRequestDTO) {
		try{
			return userService.save(userSaveRequestDTO);
		}catch(Exception e){
			System.out.println("Controller user save Hata!");
		}
		return Optional.empty();
	}

	public Optional<UserResponseDTO> update (UserUpdateRequestDTO userUpdateRequestDTO) {
		try{
			return userService.update(userUpdateRequestDTO);
		}catch(Exception e){
			System.out.println("Controller user  Update Hata!");
		}
		return Optional.empty();
	}

	public boolean delete (Long id) {
		try{
			return userService.delete(id);
		}catch(Exception e){
			System.out.println("Controller user Delete Hata!");
		}
		return false;
	}

	public List<UserResponseDTO> findAll() {
		try{
			return userService.findAll();
		}catch(Exception e){
			System.out.println("Controller user FindAll Hata!");
		}
		return new ArrayList<>();
	}

	public Optional<User> findById(Long id) {
		Optional<User> user = userService.findById(id);
		user.ifPresentOrElse(
				v -> System.out.println("Controller user bulundu: "+v.getId()+v.getName()),
		        () -> System.out.println("Controller boyle bir user bulunamadı.")
		);
		return user;
}
}