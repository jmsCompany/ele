package qingyun.ele;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import qingyun.ele.domain.db.Users;
import qingyun.ele.repository.UsersRepository;


@Service
public class SecurityUtils {
	@Autowired
	private  UsersRepository usersRepository;
	
	public String getUsername() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth.getPrincipal() instanceof UserDetails) {
            return ((MCAUserDetails) auth.getPrincipal()).getUsername();
        } else {
            return auth.getPrincipal().toString();
        }
    }
	public Users getCurrentDBUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Long userid;
        if (auth.getPrincipal() instanceof UserDetails) {
        	userid =((MCAUserDetails) auth.getPrincipal()).getUserId();
        } else {
        	userid = null;
        }
        
       return usersRepository.findOne(userid);
    }
	public MCAUserDetails getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth.getPrincipal() instanceof UserDetails) {
            return ((MCAUserDetails) auth.getPrincipal());
        } else {
            return null;
        }
    }

	@Transactional(readOnly=true)
	public  Collection<GrantedAuthority> getAuthorities(Long idUser) {
		
		List<GrantedAuthority> l = new ArrayList<GrantedAuthority>();
		Users user = usersRepository.findOne(idUser);
		if(user == null) {
			return l;
		}

		  l.add( new GrantedAuthority() {
				private static final long serialVersionUID = 1L;
				
				@Override
				public String getAuthority() {
					return "user";
				}
				
				@Override
				public String toString() {
					return getAuthority();
				}
			});
	   
		
		return l;		
	}
    
}