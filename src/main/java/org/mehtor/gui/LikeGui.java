package org.mehtor.gui;

import org.mehtor.entity.ELikeStatus;
import org.mehtor.entity.Like;
import org.mehtor.repository.LikeRepository;

public class LikeGui {
	public static void main(String[] args) {
		//Long videoId, Long userId, ELikeStatus likeStatus,Integer state, Long createdAt, Long updatedAt
		Like like = new Like(2L, 1L, ELikeStatus.LIKE,1, System.currentTimeMillis(),System.currentTimeMillis());
		Like like2 = new Like(3L, 5L, ELikeStatus.DISLIKE,1, System.currentTimeMillis(),System.currentTimeMillis());
		
		LikeRepository likeRepository = new LikeRepository();
		//likeRepository.save(like2);
		
		like2.setId(2L);
		like2.setLikeStatus(ELikeStatus.LIKE);
		likeRepository.update(like2);
		
		
	}
}