package hh.swd.bookstore.Bookstore;

import org.springframework.boot.CommandLineRunner;  
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import hh.swd.bookstore.Bookstore.domains.Book;
import hh.swd.bookstore.Bookstore.domains.BookRepository;
import hh.swd.bookstore.Bookstore.domains.Category;
import hh.swd.bookstore.Bookstore.domains.CategoryRepository;
 
@SpringBootApplication
public class BookstoreApplication {
	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner bookDemo(BookRepository bookRepository, CategoryRepository catRepository) { 
		return (args) -> {
			log.info("Save a couple of books and categories...");
			
			catRepository.save(new Category("Literature"));
			catRepository.save(new Category("Culture"));
			catRepository.save(new Category("Sci-fi"));
			catRepository.save(new Category("Horror"));
			for (Category cat : catRepository.findAll()) {
				log.info(cat.toString());
			}
			
			
			bookRepository.save(new Book("Tesla", "Matts Johansson", 1994, "2100-2345-113", 54.50, catRepository.findByName("Horror").get(0)));
			bookRepository.save(new Book("Saving Private Ryan", "Lord Hansel", 1999, "2833-2345-112", 69.50, catRepository.findByName("Literature").get(0)));	
			bookRepository.save(new Book("Saving Private Holmes", "Lord Helmett", 2007, "2123-2545-812", 199.50, catRepository.findByName("Culture").get(0)));	
			log.info("Fetch all books and their information...");
			for (Book book : bookRepository.findAll()) {
				log.info(book.toString());
			}
		};
	}

}
