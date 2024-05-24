package org.zeraki.task.learninglanguagemoduleapi.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.zeraki.task.learninglanguagemoduleapi.models.exercise.Exercise;
import org.zeraki.task.learninglanguagemoduleapi.service.ExerciseService;

@RestController
@RequestMapping("/exercise")
@RequiredArgsConstructor
@CrossOrigin
public class ExerciseController {
    private final ExerciseService exerciseService;

    @GetMapping("/getAll")
    @Operation(summary = "Get all exercises that belong to a lesson")
    public ResponseEntity<?>getAllExercises(@RequestParam(name = "lessonId") Long lessonId){
        try{
            return ResponseEntity.ok(exerciseService.getAllExercisesByLessonId(lessonId));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }
    }

    @PostMapping("/create")
    @Operation(summary = "create an exercise")
    public ResponseEntity<?>createExercise(@RequestBody Exercise exercise){
        try{
            return ResponseEntity.ok(exerciseService.createExercise(exercise));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }
    }

    @PostMapping("/add-lesson")
    @Operation(summary = "Add exercise to a lesson by passing lessonId and exerciseId as parameters")
    public ResponseEntity<?>addExerciseToLesson(@RequestParam(name = "lessonId") Long lessonId,
                                                @RequestParam(name = "exerciseId") Long exerciseId){
        try{
            return ResponseEntity.ok(exerciseService.addExerciseToLesson(exerciseId,lessonId));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }
    }

}
