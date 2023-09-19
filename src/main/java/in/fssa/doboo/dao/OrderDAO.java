package in.fssa.doboo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import in.fssa.doboo.exception.PersistanceException;
import in.fssa.doboo.model.Orders;
import in.fssa.doboo.util.ConnectionUtil;

public class OrderDAO {
	public void createOrder(Orders order)throws PersistanceException {
	    Connection con = null;
	    PreparedStatement ps = null;
	    ResultSet rs = null;
	    
	    try {
	       
	        String query = "INSERT INTO orders (track_id,user_id) VALUES (?,?)";
	        con = ConnectionUtil.getConnection();
	        ps = con.prepareStatement(query);
	        ps.setInt(1, order.getTrackId());
	        ps.setInt(2, order.getUserId());
	        
	        ps.executeUpdate();
	        System.out.println("order is successufully created");
	   
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
	
	 public List<Orders> getOrdersByUserId(int userId) throws PersistanceException {
	        Connection con = null;
	        PreparedStatement ps = null;
	        ResultSet rs = null;
	        List<Orders> ordersList = new ArrayList<>();

	        try {
	            String query = "SELECT id,track_id,user_id FROM orders WHERE user_id=?";
	            con = ConnectionUtil.getConnection();
	            ps = con.prepareStatement(query);
	            ps.setInt(1, userId);
	            rs = ps.executeQuery();

	            while (rs.next()) {
	                Orders order = new Orders();
	                order.setId(rs.getInt("id"));
	                order.setTrackId(rs.getInt("track_id"));
	                order.setUserId(rs.getInt("user_id"));
	                // Set other order attributes if needed
	                ordersList.add(order);
	            }

	            return ordersList;
	        } catch (SQLException e) {
	            e.printStackTrace();
	            System.out.println(e.getMessage());
	            throw new PersistanceException(e.getMessage());
	        } finally {
	            ConnectionUtil.close(con, ps, rs);
	        }
	    }

}
