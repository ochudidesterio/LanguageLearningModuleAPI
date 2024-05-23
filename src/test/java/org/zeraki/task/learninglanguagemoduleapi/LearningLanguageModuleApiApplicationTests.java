package org.zeraki.task.learninglanguagemoduleapi;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.zeraki.task.learninglanguagemoduleapi.models.enums.AccountType;
import org.zeraki.task.learninglanguagemoduleapi.models.exercise.Exercise;
import org.zeraki.task.learninglanguagemoduleapi.models.lesson.Lesson;
import org.zeraki.task.learninglanguagemoduleapi.models.user.AppUser;
import org.zeraki.task.learninglanguagemoduleapi.service.ExerciseService;
import org.zeraki.task.learninglanguagemoduleapi.service.LessonService;
import org.zeraki.task.learninglanguagemoduleapi.service.UserService;

@SpringBootTest
class LearningLanguageModuleApiApplicationTests {
    @Autowired
   private UserService userService;
    @Autowired
    private LessonService lessonService;
    @Autowired
    private ExerciseService exerciseService;

    @Test
    void contextLoads() {
    }


    @Test
    public void testRegisterUser(){
        AppUser user = new AppUser();
        user.setFirstname("firstName");
        user.setLastname("lastName");
        user.setRole(AccountType.USER);
        user.setPassword("password");
        AppUser appUser = userService.registerUser(user);
        assertNotNull(appUser);
        assertEquals("firstName",appUser.getFirstname());
    }

    @Test
    public void testCreateLesson(){
        Lesson lesson = new Lesson();
        lesson.setTitle("lesson1");
        Lesson savedLesson = lessonService.createLesson(lesson);
        assertNotNull(savedLesson);
        assertEquals("lesson1",savedLesson.getTitle());

    }

    @Test
    public void testCreateExercise(){
        Exercise exercise = new Exercise();
        exercise.setDescription("exercise1");
        exercise.setScore(20);
        Exercise savedExercise = exerciseService.createExercise(exercise);
        assertNotNull(savedExercise);
        assertEquals("exercise1",savedExercise.getDescription());
        assertEquals(20,savedExercise.getScore());
    }

}
