package org.zeraki.task.learninglanguagemoduleapi.models.exercise;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.zeraki.task.learninglanguagemoduleapi.models.lesson.Lesson;
import org.zeraki.task.learninglanguagemoduleapi.models.progress.UserProgress;

import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Entity(name = "exercises")
public class Exercise {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lesson_id", referencedColumnName = "id")
    @JsonIgnore
    private Lesson lesson;

    @OneToMany(mappedBy = "exercise", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<UserProgress> progress;
    private int score;


}
