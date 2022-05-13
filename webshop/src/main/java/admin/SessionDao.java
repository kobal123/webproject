package admin;

import java.util.List;
import java.util.Map;

import org.springframework.boot.web.servlet.server.Session;

public interface SessionDao {

	
	public Map<String,Object> sessions();
	
	public void deleteSessionById(String sessionId);
}
