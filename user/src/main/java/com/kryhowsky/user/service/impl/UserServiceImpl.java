package com.kryhowsky.user.service.impl;

import com.kryhowsky.user.model.User;
import com.kryhowsky.user.repository.RoleRepository;
import com.kryhowsky.user.repository.UserRepository;
import com.kryhowsky.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Collections;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    @Override
    public User save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        roleRepository.findByName("ROLE_USER").ifPresent(role -> user.setRoles(Collections.singleton(role)));
        return userRepository.save(user);
    }

    @Override
    @Transactional
    public User update(User user, Long id) {

        var userDb = getUserById(id);
        userDb.setLogin(user.getLogin());
        userDb.setEmail(user.getEmail());

        return userDb;
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public Page<User> getPage(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.getById(id);
    }

    @Override
    public User getCurrentUser() {
        return userRepository.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName()).orElseThrow(EntityNotFoundException::new);
    }
}
