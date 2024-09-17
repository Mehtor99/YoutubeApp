package org.mehtor.dto.request;

import org.mehtor.entity.ELikeStatus;

public class LikeSaveRequestDTO {
	
	private Long videoId;
	private Long userId;
	private ELikeStatus likeStatus;
	
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