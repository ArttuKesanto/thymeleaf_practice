package Hardware.Store.Example.Hardware.Store.Ltd.domains;

import java.util.List;   
import org.springframework.data.repository.CrudRepository;

public interface EProductCategoryRepository extends CrudRepository<EProductCategory, Long> {

    List<EProductCategory> findByName(String categoryName);
    
}
