package hse.project.controllers;

import hse.project.authentication.AuthRequest;
import hse.project.authentication.JwtData;
import hse.project.authentication.JwtTokenProvider;
import hse.project.entities.Response;
import hse.project.entities.mongo.*;
import hse.project.entities.prototypes.Customer;
import hse.project.mongo.repository.TariffCustomerRepositoryInterface;
import hse.project.mongo.repository.VMCustomerRepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:4201"})
public class AuthenticationController {
	
	private final AuthenticationManager authenticationManager;
	
	private final JwtTokenProvider jwtTokenProvider;
	
	private final VMCustomerRepositoryInterface userService;
	
	@Autowired
	public AuthenticationController(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider,
									VMCustomerRepositoryInterface userService) {
		this.authenticationManager = authenticationManager;
		this.jwtTokenProvider = jwtTokenProvider;
		this.userService = userService;
	}
	
	@GetMapping("/")
	public String welcome() {
		return "Welcome";
	}
	
	@PostMapping("/authenticate_user")
	public Response<JwtData> generateToken(@RequestBody AuthRequest authRequest) throws Exception {
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
			
			JwtData data = new JwtData();
			data.setToken(token);
			data.setUsername(username);
			
			return new Response<JwtData>(data, true);
		} catch (AuthenticationException e) {
			System.out.println(e);
			JwtData data = new JwtData();
			data.setToken("Invalid credentials");
			return new Response<JwtData>(data, false);
		}
	}
	
	@PostMapping("/register")
	public Response<String> registerUser(@RequestBody AuthRequest authRequest) throws Exception {
		System.out.println("Register...");
		Optional<MongoCustomer> optionalMongoCustomer = userService.findById(authRequest.getUsername());
		if (optionalMongoCustomer.isPresent())
			return new Response<String>("Username is taken", false);
		else {
			MongoCustomer newEntity = new MongoCustomer(authRequest.getUsername(), authRequest.getUsername());
			newEntity.setPassword(authRequest.getPassword());
			userService.insert(newEntity);
			return new Response<>(null, true);
		}
	}
}
