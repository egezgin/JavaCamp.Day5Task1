package nLayeredDemo.dataAccess.concretes;

import java.util.ArrayList;
import java.util.List;

import nLayeredDemo.dataAccess.abstracts.UserDao;
import nLayeredDemo.entities.concretes.User;

public class HibernateUserDao implements UserDao {
	List<User> userList;
	
	public HibernateUserDao() {
		userList = new ArrayList<User>();
	}
	
	@Override
	public void add(User user) {
		this.userList.add(user);
		System.out.println("Added with hibernate: " + user.getMail());		
	}
	
	@Override
	public void delete(User user) {
		this.userList.remove(user);
		System.out.println("Deleted with hibernate: " + user.getMail());		
	}

	@Override
	public User get(int id) {	
		User user = null;
		
		for (User iterator : this.userList)
		{
			if (iterator.getId() == id)
			{
				user = iterator;
				break;
			}
		}
		
		return user;
	}

	@Override
	public List<User> getAll() {
		return this.userList;
	}
}
