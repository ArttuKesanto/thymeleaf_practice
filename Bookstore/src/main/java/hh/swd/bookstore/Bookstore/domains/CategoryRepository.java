package hh.swd.bookstore.Bookstore.domains;


import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface CategoryRepository extends CrudRepository<Category, Long> {

    List<Category> findByName(String categoryName);
   // List<Category> findByName(@Param("name") String name);
    
}