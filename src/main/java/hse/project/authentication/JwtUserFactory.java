package hse.project.authentication;

import hse.project.entities.prototypes.Customer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public final class JwtUserFactory {
	public JwtUserFactory() {
	}
	
	public static JwtUser create(Customer user) {
		ArrayList<String> authorities = new ArrayList<String>();
		authorities.add("ROLE_USER");
		return new JwtUser(
			user.getId(),
			user.getLogin(),
			user.getPassword(),
			mapToGrantedAuthorities(authorities)
		);
	}
	
	private static List<GrantedAuthority> mapToGrantedAuthorities(List<String> userRoles) {
		return userRoles.stream()
			.map(SimpleGrantedAuthority::new
			).collect(Collectors.toList());
	}
}
