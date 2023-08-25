package in.fssa.doboo.service;

import java.sql.SQLException;
import java.sql.Timestamp;

import in.fssa.doboo.dao.TrackPriceDAO;
import in.fssa.doboo.exception.ValidationException;
import in.fssa.doboo.validator.TrackValidator;

public class TrackPriceService {
/**
 * 
 * @param updateDate
 * @param trackId
 * @param price
 * @throws ValidationException
 * @throws RuntimeException
 */
	public void createTrackPrice(Timestamp updateDate, int trackId, int price) throws ValidationException, RuntimeException {
		
		try {
			
			TrackValidator.isIdValid(trackId);	
			TrackPriceDAO trackPriceDao = new TrackPriceDAO();	
			trackPriceDao.createTrackPrice(trackId, price, updateDate);
				
		}catch(ValidationException e) {
			throw new RuntimeException(e.getMessage());
			
		}	
	}
	/**
	 * 
	 * @param trackId
	 * @return
	 * @throws ValidationException
	 * @throws RuntimeException
	 */
	
	public Timestamp getTrackByDate(int trackId) throws ValidationException, RuntimeException {
		Timestamp d = null;
	try {
		 TrackValidator.isIdValid(trackId);
		 TrackPriceDAO trackPriceDao = new TrackPriceDAO();	
			d = trackPriceDao.getDate(trackId);
				
		}catch(ValidationException e) {
			throw new RuntimeException(e.getMessage());
			
		}	
		return d;
	}
	
public void updateTrackPrice(Timestamp upateDate, int trackId, int price) throws ValidationException, RuntimeException {
		
		try {
			TrackValidator.isIdValid(trackId);	
			TrackPriceDAO trackPriceDao = new TrackPriceDAO();
			trackPriceDao.update(upateDate, trackId);
			trackPriceDao.createTrackPrice(trackId,price,upateDate);
				
		}catch(ValidationException e) {
			throw new RuntimeException(e.getMessage());	
		}
	}
}
