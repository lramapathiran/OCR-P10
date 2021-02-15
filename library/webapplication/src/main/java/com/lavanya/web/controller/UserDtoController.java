package com.lavanya.web.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.lavanya.web.dto.AuthBodyDto;
import com.lavanya.web.proxies.UserProxy;

/**
 * Controller used in MVC architecture to control all the requests related to UserDto object.
 * @author lavanya
 */
@Controller
public class UserDtoController {
	
	@Autowired
	UserProxy userProxy;
	
	/**
     * GET requests for /homePage endpoint.
     * This controller-method show the homePage of the site and the login form for the user to be connected
     * 
     * @param model  to pass data to the view.
     * @param error only when an error exist while login in.
     * @param session a HttpSession where attributes of interest are stored, here it concerns the token generated following user connection.
     * @return index.html
     */	
	@GetMapping("/homePage")
	public String showLoginAndHomePage (@RequestParam(value = "error", required = false) String error,
			HttpSession session, Model model) {
		
		
		String token = (String) session.getAttribute("token");
		
		if (token!=null) {
			String subToken = token.substring(7);
			 
			DecodedJWT jwt = JWT.decode(subToken);
			String fullname = jwt.getClaim("fullname").asString();
								 
			model.addAttribute("fullname", fullname);
		}
		
    	String errorMessage = null;
        if(error != null) {
            errorMessage = "L'identifiant ou le mot de passe est incorrect!!";
        }
        model.addAttribute("errorMessage", errorMessage);
		
		AuthBodyDto authBody = new AuthBodyDto();
		model.addAttribute("authBodyDto", authBody);
        return "index.html";
	}
	
	/**
     * POST requests for /login endpoint.
     * This controller-method send data required for user authentication to the api module.
     * 
     * @param authBody is the bean where the password and username of the user are stored to authenticate the user.
     * @param session a HttpSession where attributes of interest are stored, here it concerns the token generated while login in.
     * @return redirect to userDashboard.html
     */	
	@PostMapping("/login")
	public String sendAuthBodyForAuthentication(AuthBodyDto data, HttpSession session) {
			
		try{
			String resp = userProxy.login(data);
			String token = "Bearer " + resp;
	        session.setAttribute("token", token);
			return "redirect:/user/lendings";
		}catch (Exception e) {
			return "redirect:/homePage?error=true#sign-in";
		}
		
	}
	
	/**
	 * GET requests for /logout endpoint.
	 * This controller-method is used to logout a user.
	 * @param session HttpSession that needs to be invalidated to log out the user of interest.
	 * @return homePage.html.
	 */
	@GetMapping("/logout")
    public String logout(HttpSession session) {
		
        session.invalidate();

        return "redirect:/homePage";
    }
	
	

}
