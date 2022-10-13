package uz.bakhromjon;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

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
public class User {
    @Id
    private String ID;
    private String name;
}
