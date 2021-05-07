package nLayeredDemo.core.concretes;

import java.util.List;

import nLayeredDemo.Google.GoogleManager;
import nLayeredDemo.core.abstracts.AuthService;
import nLayeredDemo.dataAccess.abstracts.UserDao;
import nLayeredDemo.entities.concretes.User;

public class GoogleManagerAdapter implements AuthService {
	GoogleManager manager = new GoogleManager();
	private UserDao userDao;
	
	public GoogleManagerAdapter(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public List<User> getAll() {
		return this.userDao.getAll();
	}

	@Override
	public void login(String mail, String password) {
		
		// no checks, Google services doing checks here
		this.manager.login(mail, password);
	}

	@Override
	public boolean register(User user){
		if (this.manager.login(user.getMail(), user.getPassword()))
		{
			// getting data from Google services
			User newUser = new User(this.manager.getFirstName(), this.manager.getLastName(), user.getMail(), user.getPassword());
			this.userDao.add(newUser);
			
			System.out.println("Registration with Google services was successful.");
			
			return true;
		}
		
		return false;
	}
}
