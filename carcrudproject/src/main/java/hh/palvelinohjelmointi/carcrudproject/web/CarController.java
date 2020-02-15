package hh.palvelinohjelmointi.carcrudproject.web;

import hh.palvelinohjelmointi.carcrudproject.domain.Car;  
import hh.palvelinohjelmointi.carcrudproject.domain.CarRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class CarController {
	
	   // Spring-alusta luo sovelluksen käynnistyessä CarRepository-rajapintaa toteuttavan luokan olion 
	   // ja kytkee olion CarController-luokasta luodun olion attribuuttiolioksi
	@Autowired
	CarRepository carRepository; 
	
	
	// autolistaus
	@RequestMapping(value = "/cars", method = RequestMethod.GET)
	public String getCars(Model model) {
			List<Car> cars =  (List<Car>) carRepository.findAll(); // haeta tietokannasta autot
			model.addAttribute("cars", cars); // välitetään autolista templatelle model-olion avulla
			return "carlist"; // DispatherServlet saa tämän template-nimen ja kutsuu seuraavaksi carlist.html-templatea,
								// joka prosessoidaan palvelimella
	}

	// tyhjän autolomakkeen muodostaminen
	@RequestMapping(value = "/newcar", method = RequestMethod.GET)
	public String getNewCarForm(Model model) {
		model.addAttribute("car", new Car()); // "tyhjä" auto-olio
		return "carform";
	}

	// autolomakkeella syötettyjen tietojen vastaanotto ja tallennus
	@RequestMapping(value = "/newcar", method = RequestMethod.POST)
	public String saveCar(@ModelAttribute Car car) {
		// talletetaan yhden auton tiedot tietokantaan
		carRepository.save(car);
		return "redirect:/cars"; //cars-endpointin kutsu. 
	}

	// auton poisto
	@RequestMapping(value = "/deletecar/{id}", method = RequestMethod.GET)
	public String deleteCar(@PathVariable("id") Long carId) {
		carRepository.deleteById(carId);
		return "redirect:../cars"; // Liittyy polkuun, jotta mennään yksi ylemmäs.
	}
	
	// TODO edit car
	

}
