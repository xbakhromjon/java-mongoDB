package uz.bakhromjon.user;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import uz.bakhromjon.user.User;

import java.util.List;
import java.util.Optional;

/**
 * @author : Bakhromjon Khasanboyev
 * @username: @xbakhromjon
 * @since : 13/10/22, Thu, 15:16
 **/
@Repository
public interface UserRepository extends MongoRepository<User, String> {
    // generated
    Optional<User> findByID(String ID);

    List<User> findByName(String name);

    List<User> findByNameStartingWith(String regexp);

    List<User> findByNameEndingWith(String regexp);

    List<User> findByAgeBetween(int ageGT, int ageLT);

    List<User> findByNameLikeOrderByAgeAsc(String name);

    // query
    @Query("{ 'name' : ?0 }")
    List<User> findUsersByName(String name);

    @Query("{ 'name' : { $regex: ?0 } }")
    List<User> findUsersByRegexpName(String regexp);

    @Query("{ 'age' : { $gt: ?0, $lt: ?1 } }")
    List<User> findUsersByAgeBetween(int ageGT, int ageLT);
}
