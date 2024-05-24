package org.zeraki.task.learninglanguagemoduleapi.models.lesson;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.zeraki.task.learninglanguagemoduleapi.repository.LessonRepository;
import org.zeraki.task.learninglanguagemoduleapi.service.LessonService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LessonServiceImpl implements LessonService {
    private final LessonRepository lessonRepository;
    @Override
    public Lesson createLesson(Lesson lesson) {
        return lessonRepository.save(lesson);
    }



    @Override
    public List<Lesson> getAllLessons() {
        return lessonRepository.findAll();
    }
}
