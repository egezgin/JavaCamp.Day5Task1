package nLayeredDemo.core.concretes;

import java.util.List;

import nLayeredDemo.Google.GoogleManager;
import nLayeredDemo.core.abstracts.AuthService;
import nLayeredDemo.dataAccess.abstracts.UserDao;
import nLayeredDemo.entities.concretes.User;

public class GoogleManagerAdapter implements AuthService {
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
		
		GoogleManager manager = new GoogleManager();
		manager.login(mail, password);
	}

}
