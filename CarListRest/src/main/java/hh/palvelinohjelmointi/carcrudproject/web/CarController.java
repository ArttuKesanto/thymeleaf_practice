package hh.palvelinohjelmointi.carcrudproject.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import hh.palvelinohjelmointi.carcrudproject.domain.Car;
import hh.palvelinohjelmointi.carcrudproject.domain.CarRepository;

@Controller
public class CarController {
	
	   // Spring-alusta luo sovelluksen käynnistyessä CarRepository-rajapintaa toteuttavan luokan olion 
	   // ja kytkee olion CarController-luokasta luodun olion attribuuttiolioksi
	@Autowired
	CarRepository carRepository; 
	
	
	// autolistaus
	@RequestMapping(value = "/carlist", method = RequestMethod.GET)
	public String getCars(Model model) {
			List<Car> cars =  (List<Car>) carRepository.findAll(); // haeta tietokannasta autot
			model.addAttribute("cars", cars); // välitetään autolista templatelle model-olion avulla
			return "carlist"; // DispatherServlet saa tämän template-nimen ja kutsuu seuraavaksi carlist.html-templatea,
								// joka prosessoidaan palvelimella
	}
	
	// RESTful service to get all cars
    @RequestMapping(value="/cars", method = RequestMethod.GET)
    public @ResponseBody List<Car> getCarsRest() {	
        return (List<Car>) carRepository.findAll();
    }    

	// RESTful service to get car by id
    @RequestMapping(value="/cars/{id}", method = RequestMethod.GET)
    public @ResponseBody Optional<Car> getCarRest(@PathVariable("id") Long carId) {	
    	return carRepository.findById(carId);
    }
    
    // RESTful service to save new car 
    @RequestMapping(value="/cars", method = RequestMethod.POST)
    public @ResponseBody Car saveCarRest(@RequestBody Car car) {	
    	return carRepository.save(car);
    }
    
    // Home page of REST services
    @RequestMapping(value="/resthome", method = RequestMethod.GET)
    public String getRestHome() {	
    	return "resthomepage"; // resthomepage.html
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
		return "redirect:/carlist";
	}

	// auton poisto
	@RequestMapping(value = "/deletecar/{id}", method = RequestMethod.GET)
	public String deleteCar(@PathVariable("id") Long carId) {
		carRepository.deleteById(carId);
		return "redirect:../carlist";
	}
	
	// TODO edit car
	

}
