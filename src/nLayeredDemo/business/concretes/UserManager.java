package nLayeredDemo.business.concretes;

import java.util.List;

import nLayeredDemo.business.abstracts.UserService;
import nLayeredDemo.core.abstracts.AuthService;
import nLayeredDemo.dataAccess.abstracts.UserDao;
import nLayeredDemo.entities.concretes.User;

public class UserManager implements UserService {
	private UserDao userDao;
	private int minimalPasswordLength = 6;
	private int minimalNameLength = 2;
	private AuthService authService;
	
	public UserManager(UserDao userDao, AuthService authService) {
		super();
		this.userDao = userDao;
		this.authService = authService;
	}

	@Override
	public void register(User user) {
		System.out.println("trying to register a new account...");
		if (user.getPassword().length() < this.minimalPasswordLength)			
		{
			System.out.println("Password length is too small. Minimal password length is " + this.minimalPasswordLength + ".");
			return;
		}
		
		// see https://howtodoinjava.com/java/regex/java-regex-validate-email-address/
		if (user.getMail().matches("^[A-Za-z0-9+_.-]+@(.+)$") == false)
		{
			System.out.println("Mail address doesn't match right pattern.");
			return;
		}
		
		List<User> userList = this.getAll();
		for (User iterator : userList)
		{
			if (user.getMail().equals(iterator.getMail()) == true)
			{
				System.out.println("Mail address already in use.");
				return;
			}
		}
		
		if (user.getFirstName().length() < this.minimalNameLength)			
		{
			System.out.println("First name length is too small. Minimal name length is " + this.minimalNameLength + ".");
			return;
		}
		
		if (user.getLastName().length() < this.minimalNameLength)			
		{
			System.out.println("Last name length is too small. Minimal name length is " + this.minimalNameLength + ".");
			return;
		}
		
		this.userDao.add(user);	
		
		this.sendConfirmationMessage();
	}
	
	@Override
	public void login(String mail, String password) {
		System.out.println("trying to login...");
		this.authService.login(mail, password);
	}
	
	void sendConfirmationMessage()
	{
		System.out.println("Confirmation email has been sent.");
	}
	
	public void accountVerificationLinkClicked(User user)
	{
		User foundUser = null;
		List<User> userList = this.getAll();
		for (User iterator : userList)
		{
			if (iterator.getMail().equals(user.getMail()))
			{
				foundUser = user;
				break;
			}
		}
		
		if (foundUser == null)		
		{
			System.out.println("Account not found.");
			return;
		}
			
		if(foundUser.isAccountActivated())
		{
			System.out.println("Account already activated.");
			return;
		}
		
		foundUser.setAccountActivated(true);
		
		System.out.println("Account verification successful. Account has been activated.");
	}

	@Override
	public List<User> getAll() {
		return this.userDao.getAll();
	}
}
