package nLayeredDemo.core.concretes;

import java.util.List;

import nLayeredDemo.core.abstracts.AuthService;
import nLayeredDemo.dataAccess.abstracts.UserDao;
import nLayeredDemo.entities.concretes.User;

public class AuthManager implements AuthService {
	private UserDao userDao;
	private int minimalPasswordLength = 6;
	private int minimalNameLength = 2;
	
	public AuthManager(UserDao userDao) {
		this.userDao = userDao;
	}
	
	public void login(String mail, String password) {
		boolean wrongAddress = false;
		boolean wrongPassword = false;
		
		List<User> userList = this.getAll();
		for (User iterator : userList)
		{
			wrongAddress = iterator.getMail().equals(mail);
			wrongPassword = iterator.getPassword().equals(password);
			if (wrongAddress == true && wrongPassword == true)
			{
				break;
			}
		}
		
		if (wrongAddress != true)
		{
			System.out.println("Mail address is wrong.");
			return;
		}
		if (wrongPassword != true)
		{
			System.out.println("Password is wrong.");
			return;
		}
		
		System.out.println("Login was successful.");
	}

	@Override
	public List<User> getAll() {
		return this.userDao.getAll();
	}

	@Override
	public boolean register(User user) {
		if (user.getPassword().length() < this.minimalPasswordLength)			
		{
			System.out.println("Password length is too small. Minimal password length is " + this.minimalPasswordLength + ".");
			return false;
		}
		
		// see https://howtodoinjava.com/java/regex/java-regex-validate-email-address/
		if (user.getMail().matches("^[A-Za-z0-9+_.-]+@(.+)$") == false)
		{
			System.out.println("Mail address doesn't match right pattern.");
			return false;
		}
		
		List<User> userList = this.getAll();
		for (User iterator : userList)
		{
			if (user.getMail().equals(iterator.getMail()) == true)
			{
				System.out.println("Mail address already in use.");
				return false;
			}
		}
		
		if (user.getFirstName().length() < this.minimalNameLength)			
		{
			System.out.println("First name length is too small. Minimal name length is " + this.minimalNameLength + ".");
			return false;
		}
		
		if (user.getLastName().length() < this.minimalNameLength)			
		{
			System.out.println("Last name length is too small. Minimal name length is " + this.minimalNameLength + ".");
			return false;
		}
		
		this.userDao.add(user);
		
		System.out.println("Registration was successful.");
		
		return true;
	}
}
