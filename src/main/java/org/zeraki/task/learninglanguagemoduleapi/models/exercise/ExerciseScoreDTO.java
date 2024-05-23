package org.zeraki.task.learninglanguagemoduleapi.models.exercise;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class ExerciseScoreDTO {
    private Long id;
    private int maxScore;
    private int userScore;
    private String description;
}
