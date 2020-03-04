package Hardware.Store.Example.Hardware.Store.Ltd.domains;


import java.util.List; 
import org.springframework.data.repository.CrudRepository;


public interface EProductRepository extends CrudRepository<ElectronicProduct, Long> {

    // List<Book> findByName(String name); // Omia metodeja.
    
    List<ElectronicProduct> findByYear(int year); //Esimerkki.
    List<ElectronicProduct> findByName(String name);
    List<ElectronicProduct> findByRating(String rating);
    // CRUDRepository-rajapinnan parametrisoinnissa annetaan Entity-luokan nimi: tässä Book ja toisena 
    //parametrina pääavainsarakkeen tietotyyppi, tässä Long.
    
    // BookRepository periytyy (extends) CrudRepositorystä ja perii mm. metodiesittelyt 
    // findAll(), findById(), save(), deleteById().
}
