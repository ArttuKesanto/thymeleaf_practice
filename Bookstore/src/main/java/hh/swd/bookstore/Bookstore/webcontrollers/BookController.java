package hh.swd.bookstore.Bookstore.webcontrollers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import hh.swd.bookstore.Bookstore.domains.Book;
import hh.swd.bookstore.Bookstore.domains.BookRepository;
import hh.swd.bookstore.Bookstore.domains.CategoryRepository;

@Controller
public class BookController {

@Autowired
BookRepository bookRepository;
@Autowired
private CategoryRepository catRepository;

@RequestMapping(value = "/allbooks", method = RequestMethod.GET)
	public String getAllBooks(Model model) {
		//Haetaan tietokannasta kirjat listaan
		List<Book> books = (List<Book>) bookRepository.findAll();
		//Laitetaan mddel-mappiin kirjalista templatea varten
		model.addAttribute("books", books);
		//Palautetaan sopivan templaten nimi.
		return "index";
	}

//täytettävän kirjalomakkeen muodostaminen
	@RequestMapping(value = "/newbook", method = RequestMethod.GET)
	public String getNewBookForm(Model model) {
		model.addAttribute("book", new Book()); // "tyhjä" auto-olio
		model.addAttribute("catslist", catRepository.findAll());
		return "newbookform";
	}

	// kirjalomakkeella syötettyjen tietojen vastaanotto ja tallennus
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String saveBook(@ModelAttribute Book book) {
		// talletetaan yhden auton tiedot tietokantaan
		bookRepository.save(book);
		return "redirect:/allbooks"; //cars-endpointin kutsu. 
	}

	// kirjan poisto
	@RequestMapping(value = "/deletebook/{id}", method = RequestMethod.GET)
	public String deleteBook(@PathVariable("id") Long bookId) {
		bookRepository.deleteById(bookId);
		return "redirect:../allbooks"; // Liittyy polkuun, jotta mennään yksi ylemmäs.
	}
	
	@RequestMapping(value = "/editbook/{id}", method = RequestMethod.GET)
	public String editBook(@PathVariable("id") Long bookId, Model model) { // Kokeiltu nimellä, ID ofc parempi.
		//bookRepository.deleteById(bookId);
		//Book book = bookRepository.findById(bookId); Ei toimi. Entäs Model?
		model.addAttribute("book", bookRepository.findById(bookId));
		model.addAttribute("catslist", catRepository.findAll());
		//model.addAttribute("cats", catRepository.findAll());
		return "editbook"; // Liittyy polkuun, jolla palautetaan suoraan editbook. Ei redirect, vaan suora palautus. Redirect ei toimi.
	
	
}
}
