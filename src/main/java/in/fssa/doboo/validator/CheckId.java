package in.fssa.doboo.validator;

import in.fssa.doboo.exception.ValidationException;

public class CheckId {
	public static void validateId(int id) throws ValidationException{
		if(id <= 0) {
			throw new ValidationException("Id can't be less than or equal to zero");
		}
	}

}
