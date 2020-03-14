package hh.swd.bookstore.Bookstore.webcontrollers;


import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import hh.swd.bookstore.Bookstore.domains.Book;
import hh.swd.bookstore.Bookstore.domains.BookRepository;
import hh.swd.bookstore.Bookstore.domains.Category;
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
		//Laitetaan model-mappiin kirjalista templatea varten
		model.addAttribute("books", books);
		//Palautetaan sopivan templaten nimi.
		return "index";
	}

@RequestMapping(value = "/allcats", method = RequestMethod.GET)
public String getAllCategories(Model model) {
	//Haetaan tietokannasta kirjat listaan
	List<Category> cats = (List<Category>) catRepository.findAll();
	//Laitetaan mddel-mappiin kategorialista templatea varten
	model.addAttribute("catslist", cats);
	//Palautetaan sopivan templaten nimi.
	return "categorieslist";
}

//täytettävän kirjalomakkeen muodostaminen
	@RequestMapping(value = "/newbook", method = RequestMethod.GET)
	public String getNewBookForm(Model model) {
		model.addAttribute("book", new Book()); // "tyhjä" kirja-olio
		model.addAttribute("catslist", catRepository.findAll());
		return "newbookform";
	}
	
	@RequestMapping(value = "/newcat", method = RequestMethod.GET)
	public String getNewCatForm(Model model) {
		//model.addAttribute("book", new Book()); // "tyhjä" kategoria-olio, virheellisesti kirja.
		model.addAttribute("category", new Category());
		return "newcategoryform";
	}

	// kirjalomakkeella syötettyjen tietojen vastaanotto ja tallennus
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String saveBook(@ModelAttribute @Valid Book book, BindingResult bindingResult, Model model) {
		// talletetaan yhden kirjan tiedot tietokantaan
		if (bindingResult.hasErrors()) {
			model.addAttribute("catslist", catRepository.findAll());
			return "newbookform";
			}
		bookRepository.save(book);
		return "redirect:/allbooks"; //allbooks-endpointin kutsu. 
	}
	
	// kirjalomakkeella syötettyjen tietojen vastaanotto ja tallennus
	@RequestMapping(value = "/saveedit", method = RequestMethod.POST)
	public String saveBookEdit(@ModelAttribute @Valid Book book, BindingResult bindingResult, Model model) {
		// talletetaan yhden kirjan tiedot tietokantaan
		if (bindingResult.hasErrors()) {
			model.addAttribute("catslist", catRepository.findAll());
			return "editbook";
			}
		bookRepository.save(book);
		return "redirect:/allbooks"; //allbooks-endpointin kutsu. 
	}
	
	// kirjalomakkeella syötettyjen tietojen vastaanotto ja tallennus
	@RequestMapping(value = "/savecat", method = RequestMethod.POST)
	public String saveBook(@ModelAttribute @Valid Category category, BindingResult bindingResult1, Model model) {
		// talletetaan yhden kategorian tiedot tietokantaan
		if (bindingResult1.hasErrors()) {
			//model.addAttribute("cat", new Category());
			return "newcategoryform";
		}
		catRepository.save(category);
		return "redirect:/allcats"; //allcats-endpointin kutsu. 
	}
	
	@RequestMapping(value = "/saveeditcat", method = RequestMethod.POST)
	public String saveNewCat(@ModelAttribute @Valid Category category, BindingResult bindingResult1, Model model) {
		// talletetaan yhden kategorian tiedot tietokantaan
		if (bindingResult1.hasErrors()) {
			//model.addAttribute("cat", new Category());
			return "editcategory";
		}
		catRepository.save(category);
		return "redirect:/allcats"; //allcats-endpointin kutsu. 
	}

	// kirjan poisto
	@RequestMapping(value = "/deletebook/{id}", method = RequestMethod.GET)
	public String deleteBook(@PathVariable("id") Long bookId) {
		bookRepository.deleteById(bookId);
		return "redirect:../allbooks"; // Liittyy polkuun, jotta mennään yksi ylemmäs.
	}
	
	@RequestMapping(value = "/deletecat/{categoryId}", method = RequestMethod.GET)
	public String deleteCat(@PathVariable("categoryId") Long catId) {
		catRepository.deleteById(catId);
		return "redirect:../allcats"; // Liittyy polkuun, jotta mennään yksi ylemmäs.
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
	@RequestMapping(value = "/editcat/{categoryId}", method = RequestMethod.GET)
	public String editCat(@PathVariable("categoryId") Long catId, Model model) { // Kokeiltu nimellä, ID ofc parempi.
		//bookRepository.deleteById(bookId);
		//Book book = bookRepository.findById(bookId); Ei toimi. Entäs Model?
		model.addAttribute("category", catRepository.findById(catId));
		model.addAttribute("catslist", catRepository.findAll());
		//model.addAttribute("cats", catRepository.findAll());
		return "editcategory"; // Liittyy polkuun, jolla palautetaan suoraan editcategory ID:llä. Ei redirect, vaan suora palautus. Redirect ei toimi.
}
	
	
	
	
	
	
	
	//REST-availability and ENDPOINTS included in this work, REST API also available.
	
	//RESTful service to get all books.
	@RequestMapping(value="/books", method = RequestMethod.GET)
	public @ResponseBody List<Book> bookListRest() {
		return (List<Book>) bookRepository.findAll();
	}
	
	//RESTful service to get one book by id.
	@RequestMapping(value = "/books/{id}", method = RequestMethod.GET)
	public @ResponseBody Optional <Book> findBookRest(@PathVariable Long id) { // Täytyy olla id, ei toimi bookId tässä!
		return bookRepository.findById(id); // Laitetaan util-importti optional, jotta voidaan käyttää PathVariablea myös tässä.
	
}
	
	   // RESTful service to save new car 
    @RequestMapping(value="/books", method = RequestMethod.POST) // POST kertoo, että tallennetaan tietoa. Voisi tehdä vielä jotain websecurityllä??
    // Toisaalta haluamme näyttää kirjat kaikkineen kuitenkin JSON-muodossa GET-metodilla.
    public @ResponseBody Book saveBookRest(@RequestBody Book book) {	
    	return bookRepository.save(book);
    }
    
    // Home page of REST services
    @RequestMapping(value="/resthome", method = RequestMethod.GET)
    public String getRestHome() {	
    	return "resthomepage"; // resthomepage.html, ei oma tiedosto vaan tulee importtien kautta.
    }
}

