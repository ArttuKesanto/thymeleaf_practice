package hh.palvelinohjelmointi.carcrudproject;

import hh.palvelinohjelmointi.carcrudproject.domain.Car;
import hh.palvelinohjelmointi.carcrudproject.domain.CarRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CarcrudprojectApplication {
	
	private static final Logger log = LoggerFactory.getLogger(CarcrudprojectApplication.class);  // uusi loggeriattribuutti

	public static void main(String[] args) {
		SpringApplication.run(CarcrudprojectApplication.class, args);
	}
	
	
	//  testidatan luonti H2-testitietokantaan aina sovelluksen käynnistyessä
	@Bean
	public CommandLineRunner carDemo(CarRepository carRepository) { 
		return (args) -> {
			log.info("save a couple of cars");
			carRepository.save(new Car("Tesla", 2016));
			carRepository.save(new Car("Saab", 1986));	
			
			log.info("fetch all cars");
			for (Car car : carRepository.findAll()) {
				log.info(car.toString());
			}

		};
	}
}
