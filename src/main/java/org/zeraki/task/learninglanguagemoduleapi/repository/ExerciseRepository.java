package org.zeraki.task.learninglanguagemoduleapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.zeraki.task.learninglanguagemoduleapi.models.exercise.Exercise;

import java.util.List;

public interface ExerciseRepository extends JpaRepository< Exercise,Long> {
    List<Exercise>findAllByLesson_Id(@Param("lessonId") Long lessonId);
}
