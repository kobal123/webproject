package user;

import java.util.Collection;
import java.util.List;

public interface RoleDao  {

	public List<Role> selectUserRolesByUsername(String username);
}
