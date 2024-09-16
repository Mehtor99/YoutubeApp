package org.mehtor.repository;

import org.mehtor.entity.ERole;
import org.mehtor.entity.User;
import org.mehtor.utility.ConnectionProvider;
import org.mehtor.utility.ICRUD;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserRepository implements ICRUD<User> {
	private String sql = "";
	private final ConnectionProvider connectionProvider;
	
	public UserRepository() {
		this.connectionProvider = ConnectionProvider.getInstance();
	}
	
	@Override
	public Optional<User> save(User user) {
		sql = "INSERT INTO tbluser(name, surname, email, username, password,role) VALUES (?, ?, ?, ?, ?,?::user_role)";
		try (PreparedStatement preparedStatement = connectionProvider.getPreparedStatement(sql)) {
			preparedStatement.setString(1, user.getName());
			preparedStatement.setString(2, user.getSurname());
			preparedStatement.setString(3, user.getEmail());
			preparedStatement.setString(4, user.getUsername());
			preparedStatement.setString(5, user.getPassword());
			preparedStatement.setString(6, user.getRole().name());
			
			preparedStatement.executeUpdate();
			return Optional.of(user);
		}
		catch (SQLException e) {
			System.out.println("Kullanıcı kaydedilirken hata olustu..." + e.getMessage());
		}
		return Optional.empty();
	}
	
	@Override
	public Optional<User> update(User user) {
		sql = "UPDATE tbluser SET name=?,surname=?,email=?,username=?,password=?, role=?::user_role, state=?, " +
				"createdat=?, updatedat=?" +
				" WHERE " +
				"id=?";
		try (PreparedStatement preparedStatement = connectionProvider.getPreparedStatement(sql)) {
			preparedStatement.setString(1, user.getName());
			preparedStatement.setString(2, user.getSurname());
			preparedStatement.setString(3, user.getEmail());
			preparedStatement.setString(4, user.getUsername());
			preparedStatement.setString(5, user.getPassword());
			preparedStatement.setString(6, user.getRole().name());
			preparedStatement.setInt(7,user.getState());
			preparedStatement.setLong(8,user.getCreatedAt());
			preparedStatement.setLong(9,user.getCreatedAt());
			preparedStatement.setLong(10, user.getId());
			preparedStatement.executeUpdate();
		}
		catch (SQLException e) {
			System.out.println("Repository : Kullanıcı güncellenirken hata oluştu. " + e.getMessage());
		}
		return Optional.ofNullable(user);
	}
	
	@Override
	public boolean delete(Long id) {
		sql = "DELETE FROM tbluser WHERE id=?";
		try (PreparedStatement preparedStatement = connectionProvider.getPreparedStatement(sql)) {
			preparedStatement.setLong(1, id);
			preparedStatement.executeUpdate();
		}
		catch (SQLException e) {
			System.out.println("Kullanıcı silinirken hata olustu..." + e.getMessage());
		}
		return true;
	}
	
	@Override
	public List<User> findAll() {
		List<User> users = new ArrayList<>();
		sql = "SELECT * FROM tbluser";
		try (PreparedStatement preparedStatement = connectionProvider.getPreparedStatement(sql);
		     ResultSet rs = preparedStatement.executeQuery()) {
			while (rs.next()) {
				
				long id = rs.getLong("id");
				String name = rs.getString("name");
				String surname = rs.getString("surname");
				String email = rs.getString("email");
				String username = rs.getString("username");
				String password = rs.getString("password");
				ERole role = ERole.valueOf(rs.getString("role")); // ENUM türü
				int state = rs.getInt("state");
				long createdAt = rs.getLong("createdAt");
				long updatedAt = rs.getLong("updatedAt");

// Yeni yapıcıyı kullanarak User nesnesi oluşturuluyor
				users.add(new User(id, name, surname, email, username, password, role, state, createdAt, updatedAt));
				
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return users;
	}
	
	@Override
	public Optional<User> findById(Long bulunacakid) {
		sql = "SELECT * FROM tbluser WHERE id=?";
		try (PreparedStatement preparedStatement = connectionProvider.getPreparedStatement(sql)){
			preparedStatement.setLong(1, bulunacakid);
			try (ResultSet rs = preparedStatement.executeQuery()) {
				if (rs.next()) {
					Long id = rs.getLong("id");
					String name = rs.getString("name");
					String surname = rs.getString("surname");
					String email = rs.getString("email");
					String username = rs.getString("username");
					String password = rs.getString("password");
					ERole role = ERole.valueOf(rs.getString("role")); // ENUM türü
					Integer state = rs.getInt("state");
					Long createdAt = rs.getLong("createdAt");
					Long updatedAt = rs.getLong("updatedAt");

// Yeni yapıcıyı kullanarak User nesnesi oluşturuluyor
					User user = new User(id, name, surname, email, username, password, role, state, createdAt, updatedAt);
					return Optional.of(user);
				}
			}
		}
		catch (SQLException e) {
			System.out.println("veri bulunamadı..."+e.getMessage());
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