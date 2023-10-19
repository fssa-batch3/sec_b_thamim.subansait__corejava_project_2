package in.fssa.doboo.service;

import java.sql.Timestamp;
import java.util.Set;

import in.fssa.doboo.dao.TrackDAO;
import in.fssa.doboo.dao.UserDAO;
import in.fssa.doboo.exception.NoTrackFoundException;
import in.fssa.doboo.exception.PersistanceException;
import in.fssa.doboo.exception.ServiceException;
import in.fssa.doboo.exception.ValidationException;
import in.fssa.doboo.model.Assest;
import in.fssa.doboo.model.TrackEntity;
import in.fssa.doboo.model.UserEntity;
import in.fssa.doboo.util.StringUtil;
import in.fssa.doboo.validator.AssestValidator;
import in.fssa.doboo.validator.CheckId;
import in.fssa.doboo.validator.TrackValidator;
import in.fssa.doboo.validator.UserValidator;

public class TrackService {

	private TrackDAO trackDAO;

	public TrackService() {
		this.trackDAO = new TrackDAO(); // Initialize userDao instance in the constructor
	}

	// this is method is to get all tracks from the database.
	/**
	 * 
	 * @return
	 * @throws PersistanceException 
	 */
	public Set<TrackEntity> getAllTracks() throws PersistanceException {
		
		Set<TrackEntity> trackList = trackDAO.findAll();
		for(TrackEntity tracks :trackList ) {
			TrackPriceService trackprice = new TrackPriceService();
			int price = trackprice.getTrackPrice(tracks.getId());
			tracks.setPrice(price);
			AssestService assestService = new AssestService();
			Assest asset = assestService.findByTrackId(tracks.getId());
			String artistName = trackDAO.getArtistNameForTrackId(tracks.getId());
			tracks.setArtistName(artistName);
			tracks.setImageUrl(asset.getImageUrl());
			tracks.setAudioUrl(asset.getAudioUrl());
			System.out.println(tracks);
		}

		return trackList;
	}	
	/**
	 * 
	 * @param track
	 * @param userId
	 * @throws ValidationException
	 * @throws RuntimeException
	 * @throws PersistanceException 
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

	public void createTrack(TrackEntity track, int userId) throws ValidationException, RuntimeException{

		int generatedId = -1;
		Timestamp d = null;

		try {
//			UserDAO user = new UserDAO();
			
			TrackDAO trackDAO = new TrackDAO();
			TrackPriceService trackPriceService = new TrackPriceService();
			AssestService assestService = new AssestService();
			Assest asset = new Assest();
			asset.setImageUrl(track.getImageUrl());
			asset.setAudioUrl(track.getAudioUrl());
			AssestValidator.validate(asset);
			// validate the track object
			
			TrackValidator.validate(track);
			TrackValidator.validForCreation(track, userId);
			
			generatedId = trackDAO.createTrack(track, userId);
			d = trackPriceService.getTrackByDate(generatedId);
			trackPriceService.createTrackPrice(d, generatedId, track.getPrice());
			assestService.createAssest(asset, generatedId);

		} catch (ValidationException e) {
			throw new RuntimeException(e.getMessage());
		}
		 catch (RuntimeException e) {
				throw new RuntimeException(e.getMessage());
			}
		catch (PersistanceException e) {
			throw new RuntimeException(e.getMessage());
		}
	}
	
	/**
	 * 
	 * @param trackId
	 * @param track
	 * @throws ValidationException
	 * @throws RuntimeException
	 * @throws PersistanceException 
	 */

	public void updateTrack(int trackId, TrackEntity track) throws ValidationException, RuntimeException, PersistanceException {
        
		Timestamp d = null;
		try {
			TrackDAO trackDao = new TrackDAO();
			Assest assest = new Assest();
			assest.setImageUrl(track.getImageUrl());
			assest.setAudioUrl(track.getAudioUrl());
			TrackPriceService productPriceService = new TrackPriceService();

//			Vaidate id and product
			AssestValidator.validate(assest);
			TrackValidator.isIdValid(trackId);
			TrackValidator.validate(track);
			

//			Update details
			trackDao.update(trackId, track);
			d = productPriceService.getTrackByDate(trackId);
			productPriceService.updateTrackPrice(d, trackId, track.getPrice());
			
			AssestService assestService = new AssestService();
			assestService.updateAssest(assest, trackId);

		} catch (ValidationException | RuntimeException | PersistanceException e) {
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
	    
	    public String findAtirstNameByTrackId(int trackId) throws RuntimeException, PersistanceException {
	    	String artistName = null;
	    	try {
	        	
	        	TrackValidator.isIdValid(trackId);
	        	TrackDAO trackDAO = new TrackDAO();
	        	artistName = trackDAO.getArtistNameForTrackId(trackId);
	        }
	        catch (ValidationException e) {
	            e.printStackTrace();
	            throw new RuntimeException(e.getMessage());
	        }
	        catch (NoTrackFoundException e) {
	            e.printStackTrace();
	            throw new RuntimeException(e.getMessage());
	        }
	    	return artistName;
	    }
	    
	    /**
		 * 
		 * @param id
		 * @return
		 * @throws ValidationException
		 */
		public TrackEntity findByTrackId(int id) throws ValidationException, PersistanceException,RuntimeException {
			TrackValidator.isIdValid(id);
			TrackDAO trackDAO = new TrackDAO();
			TrackPriceService trackPriceService = new TrackPriceService();
			TrackEntity track = trackDAO.findById(id);
			AssestService assestService = new AssestService();
			Assest asset = new Assest();
			asset=assestService.findByTrackId(id);
			int price = trackPriceService.getTrackPrice(id);
			track.setPrice(price);
			track.setImageUrl(asset.getImageUrl());
			track.setAudioUrl(asset.getAudioUrl());
			return track;
		}
		/**
		 * 
		 * @param userId
		 * @return
		 * @throws RuntimeException
		 * @throws ServiceException 
		 * @throws PersistanceException 
		 */
		
		public Set<TrackEntity> findTracksByUserId(int userId) throws RuntimeException, ServiceException, PersistanceException {
	        	try {
					CheckId.validateId(userId);
					TrackValidator.isValidUser(userId);
					Set<TrackEntity>trackList=trackDAO.findTracksByUserId(userId);
					for(TrackEntity tracks :trackList ) {
						AssestService assestService = new AssestService();
						Assest asset = assestService.findByTrackId(tracks.getId());
						tracks.setImageUrl(asset.getImageUrl());
						tracks.setAudioUrl(asset.getAudioUrl());
						System.out.println(tracks);
					}
					return trackList;
					
				} catch (ValidationException | NoTrackFoundException | PersistanceException | ServiceException e) {
					e.printStackTrace();
					throw new RuntimeException(e.getMessage());
				}
	        	
	    }
	

}
