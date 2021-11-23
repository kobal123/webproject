package oauth;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import user.AppUser;
import user.AppUserDataAccessService;
import user.RoleDao;
import user.UserDao;


@Service
public class OAuthService extends DefaultOAuth2UserService{
	private final UserDao userdao;
	
	

	public OAuthService(AppUserDataAccessService userdao) {
		super();
				
		this.userdao = userdao;
		System.out.println("creating OauthService bean");
	
	}
	
	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest)
			throws OAuth2AuthenticationException {
		System.out.println("asdfffaeeasd");

		OAuth2User user = super.loadUser(userRequest);
		String username = (String) user.getAttributes().get("login");
		System.out.println(username);
		System.out.println("asdfffaeeasd");
		String oauth2Token = userRequest.getAccessToken().getTokenValue();
		
			List<GrantedAuthority> authorities = new ArrayList<>(user.getAuthorities());
			String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails()
					.getUserInfoEndpoint().getUserNameAttributeName();
			System.out.println(userNameAttributeName);
			
			DefaultOAuth2User authuser = new DefaultOAuth2User(authorities, user.getAttributes(),
					userNameAttributeName);
			
			System.out.println(authuser.toString());
			
			AppUser appuser = userdao.selectUserByUserName(authuser.getName());
			
			if( Objects.equals(appuser, null)) {
				System.out.println("creating new user from oauth...");
				appuser = new AppUser(authuser.getName(),authuser.getAttribute("email"));
				Long id = userdao.addUsers(appuser);
			}
			
			
			appuser.setName(appuser.getId().toString());
			return appuser;
			

	}



}
