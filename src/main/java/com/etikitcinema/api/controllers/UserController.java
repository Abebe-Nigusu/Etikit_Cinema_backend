package com.etikitcinema.api.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.etikitcinema.api.models.LoginUser;
import com.etikitcinema.api.models.User;
import com.etikitcinema.api.services.UserService;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/users")
@CrossOrigin("http://localhost:3000")
public class UserController {
	private final UserService userServ;
	
	public UserController(UserService userServ) {
		this.userServ = userServ;
	}
	@GetMapping("/login/reg")
	public String loginReg(@ModelAttribute("newUser")User user, @ModelAttribute("loginUser") LoginUser loginUser){
		return "user/loginreg.jsp";
	}
	
//	@PostMapping("/process/register")
//	public String processRegisterUser(@Valid @ModelAttribute("newUser") User user, BindingResult result, Model model, HttpSession session) {
		
//		if(!user.getPassword().equals(user.getConfirm())){
//			result.rejectValue("password", "Match", "confirm password must match password");
//		}
//		if(userServ.getUser(user.getEmail())!=null) {
//			result.rejectValue("email", "Unique", "email is already in use");
//		}
		
//		if(result.hasErrors()) {
//			model.addAttribute("loginUser", new LoginUser());
//			return "user/loginreg.jsp";
//		}
//		User newUser = userServ.registerUser(user);
//		session.setAttribute("user_id", newUser.getId());
//		return "redirect:/";
//	}
	
	
	
	  @PostMapping("/process/register")
	  
	  public ResponseEntity<String> registerUser(@RequestBody User user) {
		    String username = user.getUserName();
		    String email = user.getEmail();
		    String role = user.getRole();
		    String password = user.getPassword();
		    String confirm = user.getConfirm();
		  
		    User newUser = new User(username, email, role, password,confirm);
		    userServ.registerUser(newUser);
		    return ResponseEntity.ok("User registered successfully!");
		}
	  
	
	@PostMapping("/process/login")
	
	public String processLoginUser(
		
			@RequestParam("email") String email,
			@RequestParam("password") String password
 			){
		User newUser = new User( email, password);
		return "successfully logged In";
}
	
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/users/login/reg";
	}
}
