package org.zeraki.task.learninglanguagemoduleapi.models.userlessons;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.zeraki.task.learninglanguagemoduleapi.models.lesson.Lesson;
import org.zeraki.task.learninglanguagemoduleapi.models.user.AppUser;
import org.zeraki.task.learninglanguagemoduleapi.repository.LessonRepository;
import org.zeraki.task.learninglanguagemoduleapi.repository.UserLessonRepository;
import org.zeraki.task.learninglanguagemoduleapi.repository.UserRepository;
import org.zeraki.task.learninglanguagemoduleapi.service.UserLessonService;

@Service
@RequiredArgsConstructor
public class UserLessonServiceImpl implements UserLessonService {
    private final UserLessonRepository userLessonRepository;
    private final UserRepository userRepository;
    private final LessonRepository lessonRepository;

    @Override
    public UserLessonDTO enroleUserToLesson(Long userId, Long lessonId) {
        AppUser appUser = userRepository.findById(userId).orElseThrow(()->new RuntimeException("User with id not found"));
        Lesson lesson = lessonRepository.findById(lessonId).orElseThrow(()->new RuntimeException("Lesson with id not found"));
        UserLesson obj = UserLesson.builder()
                .user(appUser)
                .lesson(lesson)
                .build();
        userLessonRepository.save(obj);

        UserLessonDTO userLessonDTO = UserLessonDTO.builder()
                .lesson(lesson.getTitle())
                .username(appUser.getUsername())
                .build();


        return userLessonDTO;
    }
}
