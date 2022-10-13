package uz.bakhromjon;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

/**
 * @author : Bakhromjon Khasanboyev
 * @username: @xbakhromjon
 * @since : 13/10/22, Thu, 15:16
 **/
public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findById(String ID);

}