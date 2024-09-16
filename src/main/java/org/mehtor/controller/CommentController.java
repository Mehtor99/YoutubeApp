package org.mehtor.controller;

import org.mehtor.entity.Comment;
import org.mehtor.service.CommentService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CommentController {
private final CommentService commentService;
	
	public CommentController() {
		this.commentService = new CommentService();
	}
	
	public Optional<Comment> save(Comment comment) {
		try {
			return commentService.save(comment);
		}
		catch (Exception e) {
			System.out.println("Controller Comment save Hata!! "+e.getMessage());
		}
		return Optional.empty();
	}
	
	public Optional<Comment> update(Comment comment) {
		try {
			return commentService.update(comment);
		}
		catch (Exception e) {
			System.err.println("Controller Comment update Hata!! "+e.getMessage());
		}
		return Optional.empty();
	}
	
	public boolean delete(Long id){
		try {
			return commentService.delete(id);
		}
		catch (Exception e) {
			System.err.println("Controller Comment delete Hata!! "+e.getMessage());
		}
		return false;
	}
	
	public List<Comment> findAll() {
		try {
			return commentService.findAll();
		}
		catch (Exception e) {
			System.err.println("Controller Comment findAll Hata!! "+e.getMessage());
		}
		return new ArrayList<>();
	}
	
	public Optional<Comment> findById(Long id) {
		Optional<Comment> comment = commentService.findById(id);
		comment.ifPresentOrElse(
				v -> System.out.println("Controller comment bulundu: "+v.getId()),
				() -> System.out.println("Controller boyle bir comment bulunamadı.")
		);
		return comment;
	}
}