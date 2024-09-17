package org.mehtor.service;

import org.mehtor.dto.request.CommentSaveRequestDTO;
import org.mehtor.dto.request.CommentUpdateRequestDTO;
import org.mehtor.dto.response.CommentResponseDTO;
import org.mehtor.entity.Comment;
import org.mehtor.entity.User;
import org.mehtor.repository.CommentRepository;
import org.mehtor.utility.ICRUDService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CommentService {
	private final CommentRepository commentRepository;
	
	public CommentService() {
		this.commentRepository = new CommentRepository();
	}
	
	
	public Optional<CommentResponseDTO> save(CommentSaveRequestDTO commentSaveRequestDTO) {
		Comment comment;
		Optional<Comment> commentOptional;
		CommentResponseDTO commentResponseDTO;
		try {
			comment = new Comment();
			comment.setContent(commentSaveRequestDTO.getContent());
			comment.setVideoId(commentSaveRequestDTO.getVideoId());
			comment.setUserId(commentSaveRequestDTO.getUserId());
			commentOptional = commentRepository.save(comment);
			if (commentOptional.isPresent()) {
				System.out.println(comment.getUserId() + "Id'li kisinin yorumu kaydedildi. ");
				commentResponseDTO = new CommentResponseDTO();
				commentResponseDTO.setContent(commentOptional.get().getContent());
				commentResponseDTO.setVideoId(commentOptional.get().getVideoId());
				commentResponseDTO.setUserId(commentOptional.get().getUserId());
				return Optional.of(commentResponseDTO);
			}
		}
		catch (Exception e) {
			System.err.println("Service: Yorum kaydedilemedi..." + e.getMessage());
		}
		return Optional.empty();
	}
	
	
	public boolean delete(Long id) {
		if (findById(id).isPresent()) {
			try {
				commentRepository.delete(id);
				return true;
			}
			catch (Exception e) {
				System.out.println("Service: Comment silinemedi..." + e.getMessage());
			}
		}
		else {
			System.out.println("Service: Silinecek comment bulunamadı");
		}
		return false;
	}
	
	public Optional<CommentResponseDTO> update(CommentUpdateRequestDTO commentUpdateRequestDTO) {
		Comment comment;
		try {
			Optional<Comment> commentOptional = commentRepository.findById(commentUpdateRequestDTO.getId());
			if (commentOptional.isPresent()) {
				comment = new Comment();
				comment.setContent(commentUpdateRequestDTO.getContent());
				comment.setId(commentUpdateRequestDTO.getId());//
				commentRepository.update(comment);
				
				CommentResponseDTO commentResponseDTO = new CommentResponseDTO();
				commentResponseDTO.setContent(commentUpdateRequestDTO.getContent());
				System.out.println("Comment basariyla güncellendi");
				
			}
		}catch (Exception e) {
			System.out.println("Service: Comment güncellenemedi..." + e.getMessage());
		}
		return Optional.empty();
	}
		
		public List<CommentResponseDTO> findAll () {
			List<Comment> allComment = commentRepository.findAll();
			List<CommentResponseDTO> commentResponseDTOList = new ArrayList<>();
			
			for (Comment comment : allComment) {
				CommentResponseDTO commentResponseDTO = new CommentResponseDTO();
				commentResponseDTO.setContent(comment.getContent());
				commentResponseDTO.setVideoId(comment.getVideoId());
				commentResponseDTO.setUserId(comment.getUserId());
				commentResponseDTOList.add(commentResponseDTO);
			}
			return commentResponseDTOList;
		}
		
		public Optional<Comment> findById (Long id){
			return commentRepository.findById(id);
		}
	}