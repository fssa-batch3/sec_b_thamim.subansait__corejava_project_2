package in.fssa.doboo.service;

import java.sql.Timestamp;
import java.util.Set;

import in.fssa.doboo.dao.TrackDAO;
import in.fssa.doboo.dao.UserDAO;
import in.fssa.doboo.exception.NoTrackFoundException;
import in.fssa.doboo.exception.ValidationException;
import in.fssa.doboo.model.TrackEntity;
import in.fssa.doboo.model.UserEntity;
import in.fssa.doboo.util.StringUtil;
import in.fssa.doboo.validator.TrackValidator;

public class TrackService {

	private TrackDAO trackDAO;

	public TrackService() {
		this.trackDAO = new TrackDAO(); // Initialize userDao instance in the constructor
	}

	// this is method is to get all tracks from the database.
	/**
	 * 
	 * @return
	 */
	public Set<TrackEntity> getAllTracks() {

		Set<TrackEntity> trackList = trackDAO.findAll();

		return trackList;
	}
	/**
	 * 
	 * @param track
	 * @param userId
	 * @throws ValidationException
	 * @throws RuntimeException
	 */
	
	/*
	 * Parameter for to create tracks are 
	 * 1. track name
	 * 2. track description
	 * 3. track bpm
	 * 4. track daw
	 * 5. track genre
	 * 6. track scale
	 * */

	public void createTrack(TrackEntity track, int userId) throws ValidationException, RuntimeException {

		int generatedId = -1;
		Timestamp d = null;

		try {
//			UserDAO user = new UserDAO();
			
			TrackDAO trackDAO = new TrackDAO();
			TrackPriceService trackPriceService = new TrackPriceService();
			
			// validate the track object
			
			
			TrackValidator.validate(track);
			TrackValidator.validForCreation(track, userId);
			
			generatedId = trackDAO.createTrack(track, userId);
			d = trackPriceService.getTrackByDate(generatedId);
			trackPriceService.createTrackPrice(d, generatedId, track.getPrice());

		} catch (ValidationException e) {
			throw new RuntimeException(e.getMessage());
		}
		 catch (RuntimeException e) {
				throw new RuntimeException(e.getMessage());
			}
	}
	/**
	 * 
	 * @param userId
	 * @param track
	 * @throws ValidationException
	 * @throws RuntimeException
	 */

	public void updateTrack(int userId, TrackEntity track) throws ValidationException, RuntimeException {
        
		Timestamp d = null;
		try {
			TrackDAO trackDao = new TrackDAO();
			TrackPriceService productPriceService = new TrackPriceService();

//			Vaidate id and product
			TrackValidator.isIdValid(userId);
			TrackValidator.validate(track);
			

//			Update details
			trackDao.update(userId, track);
			d = productPriceService.getTrackByDate(userId);
			productPriceService.updateTrackPrice(d, userId, track.getPrice());

		} catch (ValidationException e) {
			throw new RuntimeException(e.getMessage());
		}

	}
	/**
	 * 
	 * @param userId
	 * @throws ValidationException
	 * @throws RuntimeException
	 */

	public void deleteTrack(int userId) throws ValidationException, RuntimeException {

		try {
			TrackDAO trackDao = new TrackDAO();
			TrackValidator.isIdValid(userId);
			trackDao.delete(userId);
		} catch (ValidationException e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
	}
	/**
	 * 
	 * @param trackName
	 * @return
	 */
	    public Set<TrackEntity> findMatchTrackByName(String trackName) {
	        try {
	        	StringUtil.rejectIfInvalidString(trackName, "trackName");
	            return trackDAO.findMatchTrackByName(trackName);
	        } 
	        catch (ValidationException e) {
	            e.printStackTrace();
	            throw new RuntimeException(e.getMessage());
	        }
	        catch (NoTrackFoundException e) {
	            e.printStackTrace();
	            throw new RuntimeException(e.getMessage());
	        }
	    }
	    /**
	     * 
	     * @param artistName
	     * @return
	     */

	    public Set<TrackEntity> findTracksByAtirstName(String artistName) throws RuntimeException {
	        try {
	        	StringUtil.rejectIfInvalidString(artistName, "artistName");
	            return trackDAO.findTracksByAtirstName(artistName);
	        }
	        catch (ValidationException e) {
	            e.printStackTrace();
	            throw new RuntimeException(e.getMessage());
	        }
	        catch (NoTrackFoundException e) {
	            e.printStackTrace();
	            throw new RuntimeException(e.getMessage());
	        }
	    }
	

}
