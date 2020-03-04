package Hardware.Store.Example.Hardware.Store.Ltd.webcontrollers;

import java.util.List;  

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import Hardware.Store.Example.Hardware.Store.Ltd.domains.EProductCategory;
import Hardware.Store.Example.Hardware.Store.Ltd.domains.EProductCategoryRepository;
import Hardware.Store.Example.Hardware.Store.Ltd.domains.EProductRepository;
import Hardware.Store.Example.Hardware.Store.Ltd.domains.ElectronicProduct;


@Controller
public class EProductController {

@Autowired
EProductRepository productRepository;
@Autowired
private EProductCategoryRepository catRepository;

@RequestMapping(value = "/allproducts", method = RequestMethod.GET)
	public String getAllProducts(Model model) {
		//Haetaan tietokannasta tuotteet listaan
		List<ElectronicProduct> products = (List<ElectronicProduct>) productRepository.findAll();
		//Laitetaan model-mappiin tuotelista templatea varten.
		model.addAttribute("products", products);
		//Palautetaan sopivan templaten nimi.
		return "index";
	}

@RequestMapping(value = "/allcats", method = RequestMethod.GET)
public String getAllCategories(Model model) {
	//Haetaan tietokannasta kategoriat listaan.
	List<EProductCategory> cats = (List<EProductCategory>) catRepository.findAll();
	//Laitetaan mddel-mappiin kategorialista templatea varten.
	model.addAttribute("catslist", cats);
	//Palautetaan sopivan templaten nimi.
	return "categorieslist";
}

	//täytettävän tuotelomakkeen muodostaminen kategoriatietojen kanssa.
	@RequestMapping(value = "/newproduct", method = RequestMethod.GET)
	public String getNewBookForm(Model model) {
		model.addAttribute("product", new ElectronicProduct()); // "tyhjä" E-product -olio.
		model.addAttribute("catslist", catRepository.findAll());
		return "newproductform";
	}
	
	@RequestMapping(value = "/newcat", method = RequestMethod.GET)
	public String getNewCatForm(Model model) {
		model.addAttribute("cat", new EProductCategory());
		return "newcategoryform"; // Luodaan tyhjä sisällöltään oleva kategoriaolio, johon tallennetaan tietoa.
	}

	// tuotelomakkeella syötettyjen tietojen vastaanotto ja tallennus
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String saveProduct(@ModelAttribute ElectronicProduct product) {
		// talletetaan yhden auton tiedot tietokantaan
		productRepository.save(product);
		return "redirect:/allproducts"; // allproducts-endpointin kutsu. 
	}
	
	// kategorialomakkeella syötettyjen tietojen vastaanotto ja tallennus.
	@RequestMapping(value = "/savecat", method = RequestMethod.POST)
	public String saveBook(@ModelAttribute EProductCategory category) {
		// tallennetaan yhden kategorian tiedot tietokantaan.
		catRepository.save(category);
		return "redirect:/allcats"; // allcats-endpointin kutsu. 
	}

	// tuotteen poisto
	@RequestMapping(value = "/deleteproduct/{id}", method = RequestMethod.GET)
	public String deleteBook(@PathVariable("id") Long electronicproductId) {
		productRepository.deleteById(electronicproductId);
		return "redirect:../allproducts"; // Liittyy polkuun, jotta mennään yksi ylemmäs /../.
	}
	
	@RequestMapping(value = "/deletecat/{categoryId}", method = RequestMethod.GET)
	public String deleteCat(@PathVariable("categoryId") Long catId) {
		catRepository.deleteById(catId);
		return "redirect:../allcats"; // Liittyy polkuun, jotta mennään yksi ylemmäs.
	}
	
	@RequestMapping(value = "/editproduct/{id}", method = RequestMethod.GET)
	public String editBook(@PathVariable("id") Long electronicproductId, Model model) { // Kokeiltu nimellä, ID ofc parempi.
		model.addAttribute("product", productRepository.findById(electronicproductId));
		model.addAttribute("catslist", catRepository.findAll());
		//model.addAttribute("cats", catRepository.findAll());
		return "editproduct"; // Liittyy polkuun, jolla palautetaan suoraan editproduct. Ei redirect, vaan suora palautus. Redirect ei toimi.
	
	
}
	
	@RequestMapping(value = "/editcat/{categoryId}", method = RequestMethod.GET)
	public String editCat(@PathVariable("categoryId") Long catId, Model model) { // Kokeiltu nimellä, ID ofc parempi.
		model.addAttribute("cat", catRepository.findById(catId));
		model.addAttribute("catslist", catRepository.findAll());
		//model.addAttribute("cats", catRepository.findAll());
		return "editcategory"; // Liittyy polkuun, jolla palautetaan suoraan editcategory. Ei redirect, vaan suora palautus. Redirect ei toimi.
}
}
