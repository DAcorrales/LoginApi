package com.init.login;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.init.login.controller.JWTAuthorizationFilter;

@SpringBootApplication
public class LoginApi2Application {

	
	public static void main(String[] args) {
		SpringApplication.run(LoginApi2Application.class, args);
	}
	
	@EnableWebSecurity
	@Configuration
	class WebSecurityConfig extends WebSecurityConfigurerAdapter {

		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http.csrf().disable()
			    .addFilterAfter(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)
				.authorizeRequests()
				.antMatchers(HttpMethod.GET, "/getlogin","/getuserinformation","/response","/adduser","/changepassword","/queryuser" ).permitAll()
				.antMatchers(HttpMethod.POST, "/adduser").permitAll()
				.antMatchers(HttpMethod.PUT, "/changepassword").permitAll()
				.anyRequest().authenticated();
		}
		
	}
}
