package hh.swd20.springfirstproject.webcontrol;

import org.springframework.stereotype.Controller;  
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody

public class IndexContactController {
	@RequestMapping("/index")	
	public String returnMainPageText() {
		return "This is the main page!";  // CMD + SHIFT + F, formattia!
	} 

	@RequestMapping("/contact")
	public String returnContactPage() {
		return "This is the contact page!";
	}
}
