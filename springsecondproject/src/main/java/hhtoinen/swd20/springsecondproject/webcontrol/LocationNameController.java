package hhtoinen.swd20.springsecondproject.webcontrol;

import org.springframework.stereotype.Controller; 
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@ResponseBody
@Controller
public class LocationNameController {

@RequestMapping("/locationNameDescription")
	public String returnLocationAndName(@RequestParam(name = "location")String locationVar, @RequestParam(name  = "personName")String personName) {
		return "Welcome to the " + locationVar + ", " + personName + "!";
	}

// Toimii osoitteella localhost:8080/locationNameDescription?location=Moon&personName=Arttu
}
