package Hardware.Store.Example.Hardware.Store.Ltd.domains;

import java.util.List; // NOT IN USE NOW. 

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional; // NOT IN USE NOW.

public interface UserRepository extends CrudRepository<User, Long> {
	User findByUsername(String username);
}
