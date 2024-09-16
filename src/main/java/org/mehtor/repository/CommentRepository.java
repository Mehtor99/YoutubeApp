package org.mehtor.repository;

import org.mehtor.entity.Comment;
import org.mehtor.entity.ERole;
import org.mehtor.entity.User;
import org.mehtor.utility.ConnectionProvider;
import org.mehtor.utility.ICRUD;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CommentRepository implements ICRUD<Comment> {
	String sql = "";
	private final ConnectionProvider connectionProvider;
	
	public CommentRepository() {
		this.connectionProvider = new ConnectionProvider();
	}
	
	@Override
	public Optional<Comment> save(Comment comment) {
		sql = ("INSERT INTO tblcomment(id, content, videoid, userid,state,createdat, updatedat) VALUES (%d,'%s',%d," +
				"%d," +
				"%d,%d,%d,)")
				.formatted(comment.getId(),comment.getContent(),comment.getVideoId(),comment.getVideoId(),
				           comment.getUserId(),comment.getState(),comment.getCreatedAt(),comment.getUpdatedAt());
		connectionProvider.executeUpdate(sql);
		return Optional.empty();
	}
	
	@Override
	public boolean delete(Long id) {
		sql = "DELETE FROM tblcomment WHERE id = " + id;
		connectionProvider.executeUpdate(sql);
		return true;
	}
	
	@Override
	public Optional<Comment> update(Comment comment) {
		sql = ("UPDATE tblcomment SET content='%s', videoid=%d, userid=%d, state=%d,createdat=%d, updatedat=%s WHERE " +
				"id=%d")
				.formatted(comment.getContent(),comment.getVideoId(),comment.getUserId(),comment.getState(),
				           comment.getCreatedAt(),comment.getUpdatedAt(),comment.getId());
		connectionProvider.executeUpdate(sql);
		return Optional.empty();
	}
	
	@Override
	public List<Comment> findAll() {
		sql = "SELECT * FROM tblcomment ORDER BY id DESC";
		Optional<ResultSet> resultSet = connectionProvider.executeQuery(sql);
		List<Comment> listComment = new ArrayList<>();
		
		try {
			if (resultSet.isPresent()) {
				ResultSet rs = resultSet.get();
				while(rs.next()){
					listComment.add(getValueFromResultsSet(rs));
				}
			}
		}
		catch (SQLException e) {
			System.out.println("comment findAll Error..."+ e.getMessage());;
		}
		return listComment;
	}
	
	@Override
	public Optional<Comment> findById(Long arananId) {
		sql = "SELECT * FROM tblcomment WHERE id = " + arananId;
		Optional<ResultSet> resultSet = connectionProvider.executeQuery(sql);
		
		try {
			if (resultSet.isPresent()) {
				ResultSet rs = resultSet.get();
				if (rs.next()) {
					return Optional.of(getValueFromResultsSet(rs));
				}
			}
		}
		catch (SQLException e) {
			System.out.println("comment findById Error..." + e.getMessage());
		}
		return Optional.empty();
	}
	
	private Comment getValueFromResultsSet(ResultSet rs) throws SQLException {
		Long id = rs.getLong("id");
		String content = rs.getString("content");
		Long videoid = rs.getLong("videoid");
		Long userid = rs.getLong("userid");
		Integer state = rs.getInt("state");
		Long createdat = rs.getLong("createdat");
		Long updateAt = rs.getLong("updatedat");
		
		return new Comment(id, content, videoid, userid, state, createdat, updateAt);
	}
}