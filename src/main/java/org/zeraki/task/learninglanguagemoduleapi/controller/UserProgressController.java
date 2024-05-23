package org.zeraki.task.learninglanguagemoduleapi.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.zeraki.task.learninglanguagemoduleapi.service.UserProgressService;

@RestController
@RequestMapping("/progress")
@RequiredArgsConstructor
@CrossOrigin
public class UserProgressController {
    private final UserProgressService userProgressService;

    @PostMapping("/save")
    @Operation(summary = "Save user score for each exercise by passing userId, exerciseId and userScore as parameters")
    public ResponseEntity<?>saveUserScore(@RequestParam(name = "userId") Long userId,
                                          @RequestParam(name = "exerciseId") Long exerciseId,
                                          @RequestParam(name = "lessonId") Long lessonId,
                                          @RequestParam(name = "userScore") int userScore){
        try{
           return ResponseEntity.ok(userProgressService.saveUserProgress(userScore,userId,exerciseId,lessonId));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }
    }

    @GetMapping("/get")
    @Operation(summary = "Get progress by userId")
    public ResponseEntity<?>findallByUserId(@RequestParam(name = "userId") Long userId,
                                            @RequestParam(name = "lessonId") Long lessonId){
        try{
          return ResponseEntity.ok(userProgressService.findAllByUserIdAndLessonId(userId,lessonId));
    }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }

        }

    @GetMapping("/lesson-status")
    @Operation(summary = "Determine if a lesson is completed by user")
    public ResponseEntity<?>isLessonComplete(@RequestParam(name = "userId") Long userId,
                                            @RequestParam(name = "lessonId") Long lessonId){
        try{
            return ResponseEntity.ok(userProgressService.isLessonComplete(userId,lessonId));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }

    }

    @GetMapping("/recommendation")
    @Operation(summary = "Recommend a user for the next lesson based on performance")
    public ResponseEntity<?>recommendUserToNextLesson(@RequestParam(name = "userId") Long userId,
                                             @RequestParam(name = "lessonId") Long lessonId){
        try{
            return ResponseEntity.ok(userProgressService.recommendUserForNextLessonOrNot(userId,lessonId));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }

    }
}
