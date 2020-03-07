package hh.swd.bookstore.Bookstore.domains;


import java.util.List; 
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface BookRepository extends CrudRepository<Book, Long> {

    // List<Book> findByName(String name); // Omia metodeja.
    
    List<Book> findByYear(int year); //Esimerkki. Muista muuttaa listaksi!
    List<Book> findByIsbn(String isbn);
    //List<Book> findByAuthor(String author);
    List<Book> findByAuthor(@Param("author") String author);

    // CRUDRepository-rajapinnan parametrisoinnissa annetaan Entity-luokan nimi: tässä Book ja toisena 
    //parametrina pääavainsarakkeen tietotyyppi, tässä Long.
    
    // BookRepository periytyy (extends) CrudRepositorystä ja perii mm. metodiesittelyt 
    // findAll(), findById(), save(), deleteById().
}
