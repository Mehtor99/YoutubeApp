package org.mehtor.repository;

import org.mehtor.entity.ELikeStatus;
import org.mehtor.entity.Like;
import org.mehtor.utility.ConnectionProvider;
import org.mehtor.utility.ICRUD;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LikeRepository implements ICRUD<Like> {
	
	private static LikeRepository instance;
	private final ConnectionProvider connectionProvider;
	private String sql;
	
	public LikeRepository() {
		this.connectionProvider = ConnectionProvider.getInstance();
	}
	
	public static synchronized LikeRepository getInstance() {
		if (instance == null) {
			instance = new LikeRepository();
		}
		return instance;
	}
	
	@Override
	public Optional<Like> save(Like like) {
		sql = "INSERT INTO tbllike (videoId, userId, likeStatus) VALUES (?, ?, ?::like_status)";
		try (PreparedStatement preparedStatement = connectionProvider.getPreparedStatement(sql)) {
			preparedStatement.setLong(1, like.getVideoId());
			preparedStatement.setLong(2, like.getUserId());
			preparedStatement.setString(3, like.getLikeStatus().name());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Repository : Like kaydedilirken hata oluştu : " + e.getMessage());
		}
		return Optional.ofNullable(like);
	}
	
	@Override
	public boolean delete(Long likeId) {
		sql = "DELETE FROM tbllike WHERE id=?";
		try (PreparedStatement preparedStatement = connectionProvider.getPreparedStatement(sql)) {
			preparedStatement.setLong(1, likeId);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Repository : Like silinirken hata oluştu. " + e.getMessage());
		}
		return true;
	}
	
	@Override
	public Optional<Like> update(Like like) {
		sql = "UPDATE tbllike SET videoId=?, userId=?, likeStatus=?::like_status  WHERE id=?";
		try (PreparedStatement preparedStatement = connectionProvider.getPreparedStatement(sql)) {
			preparedStatement.setLong(1, like.getVideoId());
			preparedStatement.setLong(2, like.getUserId());
			preparedStatement.setString(3, like.getLikeStatus().name());
			preparedStatement.setLong(4, like.getId());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Repository : Like güncellenirken hata oluştu. " + e.getMessage());
		}
		return Optional.empty();
	}
	
	@Override
	public List<Like> findAll() {
		sql = "SELECT * FROM tbllike";
		List<Like> likeList = new ArrayList<>();
		try (PreparedStatement preparedStatement = connectionProvider.getPreparedStatement(sql)) {
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Like like = mapResultSetToLike(rs);
				likeList.add(like);
			}
		} catch (SQLException e) {
			System.out.println("Repository : Like'lar listelenirken hata oluştu. " + e.getMessage());
		}
		return likeList;
	}
	
	@Override
	public Optional<Like> findById(Long likeId) {
		sql = "SELECT * FROM tbllike WHERE id=?";
		try (PreparedStatement preparedStatement = connectionProvider.getPreparedStatement(sql)) {
			preparedStatement.setLong(1, likeId);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				Like like = mapResultSetToLike(rs);
				return Optional.of(like);
			}
		} catch (SQLException e) {
			System.out.println("Repository : Like bulunamadı. " + e.getMessage());
		}
		return Optional.empty();
	}
	
	private Like mapResultSetToLike(ResultSet rs) throws SQLException {
		Long id = rs.getLong("id");
		Long videoId = rs.getLong("videoId");
		Long userId = rs.getLong("userId");
		ELikeStatus likeStatus = ELikeStatus.valueOf(rs.getString("likeStatus"));
		Integer state = rs.getInt("state");
		Long createdAt = rs.getLong("createdAt");
		Long updatedAt = rs.getLong("updatedAt");
		
		return new Like(id, videoId, userId, likeStatus, state, createdAt, updatedAt);
	}
}