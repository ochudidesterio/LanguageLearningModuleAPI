package org.zeraki.task.learninglanguagemoduleapi;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.zeraki.task.learninglanguagemoduleapi.models.user.AppUser;
import org.zeraki.task.learninglanguagemoduleapi.service.UserService;

@SpringBootApplication
@RequiredArgsConstructor
public class LearningLanguageModuleApiApplication implements ApplicationRunner {

    private final UserService userService;


    public static void main(String[] args) {
        SpringApplication.run(LearningLanguageModuleApiApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if(!userService.existsByUsername("testuser")){
            AppUser appUser = AppUser.builder()
                    .firstname("testuser")
                    .lastname("testuser")
                    .password("password")
                    .username("testuser")
                    .build();
            userService.registerUser(appUser);
        }


    }
}
