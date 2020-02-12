package hh.swd.example20.carprojectexample.webcontroller;

import java.util.ArrayList; 
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import hh.swd.example20.carprojectexample.domain.Car;

@Controller
public class CarController {

// Metodi palauttaa autolistauksen selaimelle
@RequestMapping("/allcars")
public String getAllCars(Model model) {
	// 1. autolista palautetaan Thymeleaf-templatelle oikea model-mappiin talletettu tietolista.
	// ei vielä osata hakea autoja tietokannasta, joten luodaan autolista tässä
	List<Car> cars = new ArrayList<Car>();
	cars.add(new Car("Saab", 1990));
	cars.add(new Car("Tesla", 2014));
	cars.add(new Car("Tesla", 2020));
	// 2. sopiva Thymeleaf-templaten nimi palautetaan dispatcher-servletille
	model.addAttribute("cars", cars); // ensimmäinen parametri teksti-keyword, jolla löydetään mapista.
	return "carlist"; // palauttaa carlist.html-templaten nimen

};

/* Metodi palauttaa sleaimeen tyhjän autolomakkeen */
@RequestMapping(value="/newcar", method = RequestMethod.GET) // Tästä aloitetaan. Palauttaa lomakkeen newcarform. Alempana jatketaan tästä.
public String getNewCarForm(Model model) {
	
	model.addAttribute("car", new Car());
	return "newcarform"; // newcarform.html, palauttaa tämän.
	
};

/* Metodi vastaanottaa autolomakkeelta tulleet tiedot ja 
 * tallentaa ne tietokantaan */
@RequestMapping(value="/savecar", method = RequestMethod.POST)
public String saveCar(@ModelAttribute Car car, Model model) {
	// ei osata vielä tallentaa lomakkeella annetun auton tietoja
	// tietokantaan	
	// annetaan auton tiedot raportti-templatella
	model.addAttribute("car", car);
	return "carreport";
	
};
	
};
