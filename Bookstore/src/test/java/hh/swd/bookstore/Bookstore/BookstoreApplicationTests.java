package hh.swd.bookstore.Bookstore;

import static org.assertj.core.api.Assertions.assertThat; 

import org.junit.jupiter.api.Test;
//import org.junit.Test; 
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import hh.swd.bookstore.Bookstore.webcontrollers.BookController;
import hh.swd.bookstore.Bookstore.webcontrollers.UserController;
import hh.swd.bookstore.Bookstore.webcontrollers.UserDetailServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
class BookstoreApplicationTests {

	@Test
	void contextLoadsOther() {
	}

	
	// SMOKE TESTS TO see if there are actually contents / stuff inside each of the controllers. Leaving out Web Layer / HttpRequests ...
	// ... since there are no such prints here and the port is determined by a working software.
	@Autowired
    private BookController bookController;
	@Autowired
	private UserController usController;
	@Autowired
	private UserDetailServiceImpl udsi;
	

    @Test
    public void contexLoads() throws Exception {
        assertThat(bookController).isNotNull();
    }
    
    @Test
    public void contexLoads1() throws Exception {
        assertThat(usController).isNotNull();
    }	
    
    @Test
    public void contexLoads2() throws Exception {
        assertThat(udsi).isNotNull();
    }	
}
