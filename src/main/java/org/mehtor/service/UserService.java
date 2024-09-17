package org.mehtor.service;

import org.mehtor.dto.request.UserSaveRequestDTO;
import org.mehtor.dto.request.UserUpdateRequestDTO;
import org.mehtor.dto.response.UserResponseDTO;
import org.mehtor.entity.User;
import org.mehtor.repository.UserRepository;
import org.mehtor.utility.ICRUDService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserService {
	
	private final UserRepository userRepository;
	
	public UserService() {
		this.userRepository = new UserRepository();
	}
	
	public Optional<UserResponseDTO> save(UserSaveRequestDTO userSaveRequestDTO) {
		User user;
		Optional<User> userOptional;
		UserResponseDTO userResponseDTO = new UserResponseDTO();
		try {
			user = new User();
			user.setName(userSaveRequestDTO.getName());
			user.setSurname(userSaveRequestDTO.getSurname());
			user.setEmail(userSaveRequestDTO.getEmail());
			user.setPassword(userSaveRequestDTO.getPassword());
			user.setRole(userSaveRequestDTO.getRole());
			user.setUsername(userSaveRequestDTO.getUsername());
			userOptional = userRepository.save(user);
			
			System.out.println(user.getName() + " isimli kullanıcı kaydedildi.");
			userResponseDTO.setEmail(userOptional.get().getEmail());
			userResponseDTO.setUsername(userOptional.get().getUsername());
			userResponseDTO.setName(userOptional.get().getName());
			userResponseDTO.setSurname(userOptional.get().getSurname());
			return  Optional.of(userResponseDTO);
		}
		catch (Exception e) {
			System.err.println("Service save Hata!..." + e.getMessage());
		}
		return Optional.empty();
	}
	
	
	public boolean delete(Long id) {
		try {
			userRepository.delete(id);
			System.out.println("Kullanıcı silindi.");
		}
		catch (Exception e) {
			System.out.println("Service: Kullanıcı silinirken hata oluştu: " + e.getMessage());
		}
		return true;
	}
	
	public Optional<UserResponseDTO> update(UserUpdateRequestDTO userUpdateRequestDTO) {
		
		try {
			Optional<User> optionalUser = userRepository.findById(userUpdateRequestDTO.getId());
			if (optionalUser.isPresent()) {
				User user= new User();
				user = optionalUser.get();
				user.setName(userUpdateRequestDTO.getName());
				user.setSurname(userUpdateRequestDTO.getSurname());
				user.setEmail(userUpdateRequestDTO.getEmail());
				user.setUsername(userUpdateRequestDTO.getUsername());
				Optional<User> userUpdate = userRepository.update(user);
				System.out.println(user.getUsername() + " kullanıcı adlı kullanıcı bilgileri güncellendi.");
				
				UserResponseDTO userResponseDTO = new UserResponseDTO();
				
				userResponseDTO.setEmail(userUpdate.get().getEmail());
				userResponseDTO.setUsername(userUpdate.get().getUsername());
				userResponseDTO.setName(userUpdate.get().getName());
				userResponseDTO.setSurname(userUpdate.get().getSurname());
				
				return Optional.of(userResponseDTO);
			}
			else {
				System.out.println("Kullanıcı bulunamadı.");
			}
		}
		catch (Exception e) {
			System.out.println("Service: Kullanıcı bilgileri güncellenirken hata oluştu: " + e.getMessage());
		}
		return Optional.empty();
	}
	
	public List<UserResponseDTO> findAll() {
		List<User> allUser = userRepository.findAll();
		
		List<UserResponseDTO> userResponseDTOList = new ArrayList<>();
		for (User user : allUser) {
			UserResponseDTO userResponseDTO = new UserResponseDTO();
			userResponseDTO.setEmail(user.getEmail());
			userResponseDTO.setUsername(user.getUsername());
			userResponseDTO.setName(user.getName());
			userResponseDTO.setSurname(user.getSurname());
			userResponseDTOList.add(userResponseDTO);
			
		}
		return userResponseDTOList;
	}
	
	public Optional<User> findById(Long id) {
		return userRepository.findById(id);
	}
}