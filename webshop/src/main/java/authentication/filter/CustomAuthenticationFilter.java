package authentication.filter;

import java.io.IOException;
import java.util.Date;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties.Jwt;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter{
	private final AuthenticationManager manager;
	
	public CustomAuthenticationFilter(AuthenticationManager manager) {
		this.manager=manager;
	}

	
	
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {

		String username = request.getParameter("username");
		String password = request.getParameter("passwrod");
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
		
		return manager.authenticate(token);
	}




	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		User user = (User)authResult.getPrincipal();
		Algorithm algorithm = Algorithm.HMAC256("123456789".getBytes());
		
		String jwtAccessToken = JWT.create()
							   .withSubject(user.getUsername())
							   .withExpiresAt(new Date(System.currentTimeMillis() + 10 * 60 *10))
							   .withClaim("roles", user.getAuthorities().stream().
									   map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
							   .sign(algorithm);
		
		String jwtRefreshToken = JWT.create()
				   .withSubject(user.getUsername())
				   .withExpiresAt(new Date(System.currentTimeMillis() + 10 * 60 *10))
				   .withClaim("roles", user.getAuthorities().stream().
						   map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
				   .sign(algorithm);
							   
		
		response.setHeader("access_token",jwtAccessToken);
		response.setHeader("refresh_token", jwtRefreshToken);;
		System.out.println("a user was authenticated!");
	}

}
