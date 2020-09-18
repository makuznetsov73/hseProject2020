package hse.project.controllers;

import hse.project.authentication.AuthRequest;
import hse.project.authentication.JwtTokenProvider;
import hse.project.entities.prototypes.Customer;
import hse.project.mongo.repository.CustomerRepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class AuthenticationController {
	
	private final AuthenticationManager authenticationManager;
	
	private final JwtTokenProvider jwtTokenProvider;
	
	private final CustomerRepositoryInterface userService;
	
	@Autowired
	public AuthenticationController(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider,
									CustomerRepositoryInterface userService) {
		this.authenticationManager = authenticationManager;
		this.jwtTokenProvider = jwtTokenProvider;
		this.userService = userService;
	}
	
	@GetMapping("/")
	public String welcome() {
		return "Welcome";
	}
	
	@PostMapping("/authenticate_user")
	public ResponseEntity generateToken(@RequestBody AuthRequest authRequest) throws Exception {
		System.out.println("Authentication...");
		try {
			String username = authRequest.getUsername();
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, authRequest.getPassword()));
			Customer user = userService.findByLogin(username);
			
			if (user == null) {
				throw new UsernameNotFoundException("User with username: " + username + " not found");
			}
			
			List<String> roles = new ArrayList<>();
			roles.add("ROLE_USER");
			String token = jwtTokenProvider.createToken(username, roles);
			
			Map<Object, Object> response = new HashMap<>();
			response.put("username", username);
			response.put("token", token);
			
			return ResponseEntity.ok(response);
		} catch (AuthenticationException e) {
			throw new BadCredentialsException("Invalid username or password");
		}
	}
}
