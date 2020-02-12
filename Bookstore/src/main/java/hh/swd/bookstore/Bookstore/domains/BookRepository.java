package hh.swd.bookstore.Bookstore.domains;


import java.util.List;
import org.springframework.data.repository.CrudRepository;


public interface BookRepository extends CrudRepository<Book, Long> {

    List<Book> findByName(String name);
    
}
