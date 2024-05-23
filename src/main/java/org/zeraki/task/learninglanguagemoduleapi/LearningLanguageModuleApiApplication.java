package org.zeraki.task.learninglanguagemoduleapi;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.zeraki.task.learninglanguagemoduleapi.service.UserService;

@SpringBootApplication
@RequiredArgsConstructor
public class LearningLanguageModuleApiApplication  {


    public static void main(String[] args) {
        SpringApplication.run(LearningLanguageModuleApiApplication.class, args);
    }

}
