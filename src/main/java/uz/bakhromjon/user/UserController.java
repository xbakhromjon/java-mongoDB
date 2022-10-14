package uz.bakhromjon.user;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author : Bakhromjon Khasanboyev
 * @username: @xbakhromjon
 * @since : 13/10/22, Thu, 15:22
 **/
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody User user) {
        return service.create(user);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable String id) {
        return service.get(id);
    }

    @GetMapping()
    public ResponseEntity<?> getAll() {
        return service.getAll();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        return service.delete(id);
    }

    @PatchMapping()
    public ResponseEntity<?> update(@RequestBody User user) {
        return service.update(user);
    }

    @GetMapping("/byName/{name}")
    public ResponseEntity<?> getByName(@PathVariable String name) {
        return service.getByName(name);
    }

}
