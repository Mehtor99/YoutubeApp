package org.mehtor;


import org.mehtor.entity.ECategory;
import org.mehtor.entity.ERole;
import org.mehtor.entity.User;
import org.mehtor.entity.Video;
import org.mehtor.repository.UserRepository;

import java.util.Optional;

public class Main {
	public static void main(String[] args) {
		User user1 = new User("Alper", "Güler", "alperg@gmail", "mehtor3", "1234", ERole.ADMIN,1,
		                      System.currentTimeMillis(),System.currentTimeMillis());
		//UserController userController = new UserController();
		//userController.save(user);
		
		//userController.findAll().forEach(System.out::println);
		
		//VideoController videoController = new VideoController();
		//Video video = new Video(1L, "video1", "açıklama", ECategory.GAMES);
		
		//videoController.save(video);
		//videoController.delete(1L);
		
		UserRepository userRepository = new UserRepository();
		Optional<User> byId = userRepository.findById(5L);
		System.out.println(byId.get());
		
		
	}
}