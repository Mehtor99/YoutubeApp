package org.mehtor.service;

import org.mehtor.entity.Comment;
import org.mehtor.repository.CommentRepository;
import org.mehtor.utility.ICRUDService;

import java.util.List;
import java.util.Optional;

public class CommentService implements ICRUDService<Comment> {
	private final CommentRepository commentRepository;
	
	public CommentService() {
		this.commentRepository = new CommentRepository();
	}
	
	@Override
	public Optional<Comment> save(Comment comment) {
		
		try {
			commentRepository.save(comment);
			System.out.println(comment.getUserId()+ "Id'li kisinin yorumu kaydedildi. ");
		}
		catch (Exception e) {
			System.err.println("Service: Yorum kaydedilemedi..."+e.getMessage());
		}
		return Optional.ofNullable(comment);
	}
	
	@Override
	public boolean delete(Long id) {
		if(findById(id).isPresent()) {
			try{
				commentRepository.delete(id);
				return true;
			}catch (Exception e) {
				System.out.println("Service: Comment silinemedi..."+e.getMessage());
			}
		}else{
			System.out.println("Service: Silinecek comment bulunamadı");
		}
		return false;
	}
	
	@Override
	public Optional<Comment> update(Comment comment) {
		if(findById(comment.getId()).isPresent()) {
			try {
				commentRepository.update(comment);
				System.out.println(comment.getUserId()+"Idli kisinin yorumu guncellendi");
			}catch (Exception e){
				System.err.println("Service: Comment guncellenemdi..."+e.getMessage());
				return Optional.empty();
			}
		}else{
			System.out.println("Service: Guncellenecek comment bulunamadi");
			return Optional.empty();
		}
		return Optional.of(comment);
	}
	
	@Override
	public List<Comment> findAll() {
		return commentRepository.findAll();
	}
	
	@Override
	public Optional<Comment> findById(Long id) {
		return commentRepository.findById(id);
	}
}