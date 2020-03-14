package Hardware.Store.Example.Hardware.Store.Ltd.domains;


import java.util.List;  
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;



public interface EProductRepository extends CrudRepository<ElectronicProduct, Long> {

    // List<Book> findByName(String name); // Omia metodeja.
    
    List<ElectronicProduct> findByYear(int year); //Esimerkki.
    List<ElectronicProduct> findByName(String name);
    List<ElectronicProduct> findByRating(String rating);
    List<ElectronicProduct> findByAvailability(@Param("availability") String availability); // REST-stuff, searching.
    // CRUDRepository-rajapinnan parametrisoinnissa annetaan Entity-luokan nimi: tässä Product ja toisena 
    //parametrina pääavainsarakkeen tietotyyppi, tässä Long.
    
    // EProductRepository periytyy (extends) CrudRepositorystä ja perii mm. metodiesittelyt 
    // findAll(), findById(), save(), deleteById().
}
