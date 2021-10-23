package com.example.demo;

import java.security.Principal;
import java.util.Arrays;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;



@ComponentScan({"user","order","product","orderitem","registration","oauth"})
@SpringBootApplication(exclude = SecurityAutoConfiguration.class)

public class WebshopApplication {
    private static ApplicationContext applicationContext;

	public static void main(String[] args) {
		applicationContext = SpringApplication.run(WebshopApplication.class, args);
	}


	  
	 
	  @Configuration
	  @EnableWebSecurity
	  protected static class SecurityConfiguration extends WebSecurityConfigurerAdapter {
		  private final UserDetailsService service;
			private final BCryptPasswordEncoder passwordEncoder;
			
			public SecurityConfiguration(UserDetailsService service, BCryptPasswordEncoder encoder) {
				this.service = service;
				this.passwordEncoder = encoder;
			}
			
			
			@Override
			protected void configure(AuthenticationManagerBuilder auth) throws Exception {
			
			auth.userDetailsService(service).passwordEncoder(passwordEncoder);
			}


			@Override
			protected void configure(HttpSecurity http) throws Exception {
				System.out.println("hi");
				
				
				
				http
				.formLogin().and().oauth2Login().and()
				.authorizeRequests().antMatchers("/api/**").hasRole("ADMIN")
				.antMatchers("/orders","/orders/**").authenticated()
				.antMatchers("/product","/product/**").authenticated()
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
			        configuration.setAllowedOrigins(Arrays.asList("http://localhost:3000"));
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
