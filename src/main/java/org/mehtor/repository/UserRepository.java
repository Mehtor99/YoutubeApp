package org.mehtor.repository;

import org.mehtor.entity.ERole;
import org.mehtor.entity.User;
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

public class UserRepository implements ICRUD<User> {
	private String sql = "";
	private final ConnectionProvider connectionProvider;
	private final PreparedStatement ps;
	private final Connection connection;
	
	public UserRepository() {
		this.connectionProvider = new ConnectionProvider();
		this.ps = connectionProvider.getPreparedStatement();
		this.connection=connectionProvider.getConn();
	}
	
	@Override
	public Optional<User> save(User user) {
		connectionProvider.executeUpdate(SQLQueryBuilder.generateInsert(user,"tbluser"));
		return Optional.of(user);
	}
	
	@Override
	public Optional<User> update(User user) {
		connectionProvider.executeUpdate(SQLQueryBuilder.generateUpdate(user,"tbluser"));
		return Optional.of(user);
	}
	
	@Override
	public boolean delete(Long id) {
		connectionProvider.executeUpdate(SQLQueryBuilder.generateDelete(User.class,"tbluser",id));
		return true;
	}
	
	@Override
	public List<User> findAll() {
		Optional<ResultSet> resultSet=connectionProvider.executeQuery("SELECT * FROM " + "tbluser" + " ORDER BY id");
		if(resultSet.isPresent()){
			ResultSet rs = resultSet.get();
			return SQLQueryBuilder.generateList(User.class,"tbluser", rs);
		}
		return new ArrayList<>();
	}
	
	@Override
	public Optional<User> findById(Long id) {
		sql="SELECT * FROM" + "tbluser" + "WHERE id=" + id;
		Optional<ResultSet> resultSet=connectionProvider.executeQuery(sql);
		if(resultSet.isPresent()){
			ResultSet rs = resultSet.get();
			return SQLQueryBuilder.findById(User.class,"tbluser",id,rs);
		}
		return Optional.empty();
	}

/*	private User getValueFromResultSet(ResultSet rs) throws SQLException {
		Long id = rs.getLong("id");
		String name = rs.getString("name");
		String surname = rs.getString("surname");
		String email = rs.getString("email");
		String username = rs.getString("username");
		String password = rs.getString("password");
		String roleName = rs.getString("role");
		Integer state = rs.getInt("state");
		Long createdAt = rs.getLong("createdat");
		Long updatedAt = rs.getLong("updatedat");
		return new User(id, name, surname, email, username, password, ERole.valueOf(roleName), state, createdAt,
		                updatedAt);
	}*/
}