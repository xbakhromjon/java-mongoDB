package uz.bakhromjon.passport;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author : Bakhromjon Khasanboyev
 * @username: @xbakhromjon
 * @since : 14/10/22, Fri, 14:01
 **/
public interface PassportRepository extends MongoRepository<Passport, String> {
}
