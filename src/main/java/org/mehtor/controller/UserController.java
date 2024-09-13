package org.mehtor.controller;

import org.mehtor.entity.User;
import org.mehtor.service.UserService;

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
			System.out.println("Controller Hata!");
		}
		return Optional.empty();
	}
	
	public Optional<User> update (User user) {
		try{
			return userService.update(user);
		}catch(Exception e){
			System.out.println("Controller Update Hata!");
		}
		return Optional.empty();
	}
	
	public boolean delete (Long id) {
		try{
			return userService.delete(id);
		}catch(Exception e){
			System.out.println("Controller Delete Hata!");
		}
		return false;
	}
	
	public List<User> findAll() {
		try{
			return userService.findAll();
		}catch(Exception e){
			System.out.println("Controller FindAll Hata!");
		}
		return null;
	}
	
	public Optional<User> findById(Long id) {
		try{
			return userService.findById(id);
		}catch(Exception e){
			System.out.println("Controller FindById Hata!");
		}
		return Optional.empty();
	}
}