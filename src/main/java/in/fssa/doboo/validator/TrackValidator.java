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
			TrackDAO productDao = new TrackDAO();

			productDao.checkIdExists(id);
		} catch (RuntimeException e) {
			throw new ValidationException(e.getMessage());
		}

	}

}
