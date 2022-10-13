package uz.bakhromjon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author : Bakhromjon Khasanboyev
 * @username: @xbakhromjon
 * @since : 13/10/22, Thu, 15:22
 **/
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody User user) {
        return userService.create(user);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable String id) {
        return userService.get(id);
    }

    @GetMapping()
    public ResponseEntity<?> getAll() {
        return userService.getAll();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        return userService.delete(id);
    }

    @PatchMapping()
    public ResponseEntity<?> update(@RequestBody User user) {
        return userService.update(user);
    }
}
