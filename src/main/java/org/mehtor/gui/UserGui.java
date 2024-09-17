package org.mehtor.gui;


import org.mehtor.controller.UserController;
import org.mehtor.dto.request.UserSaveRequestDTO;
import org.mehtor.dto.request.UserUpdateRequestDTO;
import org.mehtor.entity.ERole;
import org.mehtor.entity.User;
import org.mehtor.service.UserService;

public class UserGui {
	public static void main(String[] args) {
		User user1 = new User("Alperen", "bicav3", "alpg@gmaiiiil", "mehtor31234", "1234", ERole.USER,1,
		                      System.currentTimeMillis(),System.currentTimeMillis());
		
		UserSaveRequestDTO userSaveRequestDTO = new UserSaveRequestDTO("Alperen", "bicav", "alpg@gmail12345", "mehtor31212343", "1234",ERole.USER);
		//UserController userController = new UserController();
		//userController.save(user);
		
		//userController.findAll().forEach(System.out::println);
		
		//VideoController videoController = new VideoController();
		//Video video = new Video(1L, "video1", "açıklama", ECategory.GAMES);
		
		//videoController.save(video);
		//videoController.delete(1L);
		
//		UserRepository userRepository = new UserRepository();
//		Optional<User> byId = userRepository.findById(5L);
//		System.out.println(byId.get());
		
		UserController userController = new UserController();
		//userController.save(userSaveRequestDTO);
		
		//userController.findAll().forEach(System.out::println);
		
		//userController.findById(5L);
		UserUpdateRequestDTO userUpdateRequestDTO = new UserUpdateRequestDTO();
		
		userUpdateRequestDTO.setId(11L);
		userUpdateRequestDTO.setName("Alperen");
		userUpdateRequestDTO.setSurname("Bicav");
		userUpdateRequestDTO.setUsername("mehtorYen");
		userUpdateRequestDTO.setEmail("123454356@@");
		userController.update(userUpdateRequestDTO);
		
		
	}
}