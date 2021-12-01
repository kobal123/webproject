package com.example.demo;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.Arrays;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.boot.autoconfigure.security.SecurityProperties.Filter;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import oauth.OAuthService;



@ComponentScan({"user","order","product","orderitem","registration","oauth","image","cart","cart_item","search","thymeleaf","admin"})
@SpringBootApplication(exclude = SecurityAutoConfiguration.class)

public class WebshopApplication {
    private static ApplicationContext applicationContext;

	public static void main(String[] args) {
		System.out.println(LocalDateTime.now());
		applicationContext = SpringApplication.run(WebshopApplication.class, args);
	}


	  
	 
	  @Configuration
	  @EnableWebSecurity
	
	  protected static class SecurityConfiguration extends WebSecurityConfigurerAdapter {
		  	private final UserDetailsService service;
			private final BCryptPasswordEncoder passwordEncoder;
			private OAuthService authService;
			
			public SecurityConfiguration(UserDetailsService service, BCryptPasswordEncoder encoder,OAuthService authservice) {
				this.service = service;
				this.passwordEncoder = encoder;
				this.authService= authservice;
			}
			
			
			@Override
			protected void configure(AuthenticationManagerBuilder auth) throws Exception {
			
			auth.userDetailsService(service).passwordEncoder(passwordEncoder);
			}


			@Override
			protected void configure(HttpSecurity http) throws Exception {
				
				
				
				http
				.formLogin().and().oauth2Login().userInfoEndpoint().userService(authService).and().and()
				.authorizeRequests()
				.antMatchers("/orders","/orders/**").authenticated()
				.antMatchers("/product","/product/**").authenticated()
				.antMatchers("/cart","/cart/**").authenticated()
				.antMatchers("/api/cart","/api/cart/**").authenticated()
				.antMatchers("/admin","/admin/**").hasRole("ADMIN")
				.and().csrf()
				;
				
				
				//http.authorizeRequests().anyRequest().permitAll();
				
			}

			@Bean
			@Override
			public AuthenticationManager authenticationManagerBean() throws Exception {
			
				return super.authenticationManagerBean();
			}
			 @Bean
			    public CorsConfigurationSource corsConfigurationSource() {
			        final CorsConfiguration configuration = new CorsConfiguration();
			        configuration.setAllowedOrigins(Arrays.asList("http://localhost:8000"));
			        configuration.setAllowedMethods(Arrays.asList("HEAD",
			                "GET", "POST", "PUT", "DELETE", "PATCH"));
			        // setAllowCredentials(true) is important, otherwise:
			        // The value of the 'Access-Control-Allow-Origin' header in the response must not be the wildcard '*' when the request's credentials mode is 'include'.
			        configuration.setAllowCredentials(true);
			        // setAllowedHeaders is important! Without it, OPTIONS preflight request
			        // will fail with 403 Invalid CORS request
			        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Cache-Control", "Content-Type","X-XSRF-TOKEN","X-Requested-With"));
			        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
			        source.registerCorsConfiguration("/**", configuration);
			        System.out.println("áááááá");
			        return source;
			    }
			
	  }
	  

	  
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
