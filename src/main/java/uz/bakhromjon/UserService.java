package uz.bakhromjon;

import com.mongodb.client.result.UpdateResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author : Bakhromjon Khasanboyev
 * @username: @xbakhromjon
 * @since : 13/10/22, Thu, 15:20
 **/
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    public ResponseEntity<?> create(User user) {
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
        List<User> users = userRepository.findAll(sort);
        return users;
    }


    public List<User> findAllWithPageable() {
        Pageable pageableRequest = PageRequest.of(0, 1);
        Page<User> page = userRepository.findAll(pageableRequest);
        List<User> users = page.getContent();
        return users;
    }

}
