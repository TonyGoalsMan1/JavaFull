package tech.reliab.course.nikiforovda.bank.controller.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.reliab.course.nikiforovda.bank.entity.User;
import tech.reliab.course.nikiforovda.bank.model.UserRequest;
import tech.reliab.course.nikiforovda.bank.service.UserService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserControllerImpl {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<User> createUser(UserRequest userRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(userRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(int id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<User> updateUser(int id, String name) {
        return ResponseEntity.ok(userService.updateUser(id, name));
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(int id) {
        return ResponseEntity.ok(userService.getUserDtoById(id));
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }
}
