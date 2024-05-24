package org.zeraki.task.learninglanguagemoduleapi.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.zeraki.task.learninglanguagemoduleapi.models.lesson.Lesson;
import org.zeraki.task.learninglanguagemoduleapi.service.LessonService;

@RestController
@RequestMapping("/lesson")
@RequiredArgsConstructor
@CrossOrigin
public class LessonController {
    private final LessonService lessonService;

    @PostMapping("/create")
    @Operation(summary = "create a new lesson")
    private ResponseEntity<?>createLesson(@RequestBody Lesson lesson){
        try{
           return ResponseEntity.ok(lessonService.createLesson(lesson));
        }catch (Exception e){
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }
    }
    @GetMapping("/getAll")
    @Operation(summary = "get all lessons")
    private ResponseEntity<?>getAllLessons(){
        try{
            return ResponseEntity.ok(lessonService.getAllLessons());
        }catch (Exception e){
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }
    }
}
