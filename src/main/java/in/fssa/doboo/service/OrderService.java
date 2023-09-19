package in.fssa.doboo.service;
import java.util.List;

import in.fssa.doboo.dao.OrderDAO;
import in.fssa.doboo.exception.PersistanceException;
import in.fssa.doboo.exception.ServiceException;
import in.fssa.doboo.exception.ValidationException;
import in.fssa.doboo.model.Orders;
import in.fssa.doboo.validator.TrackValidator;
import in.fssa.doboo.validator.UserValidator;

public class OrderService {
    private OrderDAO orderDAO;

    public OrderService() {
        this.orderDAO = new OrderDAO();
    }

    public void createOrder(Orders order) throws PersistanceException, ValidationException {
        try {
        	TrackValidator.isIdValid(order.getTrackId());
        	UserValidator.checkIdExists(order.getUserId());
            orderDAO.createOrder(order);
        } catch (PersistanceException |ValidationException | RuntimeException e) {
            e.printStackTrace();
            throw new ServiceException(e.getMessage());
        }
    }

    public List<Orders> getOrdersByUserId(int userId) throws PersistanceException {
    	List<Orders> orders = null;
    	try {
             orders =orderDAO.getOrdersByUserId(userId);
        } catch (PersistanceException e) {
            e.printStackTrace();
            throw new ServiceException(e.getMessage());
        }
    	return orders;
    }

    // You can add other methods related to orders as needed
}
