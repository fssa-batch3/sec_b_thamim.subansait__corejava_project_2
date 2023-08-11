package in.fssa.doboo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import in.fssa.doboo.model.UserEntity;
import in.fssa.doboo.util.ConnectionUtil;

import in.fssa.doboo.model.User;
import in.fssa.doboo.Interface.UserInterface;
import in.fssa.doboo.exception.PersistanceException;
import in.fssa.doboo.exception.ValidationException;

public class UserDAO implements UserInterface {
	
	/*
	 * @detail 
	 * 
	 * This method is used to find all user records from the database table using connection to the database.
	 * 
	 * @param 
	 * No need of pass any argument
	 *
	 */
		
	// Find all methods is used to find all user
	
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
				user.setName(rs.getString("name"));
				user.setActive(rs.getBoolean("is_active"));
				user.setEmail(rs.getString("email"));
				user.setPassword(rs.getString("password"));
				user.setArtistName(rs.getString("artist_name"));
				user.setDob(rs.getString("dob"));
				user.setRole(rs.getString("role"));
				
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


	}
	
	// userEmial already exists then throw new exception
	
			public void emailExists(String email) throws Exception{
				Connection conn = null;
				PreparedStatement pre = null;
				ResultSet rs = null;

				try {
					String query = "Select * From users Where email = ? AND is_active = 1";
					conn = ConnectionUtil.getConnection();
					pre = conn.prepareStatement(query);
					pre.setString(1, email);
					rs = pre.executeQuery();
					if (rs.next()) {
						throw new PersistanceException("Email already exists");
					}
				} catch (SQLException e) {
					e.printStackTrace();
					System.out.println(e.getMessage());
					throw new Exception();
				} finally {
					ConnectionUtil.close(conn, pre, rs);
				}
			}
	
	// create Method for user to ad record to the database.
	
	public void create(UserEntity newUser) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		
		try {
			String query = "INSERT INTO users (name, password, dob, email,artist_name,role)VALUES (?,?,?,?,?,?)";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setString(1,newUser.getName());
			ps.setString(2,newUser.getPassword());
			ps.setString(3,newUser.getDob());
			ps.setString(4,newUser.getEmail());
			ps.setString(5,newUser.getArtistName());
			ps.setString(6,newUser.getRole());
			
		   ps.executeUpdate();
			System.out.println("User is successfully created");
			
		}catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new RuntimeException();
		}
		finally {
			ConnectionUtil.close(con, ps, rs);
		}
		

	}

	public void update(int id,  UserEntity updatedUser) {
		Connection con = null;
		PreparedStatement ps = null;		

		try {
			String query = "UPDATE users set name = ?,artist_name = ?,password=? WHERE is_active = 1 AND id = ?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setString(1,updatedUser.getName());
			ps.setString(2,updatedUser.getArtistName());
			ps.setString(3,updatedUser.getPassword());
			ps.setInt(4,id);
			
		   ps.executeUpdate();
			System.out.println("User details is successfully updated");
			
		}catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new RuntimeException();
		}
		finally {
			ConnectionUtil.close(con, ps);
		}
		
		}
	

	public void delete(int id) {
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			String query = "UPDATE users SET is_active = 0 WHERE is_active = 1 AND id = ?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			
			ps.setInt(1, id);
			ps.executeUpdate();
			
			System.out.println("User has been deleted successfully");
			
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new RuntimeException();
		}
		finally {
			ConnectionUtil.close(con, ps);
		}
	}

	public UserEntity findById(int id) {
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		UserEntity user = null;
		
		try {
			String query = "SELECT * FROM users WHERE is_active = 1  AND id = ? ";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, id);
			
			rs = ps.executeQuery();
			if(rs.next()) {
				user = new UserEntity();
				user.setId(rs.getInt("id"));
				user.setName(rs.getString("name"));
				user.setArtistName(rs.getString("artist_name"));
				user.setActive(rs.getBoolean("is_active"));
				user.setEmail(rs.getString("email"));
				user.setPassword(rs.getString("password"));
				user.setDob(rs.getString("dob"));
			}
		}catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new RuntimeException();
		}
		finally {
			ConnectionUtil.close(con, ps, rs);
		}
		return user;
		
	}

//	public User findByEmailId(String email) {
//		Set<User> userList = UserList.listOfUser;
//		for (User user : userList) {
//			if (user != null && user.getEmail() == email) {
//				return user;
//			}
//		}
//		return null;
//	}

	public int count() {

		return 0;
	}
	
	
	@Override
	public UserEntity findByArtistName(String artistName) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		UserEntity user = null;
		try {
			String query = "SELECT * FROM users WHERE is_active = 1 AND artist_name = ?";
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(query);
			ps.setString(1, artistName);
			rs = ps.executeQuery();
			if (rs.next()) {
				user = new UserEntity();
				user.setId(rs.getInt("id"));
				user.setName(rs.getString("name"));
				user.setEmail(rs.getString("email"));
				user.setPassword(rs.getString("password"));
				user.setActive(rs.getBoolean("is_active"));
				user.setArtistName(rs.getString("artist_name"));
				user.setDob(rs.getString("dob"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new RuntimeException();
		} finally {
			ConnectionUtil.close(conn, ps, rs);
		}
		return user;
	}

	@Override
	public UserEntity findByEmailId(String email) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		UserEntity user = null;
		try {
			String query = "SELECT * FROM users WHERE is_active = 1 AND email = ?";
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(query);
			ps.setString(1, email);
			rs = ps.executeQuery();
			if (rs.next()) {
				user = new UserEntity();
				user.setId(rs.getInt("id"));
				user.setName(rs.getString("name"));
				user.setEmail(rs.getString("email"));
				user.setPassword(rs.getString("password"));
				user.setActive(rs.getBoolean("is_active"));
				user.setArtistName(rs.getString("artist_name"));
				user.setDob(rs.getString("dob"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new RuntimeException();
		} finally {
			ConnectionUtil.close(conn, ps, rs);
		}
		return user;
	}

}
