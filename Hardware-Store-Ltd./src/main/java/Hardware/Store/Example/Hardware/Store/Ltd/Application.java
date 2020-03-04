package Hardware.Store.Example.Hardware.Store.Ltd;

import org.springframework.boot.CommandLineRunner;     
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import Hardware.Store.Example.Hardware.Store.Ltd.domains.EProductCategoryRepository;
import Hardware.Store.Example.Hardware.Store.Ltd.domains.EProductRepository;
import Hardware.Store.Example.Hardware.Store.Ltd.domains.ElectronicProduct;
import Hardware.Store.Example.Hardware.Store.Ltd.domains.EProductCategory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

 
@SpringBootApplication
public class Application {
	private static final Logger log = LoggerFactory.getLogger(Application.class);
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	@Bean
	public CommandLineRunner productDemo(EProductRepository productRepository, EProductCategoryRepository catRepository) { 
		return (args) -> {
			log.info("Save a couple of products and categories...");
			
			catRepository.save(new EProductCategory("Display"));
			catRepository.save(new EProductCategory("Keyboard"));
			catRepository.save(new EProductCategory("Mouse"));
			catRepository.save(new EProductCategory("Screen"));
			catRepository.save(new EProductCategory("Mouse hold"));
			catRepository.save(new EProductCategory("Web camera"));
			catRepository.save(new EProductCategory("Headset"));
			catRepository.save(new EProductCategory("Speakers"));
			log.info("Categories are:");
			for (EProductCategory cat : catRepository.findAll()) {
				log.info(cat.toString());
			}
			
			
			productRepository.save(new ElectronicProduct("Logitech Xy 144Hz", 2016, "4/5", "Available", "Such a good display with an amazing quality.", 145.50, catRepository.findByName("Display").get(0)));
			productRepository.save(new ElectronicProduct("Microsoft KB 7000", 2020, "5/5", "Not available", "What a fine and comfortable experience with this keyboard.", 69.50, catRepository.findByName("Keyboard").get(0)));	
			productRepository.save(new ElectronicProduct("Samsung Extra Hold", 2018, "4/5", "Available", "You get such good friction with this amazing hold.", 100.60, catRepository.findByName("Mouse hold").get(0)));	
			log.info("Fetch all products...");
			for (ElectronicProduct product : productRepository.findAll()) {
				log.info(product.toString());
			}
		};
	}

}

