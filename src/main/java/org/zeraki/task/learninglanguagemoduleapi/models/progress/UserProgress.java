package org.zeraki.task.learninglanguagemoduleapi.models.progress;

import jakarta.persistence.*;
import lombok.*;
import org.zeraki.task.learninglanguagemoduleapi.models.exercise.Exercise;
import org.zeraki.task.learninglanguagemoduleapi.models.lesson.Lesson;
import org.zeraki.task.learninglanguagemoduleapi.models.user.AppUser;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "user_progress")
public class UserProgress {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId", referencedColumnName = "id")
    private AppUser appUser;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "exerciseId",referencedColumnName = "id")
    private Exercise exercise;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lessonId",referencedColumnName = "id")
    private Lesson lesson;

    private int userScore;
}
