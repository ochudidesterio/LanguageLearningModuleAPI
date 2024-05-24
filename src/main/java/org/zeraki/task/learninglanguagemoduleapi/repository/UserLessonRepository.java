package org.zeraki.task.learninglanguagemoduleapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zeraki.task.learninglanguagemoduleapi.models.userlessons.UserLesson;

public interface UserLessonRepository extends JpaRepository<UserLesson,Long> {
}
