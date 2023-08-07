package in.fssa.doboo;
import in.fssa.doboo.service.*;
public class AppTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		UserService userNew = new UserService();
		System.out.println(userNew.getAll());
	}

}
