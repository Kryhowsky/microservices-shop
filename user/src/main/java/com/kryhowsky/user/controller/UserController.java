package com.kryhowsky.user.controller;

import com.kryhowsky.common.rest.UserDto;
import com.kryhowsky.user.mapper.UserMapper;
import com.kryhowsky.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public record UserController (UserMapper userMapper, UserService userService) {

    @PostMapping
    @Operation(description = "Allows to add new User.")
    public UserDto save(@RequestBody @Valid UserDto user) {
        return userMapper.toDto(userService.save(userMapper.toDao(user)));
    }

    @GetMapping("/{id}")
    @Operation(description = "Returns user by given Id.")
    public UserDto getUserById(@PathVariable Long id) {
        return userMapper.toDto(userService.getUserById(id));
    }

    @GetMapping
    @Operation(description = "Returns page of Users with specific size.")
    public Page<UserDto> getUserPage(@RequestParam int page, @RequestParam int size) {
        return userService.getPage(PageRequest.of(page, size)).map(userMapper::toDto);
    }

    @PutMapping("/{id}")
    @Operation(description = "Allows to update user specified by Id.")
    public UserDto updateUser(@RequestBody @Valid UserDto user, @PathVariable Long id) {
        return userMapper.toDto(userService.update(userMapper.toDao(user), id));
    }

    @DeleteMapping("/{id}")
    @Operation(description = "Allows to delete user specified by Id.")
    public void deleteUserById(@PathVariable Long id) {
        userService.delete(id);
    }

    @GetMapping("/current")
    @Operation(description = "Allows to get current user.")
    public UserDto getCurrentUser() {
        return userMapper.toDto(userService.getCurrentUser());
    }

}
