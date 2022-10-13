package uz.bakhromjon;

import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

/**
 * @author : Bakhromjon Khasanboyev
 * @username: @xbakhromjon
 * @since : 13/10/22, Thu, 21:31
 **/
@Repository
public class UserRepo2 {
    @Autowired
    private MongoTemplate mongoTemplate;


    public User save(User user) {
        User saved = mongoTemplate.save(user, "user");
        return saved;
    }

    public User update(User user) {
        user = mongoTemplate.findOne(
                Query.query(Criteria.where("name").is("Jack")), User.class);
        user.setName(user.getName());
        User saved = mongoTemplate.save(user, "user");
        return saved;
    }

    public boolean updateFirst(User user) {
        Query query = new Query();
        query.addCriteria(Criteria.where("name").is("Alex"));
        Update update = new Update();
        update.set("name", user.getName());
        UpdateResult updateResult = mongoTemplate.updateFirst(query, update, User.class);
        return updateResult.getMatchedCount() != 0;
    }


    public boolean updateMulti() {
        Query query = new Query();
        query.addCriteria(Criteria.where("name").is("Eugen"));
        Update update = new Update();
        update.set("name", "Victor");
        UpdateResult updateResult = mongoTemplate.updateMulti(query, update, User.class);
        return updateResult.getMatchedCount() != 0;
    }

    public User findAndModify() {
        Query query = new Query();
        query.addCriteria(Criteria.where("name").is("Markus"));
        Update update = new Update();
        update.set("name", "Nick");
        User user = mongoTemplate.findAndModify(query, update, User.class);
        return user;
    }


    public boolean upsert() {
        Query query = new Query();
        query.addCriteria(Criteria.where("name").is("Markus"));
        Update update = new Update();
        update.set("name", "Nick");
        UpdateResult updateResult = mongoTemplate.upsert(query, update, User.class);
        return updateResult.getModifiedCount() != 0;
    }

    public boolean remove(User user) {
        DeleteResult result = mongoTemplate.remove(user, "user");
        return result.getDeletedCount() != 0;
    }


}
