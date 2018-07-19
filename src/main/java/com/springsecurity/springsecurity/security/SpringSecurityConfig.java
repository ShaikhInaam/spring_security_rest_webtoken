package com.springsecurity.springsecurity.security;

import java.util.Collection;
import java.util.Collections;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;



@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter{

	/*
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.cors().and().csrf().disable()
		.authorizeRequests()
		.antMatchers(HttpMethod.GET, "/user/").permitAll()
		.antMatchers(HttpMethod.DELETE, "/user/").permitAll()
		.antMatchers(HttpMethod.POST, "/user/").hasRole("USER")
		.antMatchers(HttpMethod.PUT, "/user/").hasRole("ADMIN")
		.and().httpBasic();
		//.addFilter(new JWTAuthenticationFilter(authenticationManager()))
		//.addFilter(new JWTAuthorizationFilter(authenticationManager(), userDetails));
		
	}
	
	@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().passwordEncoder(NoOpPasswordEncoder.getInstance())
                .withUser("user")
                .password("password")
                .roles("USER")
                .and()
                .withUser("sysuser")
                .password("password")
                .roles("ADMIN");
    }
*/
	
	Logger logger = LoggerFactory.getLogger(SpringSecurityConfig.class);
	
	
	@Autowired
	private JWTAuthenticationProvider jWTAuthenticationProvider;  // custom class whic mplements AuthenticationProvider
	@Autowired
	private JWTAuthenticationEntryPoint entryPoint;
	
	@Bean
	public AuthenticationManager authenticationManager()	// this class is from spring
	{
		
		return new ProviderManager(Collections.singletonList(jWTAuthenticationProvider));
	}
	
	@Bean
	public JWTAuthenticationTokenFilter authenticationTokenFilter() //this is my custom class
	{
		JWTAuthenticationTokenFilter filter = new JWTAuthenticationTokenFilter();
		filter.setAuthenticationManager(authenticationManager());
		filter.setAuthenticationSuccessHandler(new JWTSuccessHandler());
		
		return filter;
	}
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		logger.info("-----------CONFIG STARTED");
		
		http.csrf().disable()
        .authorizeRequests().antMatchers("**/user/**").authenticated()
        //.antMatchers(HttpMethod.GET, "/user/").permitAll()
		//.antMatchers(HttpMethod.DELETE, "/user/").permitAll()
		//.antMatchers(HttpMethod.POST, "/user/").hasRole("USER")
		//.antMatchers(HttpMethod.PUT, "/user/").hasRole("ADMIN")
        .and()
        .exceptionHandling().authenticationEntryPoint(entryPoint)
        .and()
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

http.addFilterBefore(authenticationTokenFilter(), UsernamePasswordAuthenticationFilter.class);
http.headers().cacheControl();
		 logger.info("-----------CONFIG Ended");
	}
	
}
