package in.fssa.doboo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import in.fssa.doboo.model.UserEntity;
import in.fssa.doboo.util.ConnectionUtil;

public class UserDAO {
	
	public Set<UserEntity> findAll() throws RuntimeException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Set<UserEntity> setOfUser = new HashSet<>();

		try {
			String query = "SELECT * FROM users WHERE is_active = 1";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();

			while (rs.next()) {
				UserEntity user = new UserEntity();
				user.setId(rs.getInt("id"));
				user.setName(rs.getString("first_name"));
				user.setActive(rs.getBoolean("is_active"));
				user.setEmail(rs.getString("email"));
				user.setPassword(rs.getString("password"));
				user.setArtistName(rs.getString("artist_name"));
				user.setDob(rs.getString("artist_name"));
				
				setOfUser.add(user);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new RuntimeException();
		} finally {
			ConnectionUtil.close(con, ps, rs);
		}
		return setOfUser;
//		Set<User> userList = UserList.listOfUser;

//		return userList;

	}

}
