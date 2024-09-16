//package org.mehtor.service;
//
//import org.mehtor.entity.User;
//import org.mehtor.repository.UserRepository;
//import org.mehtor.utility.ICRUDService;
//
//import java.util.List;
//import java.util.Optional;
//
//public class UserService implements ICRUDService<User> {
//
//	private final UserRepository userRepository;
//
//	public UserService() {
//		this.userRepository = new UserRepository();
//	}
//
//	@Override
//	public Optional<User> save(User user) {
//		try{
//			userRepository.save(user);
//			System.out.println("User saved successfully!");
//		}catch(Exception e){
//			System.err.println("Service save Hata!..."+e.getMessage());
//		}
//		return Optional.ofNullable(user);
//	}
//
//	@Override
//	public boolean delete(Long silinecekId) {
//		if(findById(silinecekId).isPresent()) {
//			try{
//				userRepository.delete(silinecekId);
//				return true;
//			}catch (Exception e) {
//				System.out.println("Service: user silinemedi..."+e.getMessage());
//			}
//		}else{
//			System.out.println("Service: Silinecek user bulunamadı");
//		}
//		return false;
//	}
//
//	@Override
//	public Optional<User> update(User user) {
//		if(findById(user.getId()).isPresent()) {
//			try {
//				userRepository.update(user);
//				System.out.println(user.getName()+"isimli user guncellendi");
//			}catch (Exception e){
//				System.err.println("Service: user guncellenemdi..."+e.getMessage());
//				return Optional.empty();
//			}
//		}else{
//			System.out.println("Service: Guncellenecek user bulunamadi");
//			return Optional.empty();
//		}
//		return Optional.of(user);
//	}
//
//	@Override
//	public List findAll() {
//		return userRepository.findAll();
//	}
//
//	@Override
//	public Optional findById(Long id) {
//		return userRepository.findById(id);
//	}
//}