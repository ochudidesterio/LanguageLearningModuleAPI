package org.zeraki.task.learninglanguagemoduleapi.models.progress;

import lombok.*;
import org.zeraki.task.learninglanguagemoduleapi.models.lesson.LessonDTO;
import org.zeraki.task.learninglanguagemoduleapi.models.user.AppUser;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserProgressDTO {
    private Long userId;
    private String username;
    private List<LessonDTO>lessons;
}
