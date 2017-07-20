package qingyun.ele;

import java.io.IOException;
import java.util.Date;
import java.util.Enumeration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;
import org.springframework.stereotype.Component;
import qingyun.ele.controller.UserController;
import qingyun.ele.domain.db.Logs;
import qingyun.ele.repository.LogsRepository;

@Component
public class AuthenticationTokenProcessingFilter extends AbstractPreAuthenticatedProcessingFilter {

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
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		// logger.debug("request: " + request.getHeader("User-Agent"));
		// logger.debug("from: " + request.getRequestURI());
		if (req instanceof org.apache.catalina.connector.RequestFacade) {
		//	System.out.println("wtf? ");
			chain.doFilter(request, response);

		} else {
			if (SecurityContextHolder.getContext().getAuthentication() == null) {
				String token = request.getHeader("JMS-TOKEN");
				if (token != null) {
					//System.out.println("token: " + token);
					if (tokenUtils.validate(token)) {
						
						MCAUserDetails userDetails = tokenUtils.getUserFromToken(token);
						UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
								userDetails.getUsername(), userDetails.getPassword());
						
						//System.out.println("usernameL " + userDetails.getUsername());
						authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
						Authentication authenticated = authenticationManager.authenticate(authentication);
						SecurityContextHolder.getContext().setAuthentication(authenticated);
						Logs log = new Logs();
						log.setIp(request.getRemoteAddr());
						log.setTime(new Date());
						log.setUrl(request.getRequestURI());
						log.setUsers(userDetails.getUser());
						Enumeration<String> enumeration = req.getParameterNames();
						String params="";
						while (enumeration.hasMoreElements()){
							String paramName=enumeration.nextElement();
							if (isAvailableParam(paramName)){
								params+=paramName+":"+req.getParameter(paramName)+",";
							}
						}
						if (params!=null&&!"".equals(params)){
							params=params.substring(0,params.length()-1);
							//System.out.println("=========>"+params);
							//System.out.println(params.length());
							log.setParams(params.trim());
						}
						logsRepository.save(log);

					}
				}

			}
			chain.doFilter(request, response);
		}
	}

	/**
	 * 判断是否是可用参数
	 * @param paramName 参数名称
	 * @return true表示可用  false表示不可用
     */
	private boolean isAvailableParam(String paramName){
		String regex="^(columns|search).*";
		Pattern pattern=Pattern.compile(regex);
		Matcher matcher = pattern.matcher(paramName);
		return !matcher.matches();
	}


}