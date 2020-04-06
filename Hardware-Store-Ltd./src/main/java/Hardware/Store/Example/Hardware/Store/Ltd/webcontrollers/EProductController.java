package Hardware.Store.Example.Hardware.Store.Ltd.webcontrollers;

import java.util.List;  
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
	public String getNewProductForm(Model model) {
		model.addAttribute("electronicProduct", new ElectronicProduct()); // "tyhjä" E-product -olio.
		model.addAttribute("catslist", catRepository.findAll());
		return "newproductform";
	}
	
	@RequestMapping(value = "/newcat", method = RequestMethod.GET) // HUOM! Validointi toimii täysin BookStoressa, asia ymmärretty.
	// Valitettavasti tässä työssä en saa toimimaan kategorian validointia, vaikka kaikkea on kokeiltu ja luennoitsijaa konsultoitu.
	// Asia kuitenkin ymmärretty BindingResult-luokkaviittauksiin, yms, ja attribuuttien annotaatioihin! Muussa työssä toimii täydellisesti.
	public String getNewCatForm(Model model) {
		model.addAttribute("eProductCategory", new EProductCategory());
		return "newcategoryform"; // Luodaan tyhjä sisällöltään oleva kategoriaolio, johon tallennetaan tietoa.
	}

	// tuotelomakkeella syötettyjen tietojen vastaanotto ja tallennus
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String saveProduct(@ModelAttribute @Valid ElectronicProduct electronicProduct, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			//model.addAttribute("product", new ElectronicProduct());
			model.addAttribute("catslist", catRepository.findAll());
			return "newproductform";
		}
		// talletetaan yhden tuotteen tiedot tietokantaan
		productRepository.save(electronicProduct);
		return "redirect:/allproducts"; // allproducts-endpointin kutsu. 
	}
	
	@RequestMapping(value = "/saveedit", method = RequestMethod.POST)
	public String saveProductEdited(@ModelAttribute @Valid ElectronicProduct product, BindingResult bindingResult1, Model model) {
		if (bindingResult1.hasErrors()) {
			model.addAttribute("catslist", catRepository.findAll());
			return "editproduct";
		}
		// talletetaan yhden tuotteen tiedot tietokantaan
		productRepository.save(product);
		return "redirect:/allproducts"; // allproducts-endpointin kutsu. 
	}
	
	// kategorialomakkeella syötettyjen tietojen vastaanotto ja tallennus.
	@RequestMapping(value = "/savecat", method = RequestMethod.POST)
	public String saveCategory(@Valid EProductCategory eProductCategory, BindingResult bindingResult2, Model model) {
		if (bindingResult2.hasErrors()) {
			return "newcategoryform";
		}
		// tallennetaan yhden kategorian tiedot tietokantaan.
		catRepository.save(eProductCategory);
		return "redirect:/allcats"; // allcats-endpointin kutsu. 
	}
	
	@RequestMapping(value = "/saveeditcat", method = RequestMethod.POST)
	public String saveEditCat(@Valid @ModelAttribute EProductCategory eProductCategory, BindingResult bindingResult3, Model model) {
		if (bindingResult3.hasErrors()) {
			return "editcategory";
		}
		// tallennetaan yhden kategorian tiedot tietokantaan.
		catRepository.save(eProductCategory);
		return "redirect:/allcats"; // allcats-endpointin kutsu. 
	}

	// tuotteen poisto
	@RequestMapping(value = "/deleteproduct/{id}", method = RequestMethod.GET)
	public String deleteProduct(@PathVariable("id") Long electronicproductId) {
		productRepository.deleteById(electronicproductId);
		return "redirect:../allproducts"; // Liittyy polkuun, jotta mennään yksi ylemmäs /../.
	}
	
	@RequestMapping(value = "/deletecat/{categoryId}", method = RequestMethod.GET)
	public String deleteCat(@PathVariable("categoryId") Long catId) {
		catRepository.deleteById(catId);
		return "redirect:../allcats"; // Liittyy polkuun, jotta mennään yksi ylemmäs.
	}
	
	@RequestMapping(value = "/editproduct/{id}", method = RequestMethod.GET)
	public String editProduct(@PathVariable("id") Long electronicproductId, Model model) { // Kokeiltu nimellä, ID ofc parempi.
		model.addAttribute("electronicProduct", productRepository.findById(electronicproductId));
		model.addAttribute("catslist", catRepository.findAll());
		//model.addAttribute("cats", catRepository.findAll());
		return "editproduct"; // Liittyy polkuun, jolla palautetaan suoraan editproduct. Ei redirect, vaan suora palautus. Redirect ei toimi.
	
	
}
	
	@RequestMapping(value = "/editcat/{categoryId}", method = RequestMethod.GET)
	public String editCat(@PathVariable("categoryId") Long catId, Model model) { // Kokeiltu nimellä, ID ofc parempi.
		model.addAttribute("eProductCategory", catRepository.findById(catId));
		model.addAttribute("catslist", catRepository.findAll());
		//model.addAttribute("cats", catRepository.findAll());
		return "editcategory"; // Liittyy polkuun, jolla palautetaan suoraan editcategory. Ei redirect, vaan suora palautus. Redirect ei toimi.
}
	
	
	
	
	
	
	
	
	
	
	//REST-availability and ENDPOINTS included in this work, REST API also available.
	
	//RESTful service to get all products.
	@CrossOrigin // Can use parameters here as well, like origins, methods, allowHeaders, exposeHeaders, allowCredentials, maxAge...
	@RequestMapping(value="/eproducts", method = RequestMethod.GET)
	public @ResponseBody List<ElectronicProduct> productListRest() {
		return (List<ElectronicProduct>) productRepository.findAll();
	}
	
	//RESTful service to get one product by id.
	@CrossOrigin
	@RequestMapping(value = "/eproducts/{id}", method = RequestMethod.GET)
	public @ResponseBody Optional <ElectronicProduct> findProductRest(@PathVariable Long id) { // Täytyy olla id, ei toimi categoryId tässä!
		return productRepository.findById(id); // Laitetaan util-importti optional, jotta voidaan käyttää PathVariablea myös tässä.
	
}
	
	   // RESTful service to save a new EProduct
	@CrossOrigin
    @RequestMapping(value="/eproducts", method = RequestMethod.POST) // POST kertoo, että tallennetaan tietoa.
    public @ResponseBody ElectronicProduct saveProductRest(@RequestBody ElectronicProduct eproduct) {	
    	return productRepository.save(eproduct);
    }
    
    // Home page of REST services, Template created with examples
    @RequestMapping(value="/resthome", method = RequestMethod.GET)
    public String getRestHome() {	
    	return "resthome"; // resthome.html, oma tiedosto, ei tule importtien kautta.
    }
}

