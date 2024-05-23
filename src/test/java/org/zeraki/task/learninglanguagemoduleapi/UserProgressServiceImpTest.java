package org.zeraki.task.learninglanguagemoduleapi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.zeraki.task.learninglanguagemoduleapi.models.exercise.Exercise;
import org.zeraki.task.learninglanguagemoduleapi.models.lesson.Lesson;
import org.zeraki.task.learninglanguagemoduleapi.models.progress.UserProgressServiceImpl;
import org.zeraki.task.learninglanguagemoduleapi.models.user.AppUser;
import org.zeraki.task.learninglanguagemoduleapi.repository.ExerciseRepository;
import org.zeraki.task.learninglanguagemoduleapi.repository.LessonRepository;
import org.zeraki.task.learninglanguagemoduleapi.repository.UserProgressRepository;
import org.zeraki.task.learninglanguagemoduleapi.repository.UserRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
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

}

