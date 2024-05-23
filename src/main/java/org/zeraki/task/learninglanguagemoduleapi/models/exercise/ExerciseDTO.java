package org.zeraki.task.learninglanguagemoduleapi.models.exercise;

import lombok.*;
import org.zeraki.task.learninglanguagemoduleapi.models.lesson.Lesson;

@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ExerciseDTO {
    private Long id;
    private String description;
    private Lesson lesson;
    private int score;
}
