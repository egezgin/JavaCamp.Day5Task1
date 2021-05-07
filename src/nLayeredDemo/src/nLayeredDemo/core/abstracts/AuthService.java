package nLayeredDemo.core.abstracts;

import java.util.List;

import nLayeredDemo.entities.concretes.User;

public interface AuthService {
	public void login(String mail, String password);
	public boolean register(User user);
	List<User> getAll();
}
