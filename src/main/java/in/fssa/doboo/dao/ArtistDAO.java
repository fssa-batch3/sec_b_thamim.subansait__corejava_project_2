package in.fssa.doboo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import in.fssa.doboo.enums.ArtistType;
import in.fssa.doboo.exception.PersistanceException;
import in.fssa.doboo.model.Artist;
import in.fssa.doboo.util.ConnectionUtil;

public class ArtistDAO {
	
	public void createArtist(Artist artist,int userId) throws PersistanceException {
	    Connection con = null;
	    PreparedStatement ps = null;
	    ResultSet rs = null;

	    try {
	        String query = "INSERT INTO artist (type,bio,instagram,facebook,linkedln,spotify,user_id,artist_name) VALUES (?,?,?,?,?,?,?,?)";
	        con = ConnectionUtil.getConnection();
	        ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
	        ps.setString(1, ArtistType.tpye(artist.getType()));
	        ps.setString(2, artist.getBio());
	        ps.setString(3, artist.getInsta());
	        ps.setString(4, artist.getFacebook());
	        ps.setString(5, artist.getLinkedln());
	        ps.setString(6, artist.getSpotify());
	        ps.setInt(7,userId);
	        ps.setString(8, artist.getArtistName());

	        ps.executeUpdate();

	        System.out.println("Artist is successfully created");
	    } catch (SQLException e) {
	        e.printStackTrace();
	        System.out.println(e.getMessage());
	        throw new PersistanceException(e.getMessage());
	    } finally {
	        ConnectionUtil.close(con, ps, rs);
	    }
	}
	
	public void updateArtistDetails(Artist artist,int userId) throws PersistanceException {
	    Connection con = null;
	    PreparedStatement ps = null;
	    
	    try {
	        con = ConnectionUtil.getConnection();
	        String query = "UPDATE artist SET type=?, bio=?, instagram=?, facebook=?, linkedln=?, spotify=?, artist_name=? WHERE user_id=?";
	        ps = con.prepareStatement(query);
	        ps.setString(1, ArtistType.tpye(artist.getType()));
	        ps.setString(2, artist.getBio());
	        ps.setString(3, artist.getInsta());
	        ps.setString(4, artist.getFacebook());
	        ps.setString(5, artist.getLinkedln());
	        ps.setString(6, artist.getSpotify());
	        ps.setString(7, artist.getArtistName());
	        ps.setInt(8, userId);

	        int rowsAffected = ps.executeUpdate();

	        if (rowsAffected > 0) {
	            System.out.println("Artist details updated successfully");
	        } else {
	            System.out.println("No changes made to the artist details");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        System.out.println(e.getMessage());
	        throw new PersistanceException(e.getMessage());
	    } finally {
	        ConnectionUtil.close(con, ps, null);
	    }
	}

	
	public Artist findArtistByUserId(int userId) throws PersistanceException {
	    Connection con = null;
	    PreparedStatement ps = null;
	    ResultSet rs = null;
	    Artist artist = null;

	    try {
	        String query = "SELECT id, type, artist_name, bio, instagram, facebook, linkedln, spotify FROM artist WHERE user_id = ?";
	        con = ConnectionUtil.getConnection();
	        ps = con.prepareStatement(query);
	        ps.setInt(1, userId);
	        rs = ps.executeQuery();

	        if (rs.next()) {
	            artist = new Artist();
	            artist.setId(rs.getInt("id"));
	            artist.setType(ArtistType.getType(rs.getString("type")));
	            artist.setArtistName(rs.getString("artist_name"));
	            artist.setBio(rs.getString("bio"));
	            artist.setInsta(rs.getString("instagram"));
	            artist.setFacebook(rs.getString("facebook"));
	            artist.setLinkedln(rs.getString("linkedln"));
	            artist.setSpotify(rs.getString("spotify"));
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        System.out.println(e.getMessage());
	        throw new PersistanceException(e.getMessage());
	    } finally {
	        ConnectionUtil.close(con, ps, rs);
	    }

	    return artist;
	}
	
	public List<Artist> getAllArtists() throws PersistanceException {
	    Connection con = null;
	    PreparedStatement ps = null;
	    ResultSet rs = null;
	    List<Artist> artists = new ArrayList<>();

	    try {
	        String query = "SELECT id, type, artist_name, bio, instagram, facebook, linkedln, spotify, user_id FROM artist";
	        con = ConnectionUtil.getConnection();
	        ps = con.prepareStatement(query);
	        rs = ps.executeQuery();

	        while (rs.next()) {
	            Artist artist = new Artist();
	            artist.setId(rs.getInt("id"));
	            artist.setType(ArtistType.getType(rs.getString("type")));
	            artist.setArtistName(rs.getString("artist_name"));
	            artist.setBio(rs.getString("bio"));
	            artist.setInsta(rs.getString("instagram"));
	            artist.setFacebook(rs.getString("facebook"));
	            artist.setLinkedln(rs.getString("linkedln"));
	            artist.setSpotify(rs.getString("spotify"));
	            artists.add(artist);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        System.out.println(e.getMessage());
	        throw new PersistanceException(e.getMessage());
	    } finally {
	        ConnectionUtil.close(con, ps, rs);
	    }

	    return artists;
	}


}
