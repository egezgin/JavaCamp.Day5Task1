package nLayeredDemo.dataAccess.abstracts;

import java.util.List;

import nLayeredDemo.entities.concretes.User;

public interface UserDao {
	void add(User user);
	void delete(User user);
	User get(int id);
	List<User> getAll();
}
