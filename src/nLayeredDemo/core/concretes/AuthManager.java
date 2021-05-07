package nLayeredDemo.core.concretes;

import java.util.List;

import nLayeredDemo.core.abstracts.AuthService;
import nLayeredDemo.dataAccess.abstracts.UserDao;
import nLayeredDemo.entities.concretes.User;

public class AuthManager implements AuthService {
	private UserDao userDao;
	
	public AuthManager(UserDao userDao) {
		this.userDao = userDao;
	}
	
	@Override
	public void login(String mail, String password) {
		List<User> userList = this.getAll();
		for (User iterator : userList)
		{
			if (iterator.getMail().equals(mail) != true)
			{
				System.out.println("Mail address is wrong.");
				return;
			}
			if (iterator.getPassword().equals(password) != true)
			{
				System.out.println("Password is wrong.");
				return;
			}
		}
		
		System.out.println("Login was successful.");
	}

	@Override
	public List<User> getAll() {
		return this.userDao.getAll();
	}
}
