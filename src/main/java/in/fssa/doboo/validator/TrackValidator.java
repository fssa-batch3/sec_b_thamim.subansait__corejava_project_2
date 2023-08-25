package in.fssa.doboo.validator;

import in.fssa.doboo.dao.TrackDAO;
import in.fssa.doboo.exception.ValidationException;
import in.fssa.doboo.model.TrackEntity;
import in.fssa.doboo.util.StringUtil;

public class TrackValidator {
	/**
	 * 
	 * @param track
	 * @throws ValidationException
	 */
	public static void validate(TrackEntity track) throws ValidationException {

		if (track == null) {
			throw new ValidationException("track cannot be null");
		}
//		Validations
		StringUtil.rejectIfInvalidString(track.getTrackName(), "TrackName");
		if(track.getPrice() <= 0 || track.getPrice() >6000) {
			throw new ValidationException("price cannot be less than zero or greater than 6000");
		}
	}

	/**
	 * 
	 * @param id
	 * @throws ValidationException
	 */
	public static void isIdValid(int id) throws ValidationException {

		try {
			if (id <= 0)
				throw new ValidationException("id is less than zero");
			
			TrackDAO trackDao = new TrackDAO();
			trackDao.checkIdExists(id);
		} catch (RuntimeException e) {
			throw new ValidationException(e.getMessage());
		}

	}
	
	public static void validForCreation(TrackEntity track, int userId) throws ValidationException {
		
		TrackDAO trackDAO = new TrackDAO();
		 // Check if the user exists in the users table
        if (!trackDAO.isUserExists(userId)) {
            throw new ValidationException("User ID does not exist");
        }

        // Check if the track name is already uploaded by the same user
        if (trackDAO.isTrackNameExistsForUser(track.getTrackName(), userId)) {
            throw new ValidationException("Track name already exists for the user");
        }
	}

}
