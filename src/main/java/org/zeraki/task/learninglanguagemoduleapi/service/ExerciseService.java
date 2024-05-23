package org.zeraki.task.learninglanguagemoduleapi.service;

import org.zeraki.task.learninglanguagemoduleapi.models.exercise.Exercise;

import java.util.List;
import java.util.Optional;

public interface ExerciseService {
    Exercise createExercise(Exercise exercise);
    Optional<Exercise>getExerciseById(Long exerciseId);
    String addExerciseToLesson(Long exerciseId,Long lessonId);
    List<Exercise> getAllExercisesByLessonId(Long lessonId);
}
