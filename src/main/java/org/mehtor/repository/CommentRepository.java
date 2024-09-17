package org.mehtor.repository;

import org.mehtor.entity.Comment;
import org.mehtor.utility.ConnectionProvider;
import org.mehtor.utility.ICRUD;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CommentRepository implements ICRUD<Comment> {
	
	private static CommentRepository instance;
	private final ConnectionProvider connectionProvider;
	private String sql;
	
	public CommentRepository() {
		this.connectionProvider = ConnectionProvider.getInstance();
	}
	
	public static synchronized CommentRepository getInstance() {
		if (instance == null) {
			instance = new CommentRepository();
		}
		return instance;
	}
	
	@Override
	public Optional<Comment> save(Comment comment) {
		sql = "INSERT INTO tblcomment (content, videoId, userId) VALUES (?, ?, ?)";
		try (PreparedStatement preparedStatement = connectionProvider.getPreparedStatement(sql)) {
			preparedStatement.setString(1, comment.getContent());
			preparedStatement.setLong(2, comment.getVideoId());
			preparedStatement.setLong(3, comment.getUserId());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Repository : Yorum kaydedilirken hata oluştu : " + e.getMessage());
		}
		return Optional.ofNullable(comment);
	}
	
	@Override
	public boolean delete(Long commentId) {
		sql = "DELETE FROM tblcomment WHERE id=?";
		try (PreparedStatement preparedStatement = connectionProvider.getPreparedStatement(sql)) {
			preparedStatement.setLong(1, commentId);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Repository : Yorum silinirken hata oluştu. " + e.getMessage());
		}
		return true;
	}
	
	@Override
	public Optional<Comment> update(Comment comment) {
		sql = "UPDATE tblcomment SET content=? WHERE id=?";
		try (PreparedStatement preparedStatement = connectionProvider.getPreparedStatement(sql)) {
			preparedStatement.setString(1, comment.getContent());
			preparedStatement.setLong(2, comment.getId());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Repository : Yorum güncellenirken hata oluştu. " + e.getMessage());
		}
		return Optional.empty();
	}
	
	@Override
	public List<Comment> findAll() {
		sql = "SELECT * FROM tblcomment";
		List<Comment> commentList = new ArrayList<>();
		try (PreparedStatement preparedStatement = connectionProvider.getPreparedStatement(sql)) {
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Comment comment = mapResultSetToComment(rs);
				commentList.add(comment);
			}
		} catch (SQLException e) {
			System.out.println("Repository : Yorumlar listelenirken hata oluştu. " + e.getMessage());
		}
		return commentList;
	}
	
	@Override
	public Optional<Comment> findById(Long commentId) {
		sql = "SELECT * FROM tblcomment WHERE id=?";
		try (PreparedStatement preparedStatement = connectionProvider.getPreparedStatement(sql)) {
			preparedStatement.setLong(1, commentId);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				Comment comment = mapResultSetToComment(rs);
				return Optional.of(comment);
			}
		} catch (SQLException e) {
			System.out.println("Repository : Yorum bulunamadı. " + e.getMessage());
		}
		return Optional.empty();
	}
	
	private Comment mapResultSetToComment(ResultSet rs) throws SQLException {
		Long id = rs.getLong("id");
		String content = rs.getString("content");
		Long videoId = rs.getLong("videoId");
		Long userId = rs.getLong("userId");
		Integer state = rs.getInt("state");
		Long createdAt = rs.getLong("createdAt");
		Long updatedAt = rs.getLong("updatedAt");
		
		return new Comment(id, content, videoId, userId, state, createdAt, updatedAt);
	}
}