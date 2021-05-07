package nLayeredDemo.business.abstracts;

import java.util.List;

import nLayeredDemo.entities.concretes.User;

public interface UserService {
	void register(User user);
	void login(String mail, String password);
	List<User> getAll();
	void accountVerificationLinkClicked(User user);
}
