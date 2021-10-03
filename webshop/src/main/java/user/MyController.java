package user;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MyController  {

	
	@RequestMapping("/a/b")
	public ModelAndView index () {
	    ModelAndView modelAndView = new ModelAndView();
	    modelAndView.setViewName("custom");
	    return modelAndView;
	}
	
	
	 @GetMapping("/login")
	    public String login() {
	        return "login";
	    }
	

	
}
