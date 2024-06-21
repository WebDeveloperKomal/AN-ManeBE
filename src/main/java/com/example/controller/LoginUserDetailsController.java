package com.example.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.LoginUserDetails;
import com.example.model.JwtRequest;
import com.example.model.JwtResponse;
import com.example.repo.LoginUserDetailsRepository;
import com.example.util.JwtHelper;

@RestController
@CrossOrigin
@RequestMapping("/user")
public class LoginUserDetailsController {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private LoginUserDetailsRepository loginUserDetailsRepository;

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private JwtHelper jwtHelper;

//	private Logger logger = LoggerFactory.getLogger(AuthenticationManager.class);
	

	/*-----------------------add-new-user-----------------------*/
	
	@PostMapping(value = ("/add-new-user"))
	private ResponseEntity<Map<String, Object>> saveSlides(
			@RequestBody String data/* , Authentication authentication */) {
		Map<String, Object> response = new HashMap<>();
//		LoginUserDetails loginUserDetails = (LoginUserDetails) authentication.getPrincipal();
		try {
			JSONParser parser = new JSONParser();
			JSONObject json = (JSONObject) parser.parse(data);

			LoginUserDetails user = new LoginUserDetails();
			user.setName(json.get("name").toString());
			user.setL_name(json.get("lastName").toString());
			user.setNumber(json.get("number").toString());
			user.setEmail(json.get("email").toString());
			user.setPassword(passwordEncoder.encode(json.get("password").toString()));

			loginUserDetailsRepository.save(user);
			response.put("status", true);
			response.put("data", "Record saved");
			response.put("data", user);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception ex) {
			response.put("status", false);
			response.put("error", ex.getMessage());
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/*-----------------------login-user-----------------------*/

    @CrossOrigin(origins = "http://127.0.0.1:5502")
	@PostMapping("/login")
	public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest request) {
		this.doAuthenticate(request.getEmail(), request.getPassword());
		UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());
		String token = this.jwtHelper.generateToken(userDetails);

		JwtResponse response = JwtResponse.builder().jwtToken(token).username(userDetails.getUsername()).build();
		/* .username(userDetails.getUsername()).build(); */
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	private void doAuthenticate(String email, String password) {

		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(email, password);
		try {
			authenticationManager.authenticate(authentication);

		} catch (BadCredentialsException e) {
			throw new BadCredentialsException(" Invalid Username or Password  !!");
		}
	}
	

	/*-----------------------update-user-----------------------*/
	@PutMapping(value = "/update-user/{id}")
	private ResponseEntity<Map<String, Object>> updateUser(@PathVariable int id, @RequestParam String data, Authentication authentication) {
		Map<String, Object> response = new HashMap<>();
		LoginUserDetails loginUserDetails = (LoginUserDetails) authentication.getPrincipal();
		try {
			Optional<LoginUserDetails> userOptional = loginUserDetailsRepository.findById(id);

			if (userOptional.isPresent()) {
				LoginUserDetails user = userOptional.get();

				JSONParser parser = new JSONParser();
				JSONObject json = (JSONObject) parser.parse(data);

				user.setName(json.get("name").toString());
				user.setL_name(json.get("lastName").toString());
				user.setNumber(json.get("number").toString());
				user.setEmail(json.get("email").toString());
				user.setPassword(passwordEncoder.encode(json.get("password").toString()));

				loginUserDetailsRepository.save(user);

				response.put("status", true);
				response.put("data", "Record updated");
				return new ResponseEntity<>(response, HttpStatus.OK);
			} else {
				response.put("status", false);
				response.put("error", "User not found");
				return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
			}
		} catch (Exception ex) {
			response.put("status", false);
			response.put("error", ex.getMessage());
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/*-----------------------delete-user-----------------------*/
	@DeleteMapping(value = "/delete-user/{id}")
	private ResponseEntity<Map<String, Object>> deleteUser(@PathVariable int id,  Authentication authentication) {
		LoginUserDetails loginUserDetails = (LoginUserDetails) authentication.getPrincipal();
		Map<String, Object> response = new HashMap<>();

		try {
			Optional<LoginUserDetails> userOptional = loginUserDetailsRepository.findById(id);

			if (userOptional.isPresent()) {
				loginUserDetailsRepository.deleteById(id);
				response.put("status", true);
				response.put("data", "Record deleted");
				return new ResponseEntity<>(response, HttpStatus.OK);
			} else {
				response.put("status", false);
				response.put("error", "User not found");
				return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
			}
		} catch (Exception ex) {
			response.put("status", false);
			response.put("error", ex.getMessage());
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/*-----------------------get-all-users-----------------------*/
	@GetMapping(value = "/get-all-users")
	private ResponseEntity<Map<String, Object>> getAllUsers() {
		Map<String, Object> response = new HashMap<>();

		try {
			List<LoginUserDetails> users = loginUserDetailsRepository.findAll();
			response.put("status", true);
			response.put("data", users);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception ex) {
			response.put("status", false);
			response.put("error", ex.getMessage());
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/*-----------------------get-user-by-id-----------------------*/
	@GetMapping(value = "/get-user-by-id/{id}")
	private ResponseEntity<Map<String, Object>> getUserById(@PathVariable int id,  Authentication authentication) {
		Map<String, Object> response = new HashMap<>();
		LoginUserDetails loginUserDetails = (LoginUserDetails) authentication.getPrincipal();
		try {
			Optional<LoginUserDetails> userOptional = loginUserDetailsRepository.findById(id);

			if (userOptional.isPresent()) {
				LoginUserDetails user = userOptional.get();
				response.put("status", true);
				response.put("data", user);
				return new ResponseEntity<>(response, HttpStatus.OK);
			} else {
				response.put("status", false);
				response.put("error", "User not found");
				return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
			}
		} catch (Exception ex) {
			response.put("status", false);
			response.put("error", ex.getMessage());
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	/*-----------------------get-userProfile-----------------------*/
	@GetMapping(value="user-profile", produces="application/json")
	public ResponseEntity<Map<String,Object>> getUserProfile(Authentication authentication) {
	    LoginUserDetails loginUserDetails = (LoginUserDetails) authentication.getPrincipal();
	    Map<String,Object> response = new HashMap<>();
	    try {
	        Optional<LoginUserDetails> data1 = loginUserDetailsRepository.findByEmail(loginUserDetails.getUsername());
	        if(data1.isPresent()) {
	            LoginUserDetails data = data1.get();
	            response.put("firstName", data.getName());
	            response.put("lastName", data.getL_name());
	            response.put("email", data.getEmail());
	            response.put("id", data.getId());
	            response.put("number", data.getNumber());
	            response.put("status", true);
	            return new ResponseEntity<>(response, HttpStatus.OK);
	        } else {
	            response.put("status", false);
	            response.put("error", "User profile not found");
	            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	        }
	    } catch(Exception ex) {
	        ex.printStackTrace();
	        response.put("status", false);
	        response.put("error", ex.getMessage());
	        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}
	
	
	
	/*-----------------------get-userProfile-----------------------*/
	@PutMapping(value="update-user-profile")
	public ResponseEntity<Map<String,Object>> updateUserProfile(@RequestBody Map<String, String> userData, Authentication authentication) {
	    LoginUserDetails loginUserDetails=(LoginUserDetails) authentication.getPrincipal();
	    Map<String,Object> response=new HashMap<String,Object>();
	    try {
	        Optional<LoginUserDetails> loginUser = loginUserDetailsRepository.findByEmail(loginUserDetails.getEmail());
	        if(loginUser.isPresent()) {
	            LoginUserDetails loginUserDetail =	loginUser.get();
	            loginUserDetail.setEmail(loginUserDetails.getEmail());
	            loginUserDetail.setName(userData.get("firstNameValue1"));
	            loginUserDetail.setL_name(userData.get("lastNameValue1"));
	            loginUserDetail.setNumber(userData.get("numberValue1"));
	            loginUserDetailsRepository.save(loginUserDetail);
	        } else {
	            // Handle user not found
	        }
	        response.put("status", true);
	        return new ResponseEntity<Map<String,Object>>(response, HttpStatus.OK);
	    } catch(Exception ex) {
	        ex.printStackTrace();
	        response.put("status", false);
	        response.put("error", ex.getMessage());
	        return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}

    
    
    
    /*-------------update-user-password------------*/
	@PutMapping(value = "update-user-password")
	public ResponseEntity<Map<String, Object>> updateEmployeePassword(@RequestParam("oldPass") String oldPassword,
			@RequestParam("newPassword") String newPassword, Authentication authentication) {
		LoginUserDetails loginUserDetails = (LoginUserDetails) authentication.getPrincipal();
		Map<String, Object> response = new HashMap<String, Object>();

		try {
			Optional<LoginUserDetails> emp = loginUserDetailsRepository.findByEmail(loginUserDetails.getEmail());
			if (emp.isPresent()) {
				LoginUserDetails loginUser = emp.get();

				if (loginUser != null && passwordEncoder.matches(oldPassword, loginUser.getPassword())) {
					loginUser.setPassword(passwordEncoder.encode(newPassword));
					loginUserDetailsRepository.save(loginUser);

					response.put("status", true);
					response.put("data", "Password changed successfully.");
					return new ResponseEntity<>(response, HttpStatus.OK);
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			response.put("status", false);
			response.put("error", ex.getMessage());
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("status", false);
		response.put("error", "User not found or old password is incorrect.");
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}

}
