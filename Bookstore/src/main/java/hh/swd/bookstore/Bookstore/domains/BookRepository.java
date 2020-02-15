package hh.swd.bookstore.Bookstore.domains;


import java.util.List;
import org.springframework.data.repository.CrudRepository;


public interface BookRepository extends CrudRepository<Book, Long> {

    // List<Book> findByName(String name); // Omia metodeja.
    
    public Book findByYear(int year); //Esimerkki.
    public Book findByIsbn(String isbn);
    public Book findByAuthor(String author);
    // CRUDRepository-rajapinnan parametrisoinnissa annetaan Entity-luokan nimi: tässä Book ja toisena 
    //parametrina pääavainsarakkeen tietotyyppi, tässä Long.
    
    // BookRepository periytyy (extends) CrudRepositorystä ja perii mm. metodiesittelyt 
    // findAll(), findById(), save(), deleteById().
}
