package nLayeredDemo;

import nLayeredDemo.business.abstracts.UserService;
import nLayeredDemo.business.concretes.UserManager;
import nLayeredDemo.core.concretes.AuthManager;
import nLayeredDemo.core.concretes.GoogleManagerAdapter;
import nLayeredDemo.dataAccess.abstracts.UserDao;
import nLayeredDemo.dataAccess.concretes.HibernateUserDao;
import nLayeredDemo.entities.concretes.User;

public class Output {

	public static void main(String[] args) {
		UserDao userDao = new HibernateUserDao();
		UserService userService1 = new UserManager(userDao, new AuthManager(userDao));
		
		User user1 = new User("E", "Gezgin", "egezgin@outlook.com", "1234567");
		userService1.register(user1);		
		
		User user2 = new User("Emrah", "Gezgin", "egezgin@outlook.com", "1237");
		userService1.register(user2);
		
		User user3 = new User("Emrah", "Gezgin", "@outlook.com", "1234567");
		userService1.register(user3);
		
		User user4 = new User("Emrah", "Gezgin", "egezgin@outlook.com", "1234567");
		userService1.register(user4);
		
		User user5 = new User("Emrah", "Gezgin", "egezgin@outlook.com", "1234567");
		userService1.register(user5);
		
		userService1.accountVerificationLinkClicked(user5);
		userService1.accountVerificationLinkClicked(user5);
		userService1.accountVerificationLinkClicked(user3);
		
		userService1.login("egezgin@live.com", "1234567");
		userService1.login("egezgin@outlook.com", "123");
		userService1.login("egezgin@outlook.com", "1234567");
		
		UserService userService2 = new UserManager(userDao, new GoogleManagerAdapter(userDao));
		
		User user6 = new User("egezgin@gmail.com", "1234567");
		userService2.register(user6);
		userService2.accountVerificationLinkClicked(user6);
		
		userService2.login("egezgin@gmail.com", "1234567");
		userService1.login("egezgin@gmail.com", "1234567");
	}

}
