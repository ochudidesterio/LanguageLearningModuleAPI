package org.zeraki.task.learninglanguagemoduleapi.models.userlessons;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class UserLessonDTO {
    private String username;
    private String lesson;
}
