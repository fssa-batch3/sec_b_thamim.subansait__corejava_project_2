package in.fssa.doboo.validator;

import in.fssa.doboo.exception.ValidationException;
import in.fssa.doboo.model.Artist;
import in.fssa.doboo.util.StringUtil;

public class ArtistValidator {
	
	public static void validate(Artist aritst,int userId) throws ValidationException {

		if (aritst == null) {
			throw new ValidationException("aritst cannot be null");
		}
//		Validations
		StringUtil.rejectIfInvalidString(aritst.getArtistName(), "ArtistName");
		StringUtil.rejectIfInvalidString(aritst.getType(), "ArtistType");
		StringUtil.rejectIfInvalidString(aritst.getBio(), "ArtistBio");
		
		if(userId <= 0) {
			throw new ValidationException("userId cannot be less than zero");
		}
		
	}
	
	public static void validUrl(String url) throws ValidationException {
		try {
		AssestValidator.validateurl(url);
		}catch(ValidationException e) {
			throw new ValidationException("social link is wrong");
		}
	}
	
	public static void validateUser(int userId) throws ValidationException {
		try {
			UserValidator.checkIdExists(userId);
			
		} catch (ValidationException e) {
			throw new ValidationException("user doesn't exits");
		}
	}


}
