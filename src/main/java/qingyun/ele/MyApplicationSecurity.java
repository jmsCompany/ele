package qingyun.ele;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
@EnableWebSecurity
public class MyApplicationSecurity extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;
	@Autowired
	private AuthenticationTokenProcessingFilter authenticationTokenProcessingFilter;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.authorizeRequests()
		.antMatchers("/error").permitAll()
		.antMatchers("/check/**").permitAll()
		.antMatchers("/actuator/**").permitAll()
		.antMatchers("/dic/**").permitAll()
		.antMatchers("/getFile/**").permitAll()
		.antMatchers("/getImage/**").permitAll()
		.antMatchers("/login").permitAll()
		.antMatchers(HttpMethod.OPTIONS, "/**").permitAll();
		http.addFilter(authenticationTokenProcessingFilter);
		http.authorizeRequests().antMatchers("/customer").hasAuthority("customer");
		http.authorizeRequests().anyRequest().hasAuthority("company");
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth, UserDetailsService userDetailsService)
			throws Exception {
		// auth
		// .userDetailsService(userDetailsService)
		// .passwordEncoder(new BCryptPasswordEncoder());
	}

}