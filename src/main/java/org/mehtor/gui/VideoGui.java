package org.mehtor.gui;

import org.mehtor.controller.VideoController;
import org.mehtor.entity.ECategory;
import org.mehtor.entity.Video;
import org.mehtor.repository.VideoRepository;

import java.util.Optional;

public class VideoGui {
	public static void main(String[] args) {
		
		Video video = new Video(7L, "elma kurdu", "ya da kurmadı", ECategory.GAMES,1, System.currentTimeMillis(),System.currentTimeMillis());
		
	}
}