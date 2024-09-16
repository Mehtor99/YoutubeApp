package org.mehtor.controller;

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
	
	public Optional<User> save (User user) {
		try{
			return userService.save(user);
		}catch(Exception e){
			System.out.println("Controller user save Hata!");
		}
		return Optional.empty();
	}
	
	public Optional<User> update (User user) {
		try{
			return userService.update(user);
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
	
	public List<User> findAll() {
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