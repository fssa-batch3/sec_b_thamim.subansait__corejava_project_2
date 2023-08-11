package in.fssa.doboo;
import in.fssa.doboo.model.UserEntity;
import in.fssa.doboo.service.*;
public class AppTest {

	public static void main(String[] args) throws Exception {
		UserService userNew = new UserService();
		
//		UserEntity user = new UserEntity();
//		user.setArtistName("tommytom");
//		user.setDob("2004-12-07");
//		user.setEmail("Aimtommy@gmail.com");
//		user.setName("yankee");
//		user.setPassword("Tommy@8973");
//		user.setRole("seller");
//		userNew.create(user);
		
		
//		System.out.println(userNew.getAll());
		
		TrackService track = new TrackService();
		System.out.println(track.getAll());
	}

}
