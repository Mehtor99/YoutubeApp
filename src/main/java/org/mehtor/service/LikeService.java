package org.mehtor.service;

import org.mehtor.entity.Like;
import org.mehtor.repository.LikeRepository;
import org.mehtor.utility.ICRUDService;

import java.util.List;
import java.util.Optional;

public class LikeService implements ICRUDService<Like> {
	private final LikeRepository likeRepository;
	
	public LikeService() {
		this.likeRepository =new LikeRepository();
	}
	
	@Override
	public Optional<Like> save(Like like) {
		try {
			likeRepository.save(like);
			System.out.println("Kike saved succesfully!");
		}
		catch (Exception e) {
			System.err.println("Service: Lıke save Error..."+e.getMessage());
		}
		return Optional.ofNullable(like);
	}
	
	@Override
	public boolean delete(Long id) {
		if(findById(id).isPresent()) {
			try{
				likeRepository.delete(id);
				return true;
			}catch (Exception e) {
				System.out.println("Service: like silinemedi..."+e.getMessage());
			}
		}else{
			System.out.println("Service: Silinecek like bulunamadı");
		}
		return false;
	}
	
	@Override
	public Optional<Like> update(Like like) {
		if(findById(like.getId()).isPresent()){
			try {
				likeRepository.update(like);
				System.out.println(" Kullanıcı idsi" +like.getUserId()+"like'ı güncellendi.");
			} catch (Exception e) {
				System.err.println("Service : Kullanıcı like kaydedilirke hata oluştu. " + e.getMessage());//serr
				return Optional.empty();
			}
		}else{
			System.err.println("Service : Like bulunamadı.");
			return Optional.empty();
		}
		
		return Optional.of(like);
	}
	
	@Override
	public List<Like> findAll() {
		return likeRepository.findAll();
	}
	
	@Override
	public Optional<Like> findById(Long id) {
		return likeRepository.findById(id);
	}
}