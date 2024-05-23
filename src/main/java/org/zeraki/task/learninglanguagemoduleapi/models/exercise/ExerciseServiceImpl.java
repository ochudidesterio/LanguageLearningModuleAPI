package org.zeraki.task.learninglanguagemoduleapi.models.exercise;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.zeraki.task.learninglanguagemoduleapi.models.lesson.Lesson;
import org.zeraki.task.learninglanguagemoduleapi.repository.ExerciseRepository;
import org.zeraki.task.learninglanguagemoduleapi.repository.LessonRepository;
import org.zeraki.task.learninglanguagemoduleapi.service.ExerciseService;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExerciseServiceImpl implements ExerciseService {

    //Inject ExerciseRepository and LessonRepository
    private final ExerciseRepository exerciseRepository;
    private final LessonRepository lessonRepository;
    @Override
    public Exercise createExercise(Exercise exercise) {
        return exerciseRepository.save(exercise);
    }

    @Override
    public Optional<Exercise> getExerciseById(Long exerciseId) {
        return exerciseRepository.findById(exerciseId);
    }

    @Override
    public String addExerciseToLesson(Long exerciseId, Long lessonId) {
        // Find the lesson by its ID or throw an exception if not found
        Lesson lesson = lessonRepository.findById(lessonId)
                .orElseThrow(() -> new RuntimeException("Lesson not found"));

        // Find the exercise by its ID or throw an exception if not found
        Exercise exercise = getExerciseById(exerciseId)
                .orElseThrow(() -> new RuntimeException("Exercise not found"));

        // Set the lesson for the exercise and save
        exercise.setLesson(lesson);
        exerciseRepository.save(exercise);

        // Return a success message
        return "Exercise added to lesson successfully";
    }

    @Override
    public List<Exercise> getAllExercisesByLessonId(Long lessonId) {

        return exerciseRepository.findAllByLesson_Id(lessonId);
    }
}
