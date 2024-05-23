package org.zeraki.task.learninglanguagemoduleapi.service;

import org.zeraki.task.learninglanguagemoduleapi.models.user.AppUser;

import java.util.List;
import java.util.Optional;

public interface UserService {
    AppUser registerUser(AppUser appUser);
    Optional<AppUser>findUserById(Long userId);
    List<AppUser>getAllUsers();
    Optional<AppUser>findByUsername(String username);
}
