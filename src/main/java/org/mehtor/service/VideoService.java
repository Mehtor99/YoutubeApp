package org.mehtor.service;

import org.mehtor.entity.Comment;
import org.mehtor.entity.Video;
import org.mehtor.repository.CommentRepository;
import org.mehtor.repository.VideoRepository;
import org.mehtor.utility.ICRUDService;

import java.util.List;
import java.util.Optional;

public class VideoService implements ICRUDService<Video> {
	private final VideoRepository videoRepository;
	
	public VideoService() {
		this.videoRepository = new VideoRepository();
	}
	
	@Override
	public Optional<Video> save(Video video) {
		
		try {
			videoRepository.save(video);
			System.out.println(video.getTitle()+ "Baslikli video kaydedildi. ");
		}
		catch (Exception e) {
			System.err.println("Service: video kaydedilemedi..."+e.getMessage());
		}
		return Optional.ofNullable(video);
	}
	
	@Override
	public boolean delete(Long id) {
		if(findById(id).isPresent()) {
			try{
				videoRepository.delete(id);
				return true;
			}catch (Exception e) {
				System.out.println("Service: video silinemedi..."+e.getMessage());
			}
		}else{
			System.out.println("Service: Silinecek video bulunamadı");
		}
		return false;
	}
	
	@Override
	public Optional<Video> update(Video video) {
		if(findById(video.getId()).isPresent()) {
			try {
				videoRepository.update(video);
				System.out.println(video.getTitle()+"baslikli video guncellendi");
			}catch (Exception e){
				System.err.println("Service:  video guncellenemedi..."+e.getMessage());
				return Optional.empty();
			}
		}else{
			System.out.println("Service: Guncellenecek  video bulunamadi");
			return Optional.empty();
		}
		return Optional.of(video);
	}
	
	@Override
	public List<Video> findAll() {
		return videoRepository.findAll();
	}
	
	@Override
	public Optional<Video> findById(Long id) {
		return videoRepository.findById(id);
	}
}