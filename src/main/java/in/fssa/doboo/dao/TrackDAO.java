package in.fssa.doboo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import in.fssa.doboo.Interface.TrackInterface;
import in.fssa.doboo.model.TrackEntity;
import in.fssa.doboo.util.ConnectionUtil;

public class TrackDAO implements TrackInterface {

	
	public Set<TrackEntity> findAll() {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Set<TrackEntity> setOfTrack = new HashSet<>();

		try {
			String query = "SELECT * FROM tracks WHERE is_active = 1";
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


	public void createTrack(TrackEntity track,int userId) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			String query = "INSERT INTO tracks (track_name, track_detail, bpm, daw,genre,scale,user_id)VALUES (?,?,?,?,?,?,?)";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setString(1,track.getTrackName());
			ps.setString(2,track.getTrackDetail());
			ps.setInt(3,track.getBpm());
			ps.setString(4,track.getDaw());
			ps.setString(5,track.getGenre());
			ps.setString(6,track.getScale());
			ps.setInt(7,userId);
			
		   ps.executeUpdate();
			System.out.println("track is successfully created");
			
		}catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new RuntimeException();
		}
		finally {
			ConnectionUtil.close(con, ps, rs);
		}

		
	}

	@Override
	public void update(int id, TrackEntity track) {
		Connection con = null;
		PreparedStatement ps = null;
	
		
		try {
			String query = "UPDATE tracks set track_name = ?,track_detail = ?,bpm=?,daw=?,genre=?,scale=? WHERE is_active = 1 AND id = ?";
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
			throw new RuntimeException();
		}
		finally {
			ConnectionUtil.close(con, ps);
		}
		
				
	}

	@Override
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

		return null;
	}

	@Override
	public Set<TrackEntity> findMatchTrackByName() {

		return null;
	}

	@Override
	public Set<TrackEntity> findTracksByAtirstName() {

		return null;
	}

	@Override
	public void create(TrackEntity object) {
		// TODO Auto-generated method stub
		
	}


}
