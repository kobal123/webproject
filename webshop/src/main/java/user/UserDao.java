package user;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;


public interface UserDao {

	public List<AppUser> selectUsers();
	
	public AppUser selectUserByUserName(String username);
	
	
	public Long addUsers(AppUser user);
	
	
	public AppUser selectUserByUserId(Long id);
	
	
	
}
