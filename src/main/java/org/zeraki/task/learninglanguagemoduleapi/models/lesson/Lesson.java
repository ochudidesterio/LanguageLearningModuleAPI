package org.zeraki.task.learninglanguagemoduleapi.models.lesson;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.zeraki.task.learninglanguagemoduleapi.models.exercise.Exercise;
import org.zeraki.task.learninglanguagemoduleapi.models.progress.UserProgress;

import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Entity(name = "lessons")
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    @OneToMany(mappedBy = "lesson",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Exercise> exercises;
    @OneToMany(mappedBy = "lesson", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<UserProgress> progress;



}
