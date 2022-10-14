package uz.bakhromjon.user;

import com.mongodb.client.result.UpdateResult;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.bakhromjon.passport.Passport;
import uz.bakhromjon.passport.PassportRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author : Bakhromjon Khasanboyev
 * @username: @xbakhromjon
 * @since : 13/10/22, Thu, 15:20
 **/
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;


    private final PassportRepository passportRepository;

    private final MongoTemplate mongoTemplate;

    public ResponseEntity<?> create(User user) {
        List<Passport> passports = user.getPassports();
        List<Passport> passportList = new ArrayList<>();
        for (Passport passport : passports) {
            Passport saved = passportRepository.save(passport);
            passportList.add(passport);
        }
        user.setPassports(passportList);
        User saved = userRepository.save(user);
        return ResponseEntity.ok(saved);
    }


    public ResponseEntity<?> get(String ID) {
        Optional<User> optional = userRepository.findById(ID);
        User user = optional.orElseThrow(() -> {
            throw new RuntimeException("User not Found");
        });
        return ResponseEntity.ok(user);
    }

    public ResponseEntity<?> getAll() {
        List<User> allUsers = userRepository.findAll();
        return ResponseEntity.ok(allUsers);
    }


    public ResponseEntity<?> delete(String id) {
        userRepository.deleteById(id);
        return ResponseEntity.ok(true);
    }

    public ResponseEntity<?> update(User user) {
        Query query = new Query();
        query = query.addCriteria(Criteria.where("_id").is(user.getID()));
        Update update = new Update();
        update.set("name", user.getName());
        UpdateResult updateResult = mongoTemplate.updateMulti(query, update, User.class);
        if (updateResult.getModifiedCount() > 0) {
            return ResponseEntity.ok(true);
        }
        return ResponseEntity.ok(false);
    }

    public List<User> findAllWithSort() {
        Sort sort = Sort.by(Sort.Direction.ASC, "name");
        return userRepository.findAll(sort);
    }


    public List<User> findAllWithPageable() {
        Pageable pageableRequest = PageRequest.of(0, 1);
        Page<User> page = userRepository.findAll(pageableRequest);
        return page.getContent();
    }

    public ResponseEntity<?> getByName(String name) {
        List<User> users = userRepository.findByName(name);
        return ResponseEntity.ok(users);
    }

    public List<User> getByRegex(String regex) {
//        List<User> users = userRepository.findByNameStartingWith("A");
//        List<User> users = userRepository.findByNameEndingWith("c");
        return userRepository.findByNameStartingWith(regex);
    }

    public List<User> getByRegex2(String regex) {
//        List<User> users = userRepository.findUsersByRegexpName("^A");
//        List<User> users = userRepository.findUsersByRegexpName("c$");
        return userRepository.findUsersByRegexpName(regex);
    }

    // Qclasses
    public List<User> qClassEq() {
//        QUser qUser = new QUser("user");
//        Predicate predicate = qUser.name.eq("Eric");
//        List<User> users = (List<User>) userRepository.findAll(predicate);
//        return users;
        return null;
    }
}
