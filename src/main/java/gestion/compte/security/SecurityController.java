package gestion.compte.security;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SecurityController {
	@RequestMapping("/login")
	public String loginAction() {
		return "login";
	}
	
	
	/*
	 * @RequestMapping("/") public String homeAction() { return "redirect:/login"; }
	 */
	 
}
