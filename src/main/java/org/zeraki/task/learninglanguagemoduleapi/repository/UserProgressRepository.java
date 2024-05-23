package org.zeraki.task.learninglanguagemoduleapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.zeraki.task.learninglanguagemoduleapi.models.progress.UserProgress;

import java.util.List;

public interface UserProgressRepository extends JpaRepository<UserProgress,Long> {

    @Query("SELECT u FROM user_progress u WHERE u.appUser.id =:userId AND u.lesson.id =:lessonId")
    List<UserProgress>findAllByUserIdAndLessonId(@Param("userId") Long userId,@Param("lessonId") Long lessonId);
}
