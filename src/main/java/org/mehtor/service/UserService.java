package org.mehtor.service;

import org.mehtor.entity.User;
import org.mehtor.repository.UserRepository;
import org.mehtor.utility.ICRUDService;

import java.util.List;
import java.util.Optional;

public class UserService implements ICRUDService<User> {
	
	private final UserRepository userRepository;
	
	public UserService() {
		this.userRepository = new UserRepository();
	}
	
	@Override
	public Optional<User> save(User user) {
		try{
			userRepository.save(user);
			System.out.println("User saved successfully!");
		}catch(Exception e){
			System.err.println("Service save Hata!");
		}
		return Optional.ofNullable(user);
	}
	
	@Override
	public boolean delete(Long id) {
		try{
			userRepository.delete(id);
			System.out.println("User deleted successfully!");
			return true;
		}catch (Exception e){
			System.err.println("Service delete Hata!");
		}
		return false;
	}
	
	@Override
	public Optional<User> update(User user) {
		try{
			userRepository.update(user);
			System.out.println("User updated successfully!");
		}catch(Exception e){
			System.out.println("Service update Hata!");
		}
		return Optional.ofNullable(user);
	}
	
	@Override
	public List findAll() {
		return userRepository.findAll();
	}
	
	@Override
	public Optional findById(Long id) {
		return userRepository.findById(id);
	}
}