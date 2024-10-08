package org.mehtor.controller;

import org.mehtor.entity.Video;
import org.mehtor.service.VideoService;

import java.util.List;
import java.util.Optional;

public class VideoController {
	private final VideoService videoService;

	public VideoController() {
		this.videoService = new VideoService();
	}

	public Optional<Video> save(Video video) {
		try{
			return videoService.save(video);
		} catch (Exception e) {
			System.out.println("Controller Save Hata!");
		}
		return Optional.empty();
	}

	public Optional<Video> update(Video video) {
		try{
			return videoService.update(video);
		} catch (Exception e) {
			System.err.println("Controller Update Hata!");
		}
		return Optional.empty();
	}

	public boolean delete(Long id) {
		try{
			return videoService.delete(id);
		} catch (Exception e) {
			System.err.println("Controller Delete Hata!");
		}
		return false;
	}

	public List<Video> findAll() {
		try{
			return videoService.findAll();
		} catch (Exception e) {
			System.err.println("Controller FindAll Hata!");
		}
		return null;
	}

	public Optional<Video> findById(Long id) {
		Optional<Video> video = videoService.findById(id);
		video.ifPresentOrElse(
				v -> System.out.println("Controller Video bulundu: " + v.getTitle()),
				() -> System.out.println("Controller Böyle bir video bulunamadı.")
		);
		return video;
	}
}