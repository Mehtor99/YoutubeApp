package org.mehtor.gui;

import org.mehtor.controller.CommentController;
import org.mehtor.dto.request.CommentSaveRequestDTO;
import org.mehtor.dto.request.CommentUpdateRequestDTO;
import org.mehtor.entity.Comment;
import org.mehtor.repository.CommentRepository;

import java.util.Optional;

public class CommentGui {
	public static void main(String[] args) {
		//String content, Long videoId, Long userId,Integer state, Long createdAt, Long updatedAt
		Comment comment = new Comment("yararlı bir içerik :))",3L,7L,1,
		                              System.currentTimeMillis(),System.currentTimeMillis());
		
		CommentSaveRequestDTO commentSaveRequestDTO = new CommentSaveRequestDTO("Okul hazırlıkları için ideal",4L,11L);
		
		CommentRepository commentRepository = new CommentRepository();
		//commentRepository.save(comment);
		
		//commentRepository.findAll().forEach(System.out::println);
		
		//Optional<Comment> byId = commentRepository.findById(1L);
		//System.out.println(byId);
		
		//commentRepository.delete(2L);
		
//		comment.setId(3L);
//		comment.setContent("Devamının gelmesini istiyoruz.");
//		commentRepository.update(comment);
		
		CommentController commentController = new CommentController();
		//commentController.save(commentSaveRequestDTO);
		
		//commentController.findAll().forEach(System.out::println);
		
		//Optional<Comment> byId = commentController.findById(1L);
		//System.out.println(byId.get());
		
		CommentUpdateRequestDTO commentUpdateRequestDTO =new CommentUpdateRequestDTO();
		
		commentUpdateRequestDTO.setId(4L);
		commentUpdateRequestDTO.setContent("video kaldırılmadan izle");
		commentUpdateRequestDTO.setUserId(11L);
		commentUpdateRequestDTO.setVideoId(4L);
		
		commentController.update(commentUpdateRequestDTO);
	}
	
	
}