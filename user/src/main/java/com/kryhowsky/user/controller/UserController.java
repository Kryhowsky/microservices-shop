package com.kryhowsky.user.controller;

import com.kryhowsky.user.model.User;
import com.kryhowsky.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    @Operation(description = "Allows to add new User.")
    public User save(@RequestBody User user) {
        return userService.save(user);
    }

    @GetMapping("/{id}")
    @Operation(description = "Returns user by given Id.")
    public User getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @GetMapping
    @Operation(description = "Returns page of Users with specific size.")
    public Page<User> getUserPage(@RequestParam int page, @RequestParam int size) {
        return userService.getPage(PageRequest.of(page, size));
    }

    @PutMapping("/{id}")
    @Operation(description = "Allows to update user specified by Id.")
    public User updateUser(@RequestBody User user, @PathVariable Long id) {
        return userService.update(user, id);
    }

    @DeleteMapping("/{id}")
    @Operation(description = "Allows to delete user specified by Id.")
    public void deleteUserById(@PathVariable Long id) {
        userService.delete(id);
    }

}
