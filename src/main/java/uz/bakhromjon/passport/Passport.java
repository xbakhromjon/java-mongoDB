package uz.bakhromjon.passport;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author : Bakhromjon Khasanboyev
 * @username: @xbakhromjon
 * @since : 14/10/22, Fri, 13:52
 **/
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document
public class Passport {
    @Id
    private String ID;
    private String number;
}
