package org.mehtor;

import org.mehtor.controller.UserController;
import org.mehtor.entity.ERole;
import org.mehtor.entity.User;
import org.mehtor.repository.UserRepository;

public class Main {
	public static void main(String[] args) {
		User user = new User("Mehmet", "Ertop", "mehtor@gmail.com", "mehtor", "1234", ERole.USER,1,System.currentTimeMillis(),System.currentTimeMillis());
		UserController userController = new UserController();
		userController.save(user);
	}
}