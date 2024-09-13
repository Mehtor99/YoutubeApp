package org.mehtor.entity;

import java.time.LocalDateTime;

public class Video extends BaseEntity {
	private Long id;
	private String title;
	private String description;
	private ECategory category;
	private String viewCount;
	private String likeCount;
	private Integer dislikeCount;
	
	public Video() {
	}
	
	public Video(String title, String description,ECategory category, String viewCount, String likeCount,Integer dislikeCount,
	             Integer state,
	             Long createdAt, Long updatedAt) {
		super(state, createdAt, updatedAt);
		this.title = title;
		this.description = description;
		this.viewCount = viewCount;
		this.likeCount = likeCount;
		this.dislikeCount = dislikeCount;
		this.category = category;
	}
	
	public Video( Long id, String title, String description,ECategory category, String viewCount, String likeCount,Integer dislikeCount,Integer state, Long createdAt, Long updatedAt) {
		super(state, createdAt, updatedAt);
		this.id = id;
		this.title = title;
		this.description = description;
		this.viewCount = viewCount;
		this.likeCount = likeCount;
		this.dislikeCount = dislikeCount;
		this.category = category;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getViewCount() {
		return viewCount;
	}
	
	public void setViewCount(String viewCount) {
		this.viewCount = viewCount;
	}
	
	public String getLikeCount() {
		return likeCount;
	}
	
	public void setLikeCount(String likeCount) {
		this.likeCount = likeCount;
	}
	
	public Integer getDislikeCount() {
		return dislikeCount;
	}
	
	public void setDislikeCount(Integer dislikeCount) {
		this.dislikeCount = dislikeCount;
	}
	
	public ECategory getCategory() {
		return category;
	}
	
	public void setCategory(ECategory category) {
		this.category = category;
	}
	
	@Override
	public String toString() {
		return "Video{" + "id=" + getId() + ", title='" + getTitle() + '\'' + ", description='" + getDescription() + '\'' + ", category=" + category + ", viewCount='" + getViewCount() + '\'' + ", likeCount='" + getLikeCount() + '\'' + ", dislikeCount=" + getDislikeCount() + ", state=" + getState() + ", createdAt=" + getCreatedAt() + ", updatedAt=" + getUpdatedAt() + '}';
	}
}