package org.mehtor.dto.request;


import org.mehtor.entity.ELikeStatus;

public class LikeUpdateRequestDTO {
	
	private Long id; // Güncelleme işlemleri için ID gerekli
	private Long videoId;
	private Long userId;
	private ELikeStatus likeStatus;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getVideoId() {
		return videoId;
	}
	
	public void setVideoId(Long videoId) {
		this.videoId = videoId;
	}
	
	public Long getUserId() {
		return userId;
	}
	
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	public ELikeStatus getLikeStatus() {
		return likeStatus;
	}
	
	public void setLikeStatus(ELikeStatus likeStatus) {
		this.likeStatus = likeStatus;
	}
}