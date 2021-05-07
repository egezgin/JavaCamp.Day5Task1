package nLayeredDemo.business.concretes;

import java.util.List;

import nLayeredDemo.business.abstracts.UserService;
import nLayeredDemo.core.abstracts.AuthService;
import nLayeredDemo.dataAccess.abstracts.UserDao;
import nLayeredDemo.entities.concretes.User;

public class UserManager implements UserService {
	private UserDao userDao;
	private AuthService authService;
	
	public UserManager(UserDao userDao, AuthService authService) {
		this.userDao = userDao;
		this.authService = authService;
	}

	@Override
	public void register(User user) {
		System.out.println("trying to register a new account...");
		if (this.authService.register(user))
		{
			this.sendConfirmationMessage();
		}
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
