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

	private TrackDAO trackDao;

	public TrackService() {
		this.trackDao = new TrackDAO(); // Initialize userDao instance in the constructor
	}

	// this is method is to get all tracks from the database.
	/**
	 * 
	 * @return
	 */
	public Set<TrackEntity> getAll() {

		Set<TrackEntity> trackList = trackDao.findAll();

		return trackList;
	}
	/**
	 * 
	 * @param track
	 * @param userId
	 * @throws ValidationException
	 * @throws RuntimeException
	 */

	public void create(TrackEntity track, int userId) throws ValidationException, RuntimeException {

		int generatedId = -1;
		Timestamp d = null;

		try {
			UserDAO user = new UserDAO();
			
			TrackDAO trackDao = new TrackDAO();
			TrackPriceService productPriceService = new TrackPriceService();
			TrackValidator.validate(track);
			
			generatedId = trackDao.createTrack(track, userId);
			d = productPriceService.getDate(generatedId);
			productPriceService.create(d, generatedId, track.getPrice());

		} catch (ValidationException e) {
			throw new RuntimeException(e.getMessage());
		}
		 catch (RuntimeException e) {
				throw new RuntimeException(e.getMessage());
			}
	}
	/**
	 * 
	 * @param id
	 * @param track
	 * @throws ValidationException
	 * @throws RuntimeException
	 */

	public void update(int id, TrackEntity track) throws ValidationException, RuntimeException {

		Timestamp d = null;
		try {
			TrackDAO trackDao = new TrackDAO();
			TrackPriceService productPriceService = new TrackPriceService();

//			Vaidate id and product
			TrackValidator.validate(track);
			TrackValidator.isIdValid(id);

//			Update details
			trackDao.update(id, track);
			d = productPriceService.getDate(id);
			productPriceService.update(d, id, track.getPrice());

		} catch (ValidationException e) {
			throw new RuntimeException(e.getMessage());
		}

	}
	/**
	 * 
	 * @param id
	 * @throws ValidationException
	 * @throws RuntimeException
	 */

	public void delete(int id) throws ValidationException, RuntimeException {

		try {
			TrackDAO trackDao = new TrackDAO();
			TrackValidator.isIdValid(id);
			trackDao.delete(id);
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
	            return trackDao.findMatchTrackByName(trackName);
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

	    public Set<TrackEntity> findTracksByAtirstName(String artistName) {
	        try {
	        	StringUtil.rejectIfInvalidString(artistName, "artistName");
	            return trackDao.findTracksByAtirstName(artistName);
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
