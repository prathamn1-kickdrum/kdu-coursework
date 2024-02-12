package com.kdu.smarthome.repository;

import com.kdu.smarthome.entity.User;
import com.kdu.smarthome.util.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByUsername(String userName);

    Optional<User> findByRole(Role role);
}
