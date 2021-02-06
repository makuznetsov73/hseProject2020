package hse.project.utils;

import hse.project.authentication.JwtConfigurer;
import hse.project.authentication.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	private final JwtTokenProvider jwtTokenProvider;
	
	private static final String USER_ENDPOINT = "/user/**";
	private static final String LOGIN_ENDPOINT = "/authenticate_user";
	private static final String REGISTER_ENDPOINT = "/register";
	
	@Autowired
	public WebSecurityConfig(JwtTokenProvider jwtTokenProvider) {
		this.jwtTokenProvider = jwtTokenProvider;
	}
	
	@SuppressWarnings("deprecation")
	@Bean
	public static NoOpPasswordEncoder passwordEncoder() {
		return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
	}
	
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().and()
			.httpBasic().disable()
			.csrf().disable()
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and()
			.authorizeRequests()
			.antMatchers("/admin/**").permitAll()
			.antMatchers(LOGIN_ENDPOINT).permitAll()
			.antMatchers(REGISTER_ENDPOINT).permitAll()
			.antMatchers(USER_ENDPOINT).hasRole("USER")
			.anyRequest().authenticated()
			.and()
			.apply(new JwtConfigurer(jwtTokenProvider));
	}
	
}