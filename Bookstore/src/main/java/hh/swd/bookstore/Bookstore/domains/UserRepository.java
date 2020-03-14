package hh.swd.bookstore.Bookstore.domains;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

public interface UserRepository extends CrudRepository<User, Long> {
	User findByUsername(String username);
}
