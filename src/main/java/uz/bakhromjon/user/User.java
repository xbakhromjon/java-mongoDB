package uz.bakhromjon.user;

import com.querydsl.core.annotations.QueryEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import uz.bakhromjon.passport.Passport;

import java.util.List;

/**
 * @author : Bakhromjon Khasanboyev
 * @username: @xbakhromjon
 * @since : 13/10/22, Thu, 15:17
 **/
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document
@QueryEntity
public class User {
    @Id
    private String ID;
    private String name;
    private Integer age;
    private List<Integer> nums;
    private List<Passport> passports;
}
