package cybersoft.java12.crmapp.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import cybersoft.java12.crmapp.dbconnection.MySqlConnection;
import cybersoft.java12.crmapp.model.User;

public class UserRepository {
	public User findCustomerByCode(String email, String password) {
		User user = null;
		try {
			Connection connection = MySqlConnection.getConnection();
			String query = "SELECT id, email, password, name, address, phone, role_id "
					+ "FROM user "
					+ "Where email=? and password =?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, email);
			statement.setString(2, password);
			ResultSet resultSet =statement.executeQuery();
			if(resultSet.next()) {
				user = new User();
				user.setId(resultSet.getInt("id"));
				user.setEmail(resultSet.getString("email"));
				user.setPassword(resultSet.getString("password"));
				user.setName(resultSet.getString("name"));
				user.setAddress(resultSet.getString("address"));
				user.setPhone(resultSet.getString("phone"));
				user.setRole_id(resultSet.getInt("role_id"));
			}
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return user;
	}
}
