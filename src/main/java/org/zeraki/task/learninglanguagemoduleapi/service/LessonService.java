package org.zeraki.task.learninglanguagemoduleapi.service;

import org.zeraki.task.learninglanguagemoduleapi.models.lesson.Lesson;

import java.util.List;
import java.util.Optional;

public interface LessonService {
    Lesson createLesson(Lesson lesson);
    List<Lesson>getAllLessons();

}
