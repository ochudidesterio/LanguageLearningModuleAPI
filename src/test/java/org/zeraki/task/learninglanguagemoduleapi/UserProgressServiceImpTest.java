package org.zeraki.task.learninglanguagemoduleapi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.zeraki.task.learninglanguagemoduleapi.models.exercise.Exercise;
import org.zeraki.task.learninglanguagemoduleapi.models.lesson.Lesson;
import org.zeraki.task.learninglanguagemoduleapi.models.progress.UserProgress;
import org.zeraki.task.learninglanguagemoduleapi.models.progress.UserProgressDTO;
import org.zeraki.task.learninglanguagemoduleapi.models.progress.UserProgressServiceImpl;
import org.zeraki.task.learninglanguagemoduleapi.models.user.AppUser;
import org.zeraki.task.learninglanguagemoduleapi.repository.ExerciseRepository;
import org.zeraki.task.learninglanguagemoduleapi.repository.LessonRepository;
import org.zeraki.task.learninglanguagemoduleapi.repository.UserProgressRepository;
import org.zeraki.task.learninglanguagemoduleapi.repository.UserRepository;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserProgressServiceImplTest {

    @InjectMocks
    private UserProgressServiceImpl userProgressService;

    @Mock
    private UserProgressRepository userProgressRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private ExerciseRepository exerciseRepository;

    @Mock
    private LessonRepository lessonRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveUserProgress_Success() {
        // Mock input parameters
        int userScore = 80;
        Long userId = 1L;
        Long exerciseId = 2L;
        Long lessonId = 3L;

        // Mock dependencies
        AppUser user = new AppUser();
        Exercise exercise = new Exercise();
        Lesson lesson = new Lesson();

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(exerciseRepository.findById(exerciseId)).thenReturn(Optional.of(exercise));
        when(lessonRepository.findById(lessonId)).thenReturn(Optional.of(lesson));

        String result = userProgressService.saveUserProgress(userScore, userId, exerciseId, lessonId);

        verify(userProgressRepository).save(any());

        assertEquals("saved", result);
    }


    @Test
    void findAllByUserIdAndLessonId_ShouldReturnUserProgressDTO() {
        Long userId = 1L;
        Long lessonId = 1L;

        AppUser appUser = new AppUser();
        appUser.setId(userId);
        appUser.setUsername("testUser1");

        Lesson lesson = new Lesson();
        lesson.setId(lessonId);
        lesson.setTitle("Test Lesson");

        Exercise exercise = new Exercise();
        exercise.setId(1L);
        exercise.setScore(100);
        exercise.setDescription("Test Exercise");

        UserProgress userProgress = UserProgress.builder()
                .appUser(appUser)
                .exercise(exercise)
                .lesson(lesson)
                .userScore(80)
                .build();

        when(userRepository.findById(userId)).thenReturn(Optional.of(appUser));
        when(userProgressRepository.findAllByUserIdAndLessonId(userId, lessonId)).thenReturn(Collections.singletonList(userProgress));
        when(exerciseRepository.findById(exercise.getId())).thenReturn(Optional.of(exercise));

        UserProgressDTO result = userProgressService.findAllByUserIdAndLessonId(userId, lessonId);

        assertNotNull(result);
        assertEquals(userId, result.getUserId());
        assertEquals("testUser1", result.getUsername());
        assertFalse(result.getLessons().isEmpty());
        assertEquals(lessonId, result.getLessons().get(0).getId());
        assertEquals("Test Lesson", result.getLessons().get(0).getTitle());
        assertFalse(result.getLessons().get(0).getExercises().isEmpty());
        assertEquals(1L, result.getLessons().get(0).getExercises().get(0).getId());
        assertEquals(100, result.getLessons().get(0).getExercises().get(0).getMaxScore());
        assertEquals(80, result.getLessons().get(0).getExercises().get(0).getUserScore());
        assertEquals("Test Exercise", result.getLessons().get(0).getExercises().get(0).getDescription());

        verify(userRepository).findById(userId);
        verify(userProgressRepository).findAllByUserIdAndLessonId(userId, lessonId);
        verify(exerciseRepository).findById(exercise.getId());
    }

    @Test
    void isLessonComplete_ShouldReturnTrue() {
        Long userId = 1L;
        Long lessonId = 1L;

        AppUser appUser = new AppUser();
        appUser.setId(userId);

        Lesson lesson = new Lesson();
        lesson.setId(lessonId);

        Exercise exercise = new Exercise();
        exercise.setId(1L);
        exercise.setScore(100);

        UserProgress userProgress = UserProgress.builder()
                .appUser(appUser)
                .exercise(exercise)
                .lesson(lesson)
                .userScore(80)
                .build();

        when(lessonRepository.findById(lessonId)).thenReturn(Optional.of(lesson));
        when(userRepository.findById(userId)).thenReturn(Optional.of(appUser));
        when(exerciseRepository.findAllByLesson_Id(lessonId)).thenReturn(Collections.singletonList(exercise));
        when(userProgressRepository.findAllByUserIdAndLessonId(userId, lessonId)).thenReturn(Collections.singletonList(userProgress));
        when(exerciseRepository.findById(exercise.getId())).thenReturn(Optional.of(exercise));

        boolean result = userProgressService.isLessonComplete(userId, lessonId);

        assertTrue(result);

    }

    @Test
    void isLessonComplete_ShouldReturnFalse_WhenNoExercises() {
        Long userId = 1L;
        Long lessonId = 1L;

        AppUser appUser = new AppUser();
        appUser.setId(userId);

        Lesson lesson = new Lesson();
        lesson.setId(lessonId);

        when(lessonRepository.findById(lessonId)).thenReturn(Optional.of(lesson));
        when(userRepository.findById(userId)).thenReturn(Optional.of(appUser));
        when(exerciseRepository.findAllByLesson_Id(lessonId)).thenReturn(Collections.emptyList());

        boolean result = userProgressService.isLessonComplete(userId, lessonId);

        assertFalse(result);

        verify(lessonRepository).findById(lessonId);
        verify(userRepository).findById(userId);
        verify(exerciseRepository).findAllByLesson_Id(lessonId);
    }

}

