package com.kryhowsky.user.repository;

import com.kryhowsky.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
