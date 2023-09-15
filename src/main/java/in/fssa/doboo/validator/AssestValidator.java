package in.fssa.doboo.validator;


import in.fssa.doboo.exception.ValidationException;
import in.fssa.doboo.model.Assest;
import java.util.regex.Pattern;

import in.fssa.doboo.util.StringUtil;

public class AssestValidator {
	private static final String HTTP_PATTERN = "^https?:\\/\\/(?:www\\.)?[-a-zA-Z0-9@:%._\\+~#=]{1,256}\\.[a-zA-Z0-9()]{1,6}\\b(?:[-a-zA-Z0-9()@:%_\\+.~#?&\\/=]*)$";
	public static void validate(Assest assest) throws ValidationException {
		if (assest == null) {
			throw new ValidationException("can't leave image and audio empty or null");
		}
		
	StringUtil.rejectIfInvalidString(assest.getAudioUrl(), "audioUrl");
	validateurl(assest.getImageUrl());
	
	if (Pattern.matches(HTTP_PATTERN, assest.getAudioUrl())) {
		throw new ValidationException("don't paste http link. please paste the /d/{variable} only");
	}
		
	}
	public static void validateurl(String imageUrl) throws ValidationException {
		
		StringUtil.rejectIfInvalidString(imageUrl, "imageUrl");
		
		if (!Pattern.matches(HTTP_PATTERN, imageUrl)) {
			throw new ValidationException("imageUrl doesn't match the pattern");
		}
	
	}
}
