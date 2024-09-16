//package org.mehtor.repository;
//
//import org.mehtor.entity.Like;
//import org.mehtor.utility.ConnectionProvider;
//import org.mehtor.utility.ICRUD;
//import org.mehtor.utility.SQLQueryBuilder;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//public class LikeRepository implements ICRUD<Like> {
//	private final ConnectionProvider connectionProvider;
//	private final PreparedStatement ps;
//	private final Connection connection;
//
//	public LikeRepository() {
//		this.connectionProvider = new ConnectionProvider();
//		this.ps = connectionProvider.getPreparedStatement();
//		this.connection=connectionProvider.getConn();
//	}
//
//	@Override
//	public Optional<Like> save(Like like) {
//		connectionProvider.executeUpdate(SQLQueryBuilder.generateInsert(like,"tbllike"));
//		return Optional.of(like);
//	}
//
//	@Override
//	public Optional<Like> update(Like like) {
//		connectionProvider.executeUpdate(SQLQueryBuilder.generateUpdate(like,"tbllike"));
//		return Optional.of(like);
//	}
//
//	@Override
//	public boolean delete(Long id) {
//		connectionProvider.executeUpdate(SQLQueryBuilder.generateDelete(Like.class, "tbllike", id));
//		return true;
//	}
//
//	@Override
//	public List<Like> findAll() {
//		Optional<ResultSet> resultSet=connectionProvider.executeQuery("SELECT * FROM " + "tbllike" + " ORDER BY id");
//		if(resultSet.isPresent()){
//			ResultSet rs = resultSet.get();
//			return SQLQueryBuilder.generateList(Like.class,"tbllike", rs);
//		}
//		return new ArrayList<>();
//	}
//
//	@Override
//	public Optional<Like> findById(Long id) {
//		Optional<ResultSet> resultSet=connectionProvider.executeQuery("SELECT * FROM" + "tbllike" + "WHERE id=" + id);
//		if(resultSet.isPresent()){
//			ResultSet rs = resultSet.get();
//			return SQLQueryBuilder.findById(Like.class,"tbllike",id,rs);
//		}
//		return Optional.empty();
//	}
//}