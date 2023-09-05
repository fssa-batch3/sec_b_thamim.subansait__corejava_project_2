package in.fssa.doboo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

import javax.management.RuntimeErrorException;

import in.fssa.doboo.interfaces.TrackInterface;
import in.fssa.doboo.exception.NoTrackFoundException;
import in.fssa.doboo.exception.ValidationException;
import in.fssa.doboo.model.TrackEntity;
import in.fssa.doboo.util.ConnectionUtil;

public class TrackDAO implements TrackInterface {
	/**
	 * @return tracks
	 * @throws runtime Exception
	 */
	
	public Set<TrackEntity> findAll() {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Set<TrackEntity> setOfTrack = new HashSet<>();

		try {
			String query = "SELECT id,bpm,track_name,track_detail,daw,genre,scale FROM tracks WHERE is_active = 1";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();

			while (rs.next()) {
				TrackEntity track = new TrackEntity();
				track.setId(rs.getInt("id"));
				track.setBpm(rs.getInt("bpm"));
				track.setTrackName(rs.getString("track_name"));
				track.setTrackDetail(rs.getString("track_detail"));
				track.setDaw(rs.getString("daw"));
				track.setGenre(rs.getString("genre"));
				track.setScale(rs.getString("scale"));	
				setOfTrack.add(track);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new RuntimeException();
		} finally {
			ConnectionUtil.close(con, ps, rs);
		}
		return setOfTrack;
		
	}
	/**
	 * 
	 * @param id
	 * @throws RuntimeException
	 */
	
	public void checkIdExists(int id) throws RuntimeException {
	    Connection con = null;
	    PreparedStatement ps = null;
	    ResultSet rs = null;
	    try {
	        String query = "SELECT EXISTS (SELECT 1 FROM tracks WHERE is_active = 1 AND id = ?)";
	        con = ConnectionUtil.getConnection();
	        ps = con.prepareStatement(query);
	        ps.setInt(1, id);
	        rs = ps.executeQuery();
	        if (!rs.next() || rs.getInt(1) == 0) {
	            throw new RuntimeException("track not found");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        throw new RuntimeException(e.getMessage());
	    } finally {
	        ConnectionUtil.close(con, ps, rs);
	    }
	}

	
	/**
	 * @param track object, userID
	 * @return track id
	 * @throws runtime Exception
	 */
	
	
	


	public int createTrack(TrackEntity track, int userId) {
	    Connection con = null;
	    PreparedStatement ps = null;
	    ResultSet rs = null;
	    int generatedId = -1;

	    try {
	       
	        String query = "INSERT INTO tracks (track_name, track_detail, bpm, daw, genre, scale, user_id) VALUES (?, ?, ?, ?, ?, ?, ?)";
	        con = ConnectionUtil.getConnection();

	        ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

	        ps.setString(1, track.getTrackName());
	        ps.setString(2, track.getTrackDetail());
	        ps.setInt(3, track.getBpm());
	        ps.setString(4, track.getDaw());
	        ps.setString(5, track.getGenre());
	        ps.setString(6, track.getScale());
	        ps.setInt(7, userId);

	        ps.executeUpdate();
	        rs = ps.getGeneratedKeys();
	        if (rs.next()) {
	            generatedId = rs.getInt(1);
	        } else {
	            System.out.println("Track is successfully created");
	        }

	    } 
	    catch (SQLException e) {
	        e.printStackTrace();
	        System.out.println(e.getMessage());
	        throw new RuntimeException(e);
	    } 
	    
	    finally {
	        ConnectionUtil.close(con, ps, rs);
	    }
	    return generatedId;
	}

	// Method to check if a user exists in the users table
	public boolean isUserExists(int userId) {
	    Connection con = null;
	    PreparedStatement ps = null;
	    ResultSet rs = null;
	    boolean res = false;

	    try {
	        String query = "SELECT EXISTS (SELECT 1 FROM users WHERE id = ?)";
	        con = ConnectionUtil.getConnection();
	        ps = con.prepareStatement(query);
	        ps.setInt(1, userId);
	        rs = ps.executeQuery();

	        if (rs.next()) {
	            return res = rs.getBoolean(1); // Returns true if user exists, false if not
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	        throw new RuntimeException(e);
	    } finally {
	        ConnectionUtil.close(con, ps, rs);
	    }

	    return res; // Default to false if an exception occurred
	}

	// Method to check if a track name exists for the given user
	public boolean isTrackNameExistsForUser(String trackName, int userId) {
	    Connection con = null;
	    PreparedStatement ps = null;
	    ResultSet rs = null;
	    boolean res = false;
	    try {
	        String query = "SELECT EXISTS (SELECT 1 FROM tracks WHERE track_name = ? AND user_id = ?)";
	        con = ConnectionUtil.getConnection();
	        ps = con.prepareStatement(query);
	        ps.setString(1, trackName);
	        ps.setInt(2, userId);
	        rs = ps.executeQuery();

	        if (rs.next()) {
	            return res=rs.getBoolean(1); // Returns true if track name exists for the user, false if not
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        ConnectionUtil.close(con, ps, rs);
	    }

	    return res; // Default to false if an exception occurred
	}


	@Override
	/**
	 * @param id,track
	 */
	public void update(int id, TrackEntity track) {
		Connection con = null;
		PreparedStatement ps = null;
	
		
		try {
			String query = "UPDATE tracks set track_name = ?,track_detail = ?,bpm=?,daw=?,genre=?,scale=?,modified_at = NOW() WHERE is_active = 1 AND id = ?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setString(1,track.getTrackName());
			ps.setString(2,track.getTrackDetail());
			ps.setInt(3,track.getBpm());
			ps.setString(4,track.getDaw());
			ps.setString(5,track.getGenre());
			ps.setString(6,track.getScale());
			ps.setInt(7,id);
			
		   ps.executeUpdate();
			System.out.println("track details is successfully updated");
			
		}catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new RuntimeException(e);
		}
		finally {
			ConnectionUtil.close(con, ps);
		}
		
				
	}

	@Override
	/**
	 * @param id
	 * @throws runtime Exception
	 */
	public void delete(int id) {

		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			String query = "UPDATE tracks SET is_active = 0 WHERE is_active = 1 AND id = ?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			
			ps.setInt(1, id);
			ps.executeUpdate();
			
			System.out.println("track has been deleted successfully");
			
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new RuntimeException();
		}
		finally {
			ConnectionUtil.close(con, ps);
		}
	}
	// this 
	@Override
	public TrackEntity findById(int id) {
	    Connection con = null;
	    PreparedStatement ps = null;
	    ResultSet rs = null;
	    TrackEntity track = null;

	    try {
	        String query = "SELECT id, bpm, track_name, track_detail, daw, genre, scale FROM tracks WHERE is_active = 1 AND id = ?";
	        con = ConnectionUtil.getConnection();
	        ps = con.prepareStatement(query);

	        ps.setInt(1, id);
	        rs = ps.executeQuery();

	        if (rs.next()) {
	            track = new TrackEntity();
	            track.setId(rs.getInt("id"));
	            track.setTrackName(rs.getString("track_name"));
	            track.setTrackDetail(rs.getString("track_detail"));
	            track.setScale(rs.getString("scale"));
	            track.setGenre(rs.getString("genre"));
	            track.setDaw(rs.getString("daw"));
	            track.setBpm(rs.getInt("bpm"));
	        }

	        if (track == null) {
	            throw new NoTrackFoundException("No track has been found with the given ID");
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	        throw new RuntimeException(e.getMessage());
	    } finally {
	        ConnectionUtil.close(con, ps, rs);
	    }

	    return track;
	}


	/**
	 * @param track name
	 * @return tracks
	 * @throws runtime Exception
	 */
	public Set<TrackEntity> findMatchTrackByName(String trackName) {
	    Connection con = null;
	    PreparedStatement ps = null;
	    ResultSet rs = null;
	    Set<TrackEntity> tracks = new HashSet<>();

	    try {
	        String query = "SELECT id,bpm,track_name,track_detail,daw,genre,scale FROM tracks WHERE is_active = 1 AND track_name = ?";
	        con = ConnectionUtil.getConnection();
	        ps = con.prepareStatement(query);

	        ps.setString(1, trackName);
	        rs = ps.executeQuery();

	        while (rs.next()) {
	            TrackEntity track = new TrackEntity();
	            track.setId(rs.getInt("id"));
	            track.setTrackName(rs.getString("track_name"));
	            track.setTrackDetail(rs.getString("track_detail"));
	            track.setScale(rs.getString("scale"));
	            track.setGenre(rs.getString("genre"));
	            track.setDaw(rs.getString("daw"));
	            track.setBpm(rs.getInt("bpm"));

	            tracks.add(track);
	        }

	        if (tracks.isEmpty()) {
	            throw new NoTrackFoundException("No track has been found with the given name");
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	        throw new RuntimeException(e.getMessage());
	    } finally {
	        ConnectionUtil.close(con, ps, rs);
	    }
	    return tracks;
	}



	@Override
	/**
	 * @param artist name
	 * @return tracks
	 * @throws runtime Exception
	 */
	public Set<TrackEntity> findTracksByAtirstName(String artistName) {
	    Connection con = null;
	    PreparedStatement ps = null;
	    ResultSet rs = null;
	    Set<TrackEntity> tracks = new HashSet<>();

	    try {
	        String query = "SELECT t.* FROM tracks t " +
	                       "JOIN users u ON t.user_id = u.id " +
	                       "WHERE t.is_active = 1 AND u.artist_name = ?";
	        con = ConnectionUtil.getConnection();
	        ps = con.prepareStatement(query);

	        ps.setString(1, artistName);
	        rs = ps.executeQuery();

	        while (rs.next()) {
	            TrackEntity track = new TrackEntity();
	            track.setId(rs.getInt("id"));
	            track.setTrackName(rs.getString("track_name"));
	            track.setTrackDetail(rs.getString("track_detail"));
	            track.setScale(rs.getString("scale"));
	            track.setGenre(rs.getString("genre"));
	            track.setDaw(rs.getString("daw"));
	            track.setBpm(rs.getInt("bpm"));

	            tracks.add(track);
	        }
	        if (tracks.isEmpty()) {
	            throw new NoTrackFoundException("No tracks found for the artist");
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	        throw new RuntimeException(e.getMessage());
	    } finally {
	        ConnectionUtil.close(con, ps, rs);
	    }
	    return tracks;
	}

	@Override
	public void create(TrackEntity object) {
		// TODO Auto-generated method stub
		
	}
	
	public Set<TrackEntity> findTracksByUserId(int userId) {
	    Connection con = null;
	    PreparedStatement ps = null;
	    ResultSet rs = null;
	    Set<TrackEntity> tracks = new HashSet<>();

	    try {
	        String query = "SELECT * FROM tracks WHERE user_id = ? AND is_active = 1";
	        con = ConnectionUtil.getConnection();
	        ps = con.prepareStatement(query);

	        ps.setInt(1, userId);
	        rs = ps.executeQuery();

	        while (rs.next()) {
	            TrackEntity track = new TrackEntity();
	            track.setId(rs.getInt("id"));
	            track.setTrackName(rs.getString("track_name"));
	            track.setTrackDetail(rs.getString("track_detail"));
	            track.setScale(rs.getString("scale"));
	            track.setGenre(rs.getString("genre"));
	            track.setDaw(rs.getString("daw"));
	            track.setBpm(rs.getInt("bpm"));

	            tracks.add(track);
	        }
	        if (tracks.isEmpty()) {
	            throw new NoTrackFoundException("No tracks found for the user");
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	        throw new RuntimeException(e.getMessage());
	    } finally {
	        ConnectionUtil.close(con, ps, rs);
	    }
	    return tracks;
	}

	


}
