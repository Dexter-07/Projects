package com.dexter.springboot.project.flightReservation.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.dexter.springboot.project.flightReservation.entities.User;
import com.dexter.springboot.project.flightReservation.repos.UserRepository;
import com.dexter.springboot.project.flightReservation.services.SecurityService;

@Controller
public class UserController {

	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private SecurityService securityService;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@RequestMapping("/showReg")
	public String showRegistrationPage() {
		LOGGER.info("Inside showRegistrationPage()");
		return "login/registerUser";
	}
	
	@RequestMapping("/showLogin")
	public String showLoginPage() {
		LOGGER.info("Inside showLoginPage()");
		return "login/login";
	}
	
	
	
	@RequestMapping(value="/registerUser", method=RequestMethod.POST)
	public String register(@ModelAttribute("user") User user) {
		LOGGER.info("Inside register()"+user);
		user.setPassword(encoder.encode(user.getPassword()));
		userRepo.save(user);
		return "login/login";
		
	}
	
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public String login(@RequestParam("email") String email, @RequestParam("password") String password , ModelMap modelMap) {
		
		LOGGER.info("Inside login() and the email is : "+email);
		/*LOGGER.error("ERRRROR");
		LOGGER.warn("WARN");
		LOGGER.info("INFO");
		LOGGER.debug("DEBUG");
		LOGGER.trace("TRACE");*/

		
		//login using Spring Security
		boolean loginResponse = securityService.login(email, password);
		if(loginResponse) {
			return "findFlights";
		}else {
			modelMap.addAttribute("msg", "Incorrect username or password");
		}
		
		
		/*User user = userRepo.findByEmail(email);
		if(user.getPassword().equals(password)) {
			return "findFlights";
		}else {
			modelMap.addAttribute("msg", "Incorrect username or password");
		}*/
		return "login/login";
	}
}
