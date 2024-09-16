//package org.mehtor.repository;
//
//
//
//import org.mehtor.entity.Video;
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
//public class VideoRepository implements ICRUD<Video> {
//
//	private String sql = "";
//	private final ConnectionProvider connectionProvider;
//	private final PreparedStatement ps;
//	private final Connection connection;
//
//	public VideoRepository() {
//		this.connectionProvider = new ConnectionProvider();
//		this.ps = connectionProvider.getPreparedStatement();
//		this.connection=connectionProvider.getConn();
//	}
//
//	@Override
//	public Optional<Video> save(Video video) {
//		connectionProvider.executeUpdate(SQLQueryBuilder.generateInsert(video, "tblvideo"));
//		return Optional.of(video);
//	}
//
//	@Override
//	public Optional<Video> update(Video video) {
//		connectionProvider.executeUpdate(SQLQueryBuilder.generateUpdate(video,"tblvideo"));
//		return Optional.of(video);
//	}
//
//	@Override
//	public boolean delete(Long id) {
//		connectionProvider.executeUpdate(SQLQueryBuilder.generateDelete(Video.class, "tblvideo", id));
//		return true;
//	}
//
//	@Override
//	public List<Video> findAll() {
//		Optional<ResultSet> resultSet=connectionProvider.executeQuery("SELECT * FROM " + "tblvideo" + " ORDER BY id");
//		if(resultSet.isPresent()){
//			ResultSet rs = resultSet.get();
//			return SQLQueryBuilder.generateList(Video.class,"tblvideo", rs);
//		}
//		return new ArrayList<>();
//	}
//
//	@Override
//	public Optional<Video> findById(Long id) {
//		sql="SELECT * FROM " + "tblvideo" + " WHERE id=" + id;
//		Optional<ResultSet> resultSet=connectionProvider.executeQuery(sql);
//		if(resultSet.isPresent()){
//			ResultSet rs = resultSet.get();
//			return SQLQueryBuilder.findById(Video.class,"tblvideo",id,rs);
//		}
//		return Optional.empty();
//	}
//}