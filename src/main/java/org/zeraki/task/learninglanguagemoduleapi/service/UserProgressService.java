package org.zeraki.task.learninglanguagemoduleapi.service;

import org.zeraki.task.learninglanguagemoduleapi.models.progress.UserProgress;
import org.zeraki.task.learninglanguagemoduleapi.models.progress.UserProgressDTO;

import java.util.List;

public interface UserProgressService {
    String saveUserProgress(int userScore,Long userId,Long exerciseId,Long lessonId);
    UserProgressDTO findAllByUserIdAndLessonId(Long userId, Long lessonId);

    boolean isLessonComplete(Long userId,Long lessonId);
    String recommendUserForNextLessonOrNot(Long userId,Long lessonId);
}
