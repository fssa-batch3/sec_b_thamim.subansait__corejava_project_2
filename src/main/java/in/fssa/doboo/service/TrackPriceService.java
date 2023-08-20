package in.fssa.doboo.service;

import java.sql.SQLException;
import java.sql.Timestamp;

import in.fssa.doboo.dao.TrackPriceDAO;
import in.fssa.doboo.exception.ValidationException;
import in.fssa.doboo.validator.TrackValidator;

public class TrackPriceService {
/**
 * 
 * @param uDate
 * @param track_id
 * @param price
 * @throws ValidationException
 * @throws RuntimeException
 */
	public void create(Timestamp uDate, int track_id, int price) throws ValidationException, RuntimeException {
		
		try {
			if(price<=0 && price <6000)
				throw new ValidationException("price cannot be 0 or greater than 6000");
			
			TrackValidator.isIdValid(track_id);	
			TrackPriceDAO productPriceDao = new TrackPriceDAO();	
			productPriceDao.createTrackPrice(track_id, price, uDate);
				
		}catch(ValidationException e) {
			throw new RuntimeException(e.getMessage());
			
		}	
	}
	/**
	 * 
	 * @param track_id
	 * @return
	 * @throws ValidationException
	 * @throws RuntimeException
	 */
	
	public Timestamp getDate(int track_id) throws ValidationException, RuntimeException {
		Timestamp d = null;
	try {
		 TrackValidator.isIdValid(track_id);
		 TrackPriceDAO productPriceDao = new TrackPriceDAO();	
			d = productPriceDao.getDate(track_id);
				
		}catch(ValidationException e) {
			throw new RuntimeException(e.getMessage());
			
		}	
		return d;
	}
	
public void update(Timestamp uDate, int trackId, int price) throws ValidationException, RuntimeException {
		
		try {
			TrackValidator.isIdValid(trackId);	
			TrackPriceDAO trackPriceDao = new TrackPriceDAO();
			trackPriceDao.update(uDate, trackId);
			trackPriceDao.createTrackPrice(trackId,price,uDate);
				
		}catch(ValidationException e) {
			throw new RuntimeException(e.getMessage());	
		}
	}
}
