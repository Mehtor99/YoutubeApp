package org.mehtor.controller;

import org.mehtor.entity.Comment;
import org.mehtor.entity.Like;
import org.mehtor.service.CommentService;
import org.mehtor.service.LikeService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LikeController {
	private final LikeService likeService;
	
	public LikeController() {
		this.likeService = new LikeService();
	}
	
	public Optional<Like> save(Like like) {
		try {
			return likeService.save(like);
		}
		catch (Exception e) {
			System.out.println("Controller Like save Hata!! "+e.getMessage());
		}
		return Optional.empty();
	}
	
	public Optional<Like> update(Like like) {
		try {
			return likeService.update(like);
		}
		catch (Exception e) {
			System.err.println("Controller like update Hata!! "+e.getMessage());
		}
		return Optional.empty();
	}
	
	public boolean delete(Long id){
		try {
			return likeService.delete(id);
		}
		catch (Exception e) {
			System.err.println("Controller like delete Hata!! "+e.getMessage());
		}
		return false;
	}
	
	public List<Like> findAll() {
		try {
			return likeService.findAll();
		}
		catch (Exception e) {
			System.err.println("Controller like findAll Hata!! "+e.getMessage());
		}
		return new ArrayList<>();
	}
	
	public Optional<Like> findById(Long id) {
		Optional<Like> like = likeService.findById(id);
		like.ifPresentOrElse(
				v -> System.out.println("Controller like bulundu: "+v.getId()),
				() -> System.out.println("Controller boyle bir like bulunamadı.")
		);
		return like;
	}
}