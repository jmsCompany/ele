package qingyun.ele;

import java.io.IOException;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;
import org.springframework.stereotype.Component;
import qingyun.ele.controller.UserController;
import qingyun.ele.domain.db.Logs;
import qingyun.ele.repository.LogsRepository;


@Component
public class AuthenticationTokenProcessingFilter extends
		AbstractPreAuthenticatedProcessingFilter {

	@Autowired
	private UserDetailsService userDetailsService;
	@Autowired
	private TokenUtils tokenUtils;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private LogsRepository logsRepository;
	private static final Log logger = LogFactory.getLog(UserController.class);

	public AuthenticationTokenProcessingFilter() {
	}

	@Autowired
	public AuthenticationTokenProcessingFilter(
			@Qualifier("authenticationManager") AuthenticationManager authenticationManager) {
		setAuthenticationManager(authenticationManager);
	}

	@Override
	protected Object getPreAuthenticatedPrincipal(HttpServletRequest request) {
		// String token = request.getHeader("JMS-TOKEN");
		// return tokenUtils.getUserFromToken(token);
		return null;
	}

	@Override
	protected Object getPreAuthenticatedCredentials(HttpServletRequest request) {
		// String token = request.getHeader("JMS-TOKEN");
		// UserDetails userDetails = tokenUtils.getUserFromToken(token);
		// return userDetails.getPassword();
		return null;
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
	//	logger.debug("request: " + request.getHeader("User-Agent"));
	//	logger.debug("from: " + request.getRequestURI());
		if (req instanceof org.apache.catalina.connector.RequestFacade) {
			chain.doFilter(request, response);
			
		}
		else
		{
			if (SecurityContextHolder.getContext().getAuthentication() == null) {
				String token = request.getHeader("JMS-TOKEN");
				if (token != null) {
					if (tokenUtils.validate(token)) {
					
						MCAUserDetails userDetails = tokenUtils
								.getUserFromToken(token);
						UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
								userDetails.getUsername(),
								userDetails.getPassword());
						authentication
								.setDetails(new WebAuthenticationDetailsSource()
										.buildDetails((HttpServletRequest) request));
						Authentication authenticated = authenticationManager
								.authenticate(authentication);

						SecurityContextHolder.getContext().setAuthentication(
								authenticated);
						//logger.debug("userid:" +userDetails.getUsername() +", ip: "+request.getRemoteAddr()+", path: "+ request.getRequestURI());
						Logs log = new Logs();
						log.setIp(request.getRemoteAddr());
						log.setTime(new Date());
						log.setUrl(request.getRequestURI());
						log.setUsers(userDetails.getUser());
						logsRepository.save(log);
						
					}
				}

				
			}
			chain.doFilter(request, response);
		}
	}

}