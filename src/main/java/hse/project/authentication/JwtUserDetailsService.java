package hse.project.authentication;

import hse.project.entities.prototypes.Customer;
import hse.project.mongo.repository.TariffCustomerRepositoryInterface;
import hse.project.mongo.repository.VMCustomerRepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class JwtUserDetailsService implements UserDetailsService {
	
	private final VMCustomerRepositoryInterface userService;
	
	@Autowired
	public JwtUserDetailsService(VMCustomerRepositoryInterface userService) {
		this.userService = userService;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Customer user = userService.findByLogin(username);
		
		if (user == null) {
			throw new UsernameNotFoundException("User with username: " + username + " not found");
		}
		
		JwtUser jwtUser = JwtUserFactory.create(user);
		return jwtUser;
	}
}