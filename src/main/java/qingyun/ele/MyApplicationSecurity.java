/**
 * 
 */
package qingyun.ele;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.rememberme.AbstractRememberMeServices;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;



/**
 * @author 任宏涛， ren@ecust.edu.cn
 *
 * @created 2016年1月5日 下午12:55:36
 *
 */
@Configuration
@EnableWebSecurity
public class MyApplicationSecurity extends WebSecurityConfigurerAdapter {
	
	@Autowired private UserDetailsService userDetailsService;
	@Autowired private AuthenticationTokenProcessingFilter authenticationTokenProcessingFilter;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http
		.authorizeRequests().antMatchers("/user/reg").permitAll()
		.antMatchers("/error").permitAll()
		.antMatchers("/check/**").permitAll()
		.antMatchers("/dic/**").permitAll()
		.antMatchers("/getFile/**").permitAll()
		.antMatchers("/login").permitAll()
		.antMatchers(HttpMethod.OPTIONS,"/**").permitAll();
		http.addFilter(authenticationTokenProcessingFilter);
	    http.authorizeRequests().anyRequest().authenticated();
	   
	}
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth, UserDetailsService userDetailsService) throws Exception {
//		auth
//			.userDetailsService(userDetailsService)
//				.passwordEncoder(new BCryptPasswordEncoder());
	}
	
	
}