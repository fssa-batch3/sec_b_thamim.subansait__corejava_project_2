package in.fssa.doboo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import in.fssa.doboo.util.ConnectionUtil;
import java.sql.Timestamp;

public class TrackPriceDAO {
/**
 * 
 * @param track_id
 * @param price
 * @param updateDate
 */
	public void createTrackPrice(int track_id,int price,Timestamp updateDate) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			String query = "INSERT INTO track_prices (track_id, price,start_date)VALUES (?,?,?)";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1,track_id);
			ps.setInt(2,price);
			ps.setTimestamp(3, updateDate);
			
		   ps.executeUpdate();
			System.out.println("trackPrice is successfully created");
			
		}catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new RuntimeException();
		}
		finally {
			ConnectionUtil.close(con, ps, rs);
		}

		
	}
	/**
	 * 
	 * @param updateDate
	 * @param track_id
	 * @throws RuntimeException
	 */
	public void update(Timestamp updateDate, int track_id) throws RuntimeException {

		Connection con = null;
		PreparedStatement ps = null;

		try {
			String query = "UPDATE track_prices SET end_date = ? WHERE track_id = ? AND end_date IS NULL";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);

			ps.setTimestamp(1, updateDate);
			ps.setInt(2, track_id);
			ps.executeUpdate();
			System.out.println("Price date updated");

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		} finally {
			ConnectionUtil.close(con, ps);
		}
	}
	/**
	 * 
	 * @param track_id
	 * @return
	 * @throws RuntimeException
	 */
	
	public Timestamp getDate(int track_id) throws RuntimeException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Timestamp updateDate = null;

		try {
			String query = "SELECT modified_at FROM tracks WHERE id = ?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, track_id);
			rs = ps.executeQuery();

			if (rs.next()) {
				updateDate = rs.getTimestamp("modified_at");
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new RuntimeException(e.getMessage());
		} finally {
			ConnectionUtil.close(con, ps, rs);
		}
		return updateDate;
	}
}
