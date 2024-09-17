package org.mehtor.repository;



import org.mehtor.entity.ECategory;
import org.mehtor.entity.Video;
import org.mehtor.utility.ConnectionProvider;
import org.mehtor.utility.ICRUD;
import org.mehtor.utility.SQLQueryBuilder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class VideoRepository implements ICRUD<Video> {
	
	private static VideoRepository instance;
	private final ConnectionProvider connectionProvider;
	private String sql;
	
	public VideoRepository() {
		this.connectionProvider = ConnectionProvider.getInstance();
	}
	
	public static synchronized VideoRepository getInstance() {
		if (instance == null) {
			instance = new VideoRepository();
		}
		return instance;
	}
	
	@Override
	public Optional<Video> save(Video video) {
		sql =
				"INSERT INTO tblvideo (uploaderId, title, description, category, viewCount, likeCount, dislikeCount VALUES (?, ?, ?, ?::video_category, ?, ?, ?)";
		try (PreparedStatement preparedStatement = connectionProvider.getPreparedStatement(sql)) {
			preparedStatement.setLong(1, video.getUploaderId());
			preparedStatement.setString(2, video.getTitle());
			preparedStatement.setString(3, video.getDescription());
			preparedStatement.setString(4, video.getCategory().name());
			preparedStatement.setInt(5, video.getViewCount());
			preparedStatement.setInt(6, video.getLikeCount());
			preparedStatement.setInt(7, video.getDislikeCount());
			preparedStatement.executeUpdate();
		}
		catch (SQLException e) {
			System.out.println("Repository : Video kaydedilirken hata oluştu : " + e.getMessage());
		}
		return Optional.ofNullable(video);
	}
	
	@Override
	public boolean delete(Long videoId) {
		sql = "DELETE FROM tblvideo WHERE id=?";
		try (PreparedStatement preparedStatement = connectionProvider.getPreparedStatement(sql)) {
			preparedStatement.setLong(1, videoId);
			preparedStatement.executeUpdate();
		}
		catch (SQLException e) {
			System.out.println("Repository : Video silinirken hata oluştu. " + e.getMessage());
		}
		return true;
	}
	
	@Override
	public Optional<Video> update(Video video) {
		sql =
				"UPDATE tblvideo SET uploaderId=?, title=?, description=?, category=?::video_category, viewCount=?, " + "likeCount=?, dislikeCount=? WHERE id=?";
		try (PreparedStatement preparedStatement = connectionProvider.getPreparedStatement(sql)) {
			preparedStatement.setLong(1, video.getUploaderId());
			preparedStatement.setString(2, video.getTitle());
			preparedStatement.setString(3, video.getDescription());
			preparedStatement.setString(4, video.getCategory().name());
			preparedStatement.setInt(5, video.getViewCount());
			preparedStatement.setInt(6, video.getLikeCount());
			preparedStatement.setInt(7, video.getDislikeCount());
			preparedStatement.setLong(8, video.getId());
			preparedStatement.executeUpdate();
		}
		catch (SQLException e) {
			System.out.println("Repository : Video güncellenirken hata oluştu. " + e.getMessage());
		}
		return Optional.empty();
	}
	
	@Override
	public List<Video> findAll() {
		sql = "SELECT * FROM tblvideo";
		List<Video> videoList = new ArrayList<>();
		try (PreparedStatement preparedStatement = connectionProvider.getPreparedStatement(sql)) {
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Video video = mapResultSetToVideo(rs);
				videoList.add(video);
			}
		}
		catch (SQLException e) {
			System.out.println("Repository : Videolar listelenirken hata oluştu. " + e.getMessage());
		}
		return videoList;
	}
	
	@Override
	public Optional<Video> findById(Long videoId) {
		sql = "SELECT * FROM tblvideo WHERE id=?";
		try (PreparedStatement preparedStatement = connectionProvider.getPreparedStatement(sql)) {
			preparedStatement.setLong(1, videoId);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				Video video = mapResultSetToVideo(rs);
				return Optional.of(video);
			}
		} catch (SQLException e) {
			System.out.println("Repository : Video bulunamadı. " + e.getMessage());
		}
		return Optional.empty();
	}
	
	private Video mapResultSetToVideo(ResultSet rs) throws SQLException {
		Long id = rs.getLong("id");
		Long uploaderId = rs.getLong("uploaderId");
		String title = rs.getString("title");
		String description = rs.getString("description");
		ECategory category = ECategory.valueOf(rs.getString("category"));
		Integer viewCount = rs.getInt("viewCount");
		Integer likeCount = rs.getInt("likeCount");
		Integer dislikeCount = rs.getInt("dislikeCount");
		Integer state = rs.getInt("state");
		Long createdAt = rs.getLong("createdAt");
		Long updatedAt = rs.getLong("updatedAt");
		
		return new Video(id, uploaderId, title, description, category, viewCount, likeCount, dislikeCount, state, createdAt, updatedAt);
	}
	
}