package hh.swd20.springfirstproject.webcontrol;

import org.springframework.stereotype.Controller; // Shift + CMD + O = uusi importti ja automatisoi.
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
public class HelloController {
	// Tehdään metodi, joka /hello -endpointilla lähettää selaimeen "Hello World" -viestin.
@RequestMapping("/hello")	
	public String returnHello() {
		return "Hello, world!";  // CMD + SHIFT + F, formattia!
	} 

// Metodi, joka /helloyou -endpointilla lähettää selaimeen Hello + etunimi + sukunimi. Pyynnön paramterina saatu nimi.
// http://localhost:8080/helloyou?fname=Arttu&lastName=Kesanto
@RequestMapping("/helloyou")
	public String returnFullName(@RequestParam(name = "fname")String firstName, @RequestParam(name  = "lastName")String lastName) {
		return "Hello " + firstName + " " + lastName;
	}


}









