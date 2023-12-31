package in.fssa.doboo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Base64;
import java.util.HashSet;
import java.util.Set;

import in.fssa.doboo.model.UserEntity;
import in.fssa.doboo.util.ConnectionUtil;
import in.fssa.doboo.util.PasswordEncryptUtil;
import in.fssa.doboo.model.User;
import in.fssa.doboo.interfaces.UserInterface;
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
	
	
	public Set<UserEntity> findAll() throws PersistanceException  {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Set<UserEntity> setOfUser = new HashSet<>();

		try {
			String query = "SELECT id,name,is_active,email,password,artist_name,dob,role FROM users WHERE is_active = 1";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();

			while (rs.next()) {
				UserEntity user = new UserEntity();
				user.setId(rs.getInt("id"));
				user.setName(rs.getString("name"));
				user.setActive(rs.getBoolean("is_active"));
				user.setEmail(rs.getString("email"));
//				user.setPassword(rs.getString("password"));
				user.setArtistName(rs.getString("artist_name"));
				user.setDob(rs.getString("dob"));
				user.setRole(rs.getString("role"));
				
				setOfUser.add(user);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new PersistanceException(e.getMessage());
		} finally {
			ConnectionUtil.close(con, ps, rs);
		}
		return setOfUser;


	}
	
	
	// userEmial already exists then throw new exception
	/**
	 * 
	 * @param email
	 * @throws Exception
	 */
	
			public void emailExists(String email) throws PersistanceException{
				Connection conn = null;
				PreparedStatement pre = null;
				ResultSet rs = null;

				try {
					String query = "SELECT email FROM users WHERE email = ? AND is_active = 1";
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
					throw new PersistanceException(e.getMessage());
				} finally {
					ConnectionUtil.close(conn, pre, rs);
				}
			}
			// for getting password for the email
			
			public UserEntity logIn(String email) throws PersistanceException{
				Connection conn = null;
				PreparedStatement pre = null;
				ResultSet rs = null;
				UserEntity user = null;
				try {
					String query = "SELECT id,email,password,role FROM users WHERE email = ? AND is_active = 1";
					conn = ConnectionUtil.getConnection();
					pre = conn.prepareStatement(query);
					pre.setString(1, email);
					rs = pre.executeQuery();
					if (rs.next()) {
						user = new UserEntity();
						user.setId(rs.getInt("id"));
						user.setPassword(rs.getString("password"));
						user.setEmail(rs.getString("email"));
						user.setRole(rs.getString("role"));
					}else {
						throw new PersistanceException("email doesn't exits");
					}
				} catch (SQLException e) {
					e.printStackTrace();
					System.out.println(e.getMessage());
					throw new PersistanceException(e.getMessage());
				} finally {
					ConnectionUtil.close(conn, pre, rs);
				}
				return user;
			}
	
	// create Method for user to ad record to the database.
	/**
	 * @param newUser object
	 * @throws PersistanceException 
	 */
			
	public void create(UserEntity newUser) throws PersistanceException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		
		try {
			String query = "INSERT INTO users (name, password, dob, email,artist_name,role)VALUES (?,?,?,?,?,?)";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setString(1,newUser.getName());
			
			// this part is for the to encrypt the password using base64 in java default class
	        String hashPassword = PasswordEncryptUtil.encrypt(newUser.getPassword());
			ps.setString(2, hashPassword);
			ps.setString(3,newUser.getDob());
			ps.setString(4,newUser.getEmail());
			ps.setString(5,newUser.getArtistName());
			ps.setString(6,newUser.getRole());
			
		   ps.executeUpdate();
			System.out.println("User is successfully created");
			
		}catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new PersistanceException(e.getMessage());
		}
		finally {
			ConnectionUtil.close(con, ps, rs);
		}
		
		

	}
	/**
	 * @param id,updatedUser
	 * @throws PersistanceException 
	 */
	public void update(int id,  UserEntity updatedUser) throws PersistanceException {
		Connection con = null;
		PreparedStatement ps = null;		

		try {
			String query = "UPDATE users set name = ?,artist_name = ? WHERE is_active = 1 AND id = ?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setString(1,updatedUser.getName());
			ps.setString(2,updatedUser.getArtistName());
			ps.setInt(3,id);
			
		   ps.executeUpdate();
			System.out.println("User details is successfully updated");
			
		}catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new PersistanceException(e.getMessage());
		}
		finally {
			ConnectionUtil.close(con, ps);
		}
		
		}
	
	public void updatePassword(int id, String newPassword) throws PersistanceException {
	    Connection con = null;
	    PreparedStatement ps = null;

	    try {
	        String query = "UPDATE users SET password = ? WHERE is_active = 1 AND id = ?";
	        con = ConnectionUtil.getConnection();
	        ps = con.prepareStatement(query);
	        String hashPassword = PasswordEncryptUtil.encrypt(newPassword);
	        ps.setString(1, hashPassword);
	        ps.setInt(2, id);

	        int rowsUpdated = ps.executeUpdate();
	        
	        if (rowsUpdated > 0) {
	            System.out.println("Password successfully updated");
	        } else {
	            System.out.println("User not found or password update failed");
	            // Handle this case according to your application's requirements
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	        System.out.println(e.getMessage());
	        throw new PersistanceException(e.getMessage());
	    } finally {
	        ConnectionUtil.close(con, ps);
	    }
	}

	
	public void setArtistNameAndRole(int userId, String ArtistName ) throws PersistanceException {
		Connection con = null;
		PreparedStatement ps = null;		

		try {
			String query = "UPDATE users set artist_name = ?,role = ? WHERE is_active = 1 AND id = ?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setString(1,ArtistName);
			ps.setString(2, "seller");
			ps.setInt(3,userId);
			
		   ps.executeUpdate();
			System.out.println("User ArtistName is successfully Set");
			
		}catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new PersistanceException(e.getMessage());
		}
		finally {
			ConnectionUtil.close(con, ps);
		}
		
		}
	/**
	 * @param id
	 * @throws PersistanceException 
	 */

	public void delete(int id) throws PersistanceException {
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
			throw new PersistanceException(e.getMessage());
		}
		finally {
			ConnectionUtil.close(con, ps);
		}
	}
	/**
	 * @param id 
	 * @return user object
	 * @throws PersistanceException 
	 */

	public UserEntity findById(int id) throws PersistanceException {
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		UserEntity user = null;
		
		try {
			String query = "SELECT id,name,artist_name,is_active,email,password,dob FROM users WHERE is_active = 1  AND id = ? ";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, id);
			
			rs = ps.executeQuery();
			if(rs.next()) {
				user = new UserEntity();
				user.setId(rs.getInt("id"));
				user.setPassword(rs.getString("password"));
				user.setName(rs.getString("name"));
				user.setArtistName(rs.getString("artist_name"));
				user.setActive(rs.getBoolean("is_active"));
				user.setEmail(rs.getString("email"));
				user.setDob(rs.getString("dob"));

			}
		}catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new PersistanceException(e.getMessage());
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
	/**
	 * @param artist name
	 * @return user
	 */
	public UserEntity findByArtistName(String artistName) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		UserEntity user = null;
		try {
			String query = "SELECT id,name,email,password,is_active,artist_name,dob FROM users WHERE is_active = 1 AND artist_name = ?";
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(query);
			ps.setString(1, artistName);
			rs = ps.executeQuery();
			if (rs.next()) {
				user = new UserEntity();
				user.setId(rs.getInt("id"));
				user.setName(rs.getString("name"));
				user.setEmail(rs.getString("email"));
//				user.setPassword(rs.getString("password"));
				user.setActive(rs.getBoolean("is_active"));
				user.setArtistName(rs.getString("artist_name"));
				user.setDob(rs.getString("dob"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new RuntimeException(e.getMessage());
		} finally {
			ConnectionUtil.close(conn, ps, rs);
		}
		return user;
	}

	@Override
	/**
	 * @param emial
	 * @return user
	 */
	public UserEntity findByEmailId(String email) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		UserEntity user = null;
		try {
			String query = "SELECT id,name,email,password,is_active,artist_name,dob FROM users WHERE is_active = 1 AND email = ?";
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(query);
			ps.setString(1, email);
			rs = ps.executeQuery();
			if (rs.next()) {
				user = new UserEntity();
				user.setId(rs.getInt("id"));
				user.setName(rs.getString("name"));
				user.setEmail(rs.getString("email"));
//				user.setPassword(rs.getString("password"));
				user.setActive(rs.getBoolean("is_active"));
				user.setArtistName(rs.getString("artist_name"));
				user.setDob(rs.getString("dob"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new RuntimeException(e.getMessage());
		} finally {
			ConnectionUtil.close(conn, ps, rs);
		}
		return user;
	}

}
