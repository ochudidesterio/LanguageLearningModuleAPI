package org.zeraki.task.learninglanguagemoduleapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zeraki.task.learninglanguagemoduleapi.models.user.AppUser;

import java.util.Optional;

public interface UserRepository extends JpaRepository<AppUser,Long> {
    Optional<AppUser> findByUsername(String username);
    boolean existsByUsername(String username);

}
