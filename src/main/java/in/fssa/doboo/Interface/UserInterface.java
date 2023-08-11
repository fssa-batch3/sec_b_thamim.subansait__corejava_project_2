package in.fssa.doboo.Interface;

import in.fssa.doboo.model.User;
import in.fssa.doboo.model.UserEntity;

public interface UserInterface extends Base<UserEntity> {
	
	public abstract User findByEmailId(String email);
	
	
	public abstract User findByArtistName(String artistName);


	public abstract int count();
}

