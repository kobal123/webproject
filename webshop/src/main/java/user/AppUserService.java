package user;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AppUserService implements UserDetailsService {

	
	private final UserDao userdao;
	private final RoleDao roledao;

	
	
	public AppUserService(UserDao userdao, RoleDao roledao) {
		this.userdao = userdao;
		this.roledao = roledao;
	}
	
	
	
	public List<AppUser> getUsers(){
		return userdao.selectUsers();
	}
	
	
	public int addUser(AppUser user) {
		return userdao.addUsers(user);
	}
	
	
	public AppUser getUserByUsername(String username) {
		return userdao.selectUserByUserName(username);
	}



	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		AppUser user = userdao.selectUserByUserName(username);
		List<Role> userRoles = roledao.selectUserRolesByUsername(username);
		
		Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
		
		userRoles.forEach( role -> authorities.add(new SimpleGrantedAuthority(role.getName())));
		
		
		return new User(user.getId().toString(),
				user.getPassword(),
				authorities);
	}
	
	
}
