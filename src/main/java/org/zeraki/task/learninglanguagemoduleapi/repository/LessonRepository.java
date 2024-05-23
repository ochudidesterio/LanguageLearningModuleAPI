package org.zeraki.task.learninglanguagemoduleapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zeraki.task.learninglanguagemoduleapi.models.lesson.Lesson;

public interface LessonRepository extends JpaRepository<Lesson,Long> {
}
