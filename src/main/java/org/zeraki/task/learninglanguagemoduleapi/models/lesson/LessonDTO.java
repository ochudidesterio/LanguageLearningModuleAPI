package org.zeraki.task.learninglanguagemoduleapi.models.lesson;

import lombok.*;
import org.zeraki.task.learninglanguagemoduleapi.models.exercise.ExerciseScoreDTO;

import java.util.List;

@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class LessonDTO {
    private Long id;
    private String title;
    private List<ExerciseScoreDTO>exercises;

}
