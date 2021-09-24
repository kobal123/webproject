package user;

import java.util.List;

import org.springframework.stereotype.Service;


@Service
public class RoleService {
	private final RoleDao dao;
	
	
	public RoleService(RoleDataAccessService dao) {
		this.dao = dao;
	}
	
	
	
	
	
	public List<Role> getUserRolesByUsername(String username) {
		
		return dao.selectUserRolesByUsername(username);
	}

}
