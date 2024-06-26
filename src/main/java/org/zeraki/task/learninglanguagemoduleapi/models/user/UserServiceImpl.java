package org.zeraki.task.learninglanguagemoduleapi.models.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.zeraki.task.learninglanguagemoduleapi.models.enums.AccountType;
import org.zeraki.task.learninglanguagemoduleapi.repository.UserRepository;
import org.zeraki.task.learninglanguagemoduleapi.service.UserService;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    @Override
    public AppUser registerUser(AppUser appUser) {
        if(existsByUsername(appUser.getUsername())){
            throw new RuntimeException("User with username exists");
        }
        appUser.setRole(AccountType.USER);
        appUser.setPassword(passwordEncoder.encode(appUser.getPassword()));

        return userRepository.save(appUser);
    }




    @Override
    public List<AppUser> getAllUsers() {
        return userRepository.findAll();
    }



    @Override
    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }
}
