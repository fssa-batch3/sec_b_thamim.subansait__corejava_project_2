package in.fssa.doboo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import in.fssa.doboo.exception.PersistanceException;
import in.fssa.doboo.model.Assest;
import in.fssa.doboo.util.ConnectionUtil;

public class AssestDAO {
	public void createAssest(Assest assest, int trackId)throws PersistanceException {
	    Connection con = null;
	    PreparedStatement ps = null;
	    ResultSet rs = null;
	    
	    try {
	       
	        String query = "INSERT INTO assets (track_id, image_url,audio_url) VALUES (?,?,?)";
	        con = ConnectionUtil.getConnection();
	        ps = con.prepareStatement(query);
	        ps.setInt(1, trackId);
	        ps.setString(2, assest.getImageUrl());
	        ps.setString(3, assest.getAudioUrl());

	        ps.executeUpdate();
	        System.out.println("media is successufully created");
	   
	    } 
	    catch (SQLException e) {
	        e.printStackTrace();
	        System.out.println(e.getMessage());
	        throw new PersistanceException(e.getMessage());
	    } 
	    
	    finally {
	        ConnectionUtil.close(con, ps, rs);
	    }
	}
	
	public void updateAsset(Assest asset,int trackId) throws PersistanceException {
	    Connection con = null;
	    PreparedStatement ps = null;

	    try {
	        String query = "UPDATE assets SET image_url = ?, audio_url = ? WHERE track_id = ?";
	        con = ConnectionUtil.getConnection();
	        ps = con.prepareStatement(query);
	        ps.setString(1, asset.getImageUrl());
	        ps.setString(2, asset.getAudioUrl());
	        ps.setInt(3, trackId); // Assuming you have an asset_id field in your database

	        int rowsUpdated = ps.executeUpdate();

	        if (rowsUpdated > 0) {
	            System.out.println("Asset is successfully updated");
	        } else {
	            System.out.println("No rows were updated. Asset may not exist.");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        System.out.println(e.getMessage());
	        throw new PersistanceException(e.getMessage());
	    } finally {
	        ConnectionUtil.close(con, ps);
	    }
	}
	
	public Assest findByTrackId(int trackId) throws PersistanceException {
	    Connection con = null;
	    PreparedStatement ps = null;
	    ResultSet rs = null;
	    Assest assets = new Assest();
	    
	    try {
	        String query = "SELECT id, image_url, audio_url FROM assets WHERE track_id = ?";
	        con = ConnectionUtil.getConnection();
	        ps = con.prepareStatement(query);
	        ps.setInt(1, trackId);
	        rs = ps.executeQuery();

	        if (rs.next()) { // Move the cursor to the first row
	            assets.setImageUrl(rs.getString("image_url"));
	            assets.setAudioUrl(rs.getString("audio_url"));
	        } else {
	            // Handle the case where no results were found for the given trackId
	            // You may want to throw an exception or return some default values.
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        System.out.println(e.getMessage());
	        throw new PersistanceException(e.getMessage());
	    } finally {
	        ConnectionUtil.close(con, ps, rs);
	    }

	    return assets;
	}


}
