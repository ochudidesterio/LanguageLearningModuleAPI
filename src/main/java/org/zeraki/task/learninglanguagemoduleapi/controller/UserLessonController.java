package org.zeraki.task.learninglanguagemoduleapi.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.zeraki.task.learninglanguagemoduleapi.service.UserLessonService;

@RestController
@RequestMapping("/userlesson")
@CrossOrigin
@RequiredArgsConstructor
public class UserLessonController {
    private final UserLessonService userLessonService;

    @PostMapping("/enroll")
    @Operation(summary = "Enroll a user to a lesson")
    public ResponseEntity<?>enrollUserToLesson(@RequestParam(name = "userId") Long userId,
                                               @RequestParam(name = "lessonId") Long lessonId){
        try{
           return ResponseEntity.ok(userLessonService.enroleUserToLesson(userId, lessonId));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
