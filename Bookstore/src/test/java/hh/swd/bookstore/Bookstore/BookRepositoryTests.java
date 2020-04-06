package hh.swd.bookstore.Bookstore;

import static org.assertj.core.api.Assertions.assertThat;  

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import hh.swd.bookstore.Bookstore.domains.Book;
import hh.swd.bookstore.Bookstore.domains.BookRepository;
import hh.swd.bookstore.Bookstore.domains.Category;
import hh.swd.bookstore.Bookstore.domains.CategoryRepository;
import hh.swd.bookstore.Bookstore.domains.User;
import hh.swd.bookstore.Bookstore.domains.UserRepository;


@RunWith(SpringRunner.class)
@DataJpaTest
public class BookRepositoryTests {

    @Autowired
    private BookRepository bookRepository;
    @Autowired 
    private CategoryRepository catRepository;
    @Autowired
    private UserRepository usRepo;

    @Test
    public void findByAuthorShouldReturnYear() {
        List<Book> books = bookRepository.findByAuthor("Matts Johansson");
        assertThat(books).hasSize(1);
        assertThat(books.get(0).getYear()).isEqualTo(1994); // 1995 tested, ended in failure. Now testing with the correct year.
        // Trying to determine if Tests work. They do work //19.03.2020.// - Arttu K.
    }
    
    @Test
    public void createNewBookTest() {
    	Book book = new Book("Tesla", "Matts Johansson", 1994, "2100-2345-113", 54.50, catRepository.findByName("Horror").get(0));
    	bookRepository.save(book);
    	assertThat(book.getId()).isNotNull();
    }  
    
    @Test
    public void deleteBooksTest() {
    	Book book1 = new Book("Mathematics", "Robert S. Pearson", 2015, "2100-2345-143", 59.50, catRepository.findByName("Literature").get(0));
    	bookRepository.save(book1);
    	bookRepository.deleteAll();
    	assertThat(bookRepository.count()).isEqualTo(0); // Testing deletes with size.
    }   
    
    @Test
    public void findByNameCategoryTest() {
    	List<Category> cats = catRepository.findByName("Horror");
    	assertThat(cats).hasSize(1);
        assertThat(cats.get(0).getName()).isEqualTo("Horror"); 
    }
    
    @Test
    public void CreateNewCategoryTest() {
       	Category cat = new Category("Cooking");
    	catRepository.save(cat);
    	assertThat(cat.getCategoryId()).isNotNull();
    }
    
    @Test
    public void deleteCatsTests() {
    	Category cat = new Category("Science-Fiction");
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