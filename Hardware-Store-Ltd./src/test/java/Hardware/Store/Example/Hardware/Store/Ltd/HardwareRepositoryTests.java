package Hardware.Store.Example.Hardware.Store.Ltd;


import static org.assertj.core.api.Assertions.assertThat; 

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import Hardware.Store.Example.Hardware.Store.Ltd.domains.EProductCategory;
import Hardware.Store.Example.Hardware.Store.Ltd.domains.EProductCategoryRepository;
import Hardware.Store.Example.Hardware.Store.Ltd.domains.EProductRepository;
import Hardware.Store.Example.Hardware.Store.Ltd.domains.ElectronicProduct;
import Hardware.Store.Example.Hardware.Store.Ltd.domains.User;
import Hardware.Store.Example.Hardware.Store.Ltd.domains.UserRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class HardwareRepositoryTests {

		@Autowired
	    private EProductRepository productRepository;
	    @Autowired 
	    private EProductCategoryRepository catRepository;
	    @Autowired
	    private UserRepository usRepo;

	    @Test
	    public void findByName_Should_Return_Correct_Year_Of_Product_Test() {
	        List<ElectronicProduct> products = productRepository.findByName("Microsoft KB 7000");
	        assertThat(products).hasSize(1);
	        assertThat(products.get(0).getYear()).isEqualTo(2020); // 2000 tested, ended in failure. Now testing with the correct year.
	        // Trying to determine if Tests work. They do work //20.03.2020.// - Arttu K.
	    }
	    
	    @Test
	    public void createNewProductTest() {
	    	ElectronicProduct eProduct = new ElectronicProduct("Logitech Xy 144Hz", 2016, "4/5", "Available", "Such a good display with an amazing quality.", 145.50, catRepository.findByName("Display").get(0));
	    	productRepository.save(eProduct);
	    	assertThat(eProduct.getId()).isNotNull();
	    }  
	    
	    @Test
	    public void deleteProductsTest() {
	    	ElectronicProduct eProduct = new ElectronicProduct("Logitech Xy 144Hz", 2016, "4/5", "Available", "Such a good display with an amazing quality.", 145.50, catRepository.findByName("Display").get(0));
	    	productRepository.save(eProduct); // One save just in case the repo was empty to begin with. Which it def. should not be.
	    	productRepository.deleteAll();
	    	assertThat(productRepository.count()).isEqualTo(0); // Testing deletes with size.
	    }   
	    
	    @Test
	    public void findByNameCategoryTest() {
	    	List<EProductCategory> cats = catRepository.findByName("Display");
	    	assertThat(cats).hasSize(1);
	        assertThat(cats.get(0).getName()).isEqualTo("Display");
	        assertThat(cats.get(0).getCategoryId()).isNotNull();
	    }
	    
	    @Test
	    public void CreateNewCategoryTest() {
	       	EProductCategory cat = new EProductCategory("Cable");
	    	catRepository.save(cat);
	    	assertThat(cat.getCategoryId()).isNotNull();
	    }
	    
	    @Test
	    public void deleteCatsTests() {
	    	EProductCategory cat = new EProductCategory("Printer");
	    	catRepository.save(cat);
	    	catRepository.deleteAll();
	    	assertThat(catRepository.count()).isEqualTo(0); // Testing deletes with size.
	    }   
	    
	    @Test
	    public void findByUsernameUserTest() {
	        User user = usRepo.findByUsername("user");
	        assertThat(user.getUsername()).isNotNull();
	        assertThat(user.getRole()).isEqualTo("USER");
	    }
	    
	    @Test
	    public void createNewUserTest() {
	    	User user = new User("Henrik", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", "USER");
	    	usRepo.save(user);
	    	assertThat(user.getId()).isNotNull();
	    }  
	    
	    @Test
	    public void deleteAllUsersTest() {
	    	User user = new User("Johann", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", "USER");
	    	usRepo.save(user);
	    	usRepo.deleteAll();
	    	assertThat(usRepo.count()).isEqualTo(0); // Testing deletes with size. Everything works - need to have different usernames...
	    	// .. because of constraints in the SQL tables and columns - good and efficient.
	    }   

	}