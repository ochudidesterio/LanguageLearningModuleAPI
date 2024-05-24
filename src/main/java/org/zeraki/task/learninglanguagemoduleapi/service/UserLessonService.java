package org.zeraki.task.learninglanguagemoduleapi.service;

import org.zeraki.task.learninglanguagemoduleapi.models.userlessons.UserLessonDTO;

public interface UserLessonService {
    UserLessonDTO enroleUserToLesson(Long userId,Long lessonId);
}
