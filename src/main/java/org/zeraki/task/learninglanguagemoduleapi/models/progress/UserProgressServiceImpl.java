package org.zeraki.task.learninglanguagemoduleapi.models.progress;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.zeraki.task.learninglanguagemoduleapi.models.exercise.Exercise;
import org.zeraki.task.learninglanguagemoduleapi.models.exercise.ExerciseScoreDTO;
import org.zeraki.task.learninglanguagemoduleapi.models.lesson.Lesson;
import org.zeraki.task.learninglanguagemoduleapi.models.lesson.LessonDTO;
import org.zeraki.task.learninglanguagemoduleapi.models.user.AppUser;
import org.zeraki.task.learninglanguagemoduleapi.repository.ExerciseRepository;
import org.zeraki.task.learninglanguagemoduleapi.repository.LessonRepository;
import org.zeraki.task.learninglanguagemoduleapi.repository.UserProgressRepository;
import org.zeraki.task.learninglanguagemoduleapi.repository.UserRepository;
import org.zeraki.task.learninglanguagemoduleapi.service.UserProgressService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserProgressServiceImpl implements UserProgressService {
    private final UserProgressRepository userProgressRepository;
    private final UserRepository userRepository;
    private final ExerciseRepository exerciseRepository;
    private final LessonRepository lessonRepository;

    @Override
    public String saveUserProgress(int userScore,Long userId,Long exerciseId,Long lessonId) {

        //Retrieve user by userId provided, if not found throw an exception
        AppUser user = userRepository.findById(userId)
                .orElseThrow(()->new RuntimeException("User with Id provided is not found"));
        //Retrieve exercise by exerciseId provided, if not found throw an exception
        Exercise exercise = exerciseRepository.findById(exerciseId)
                .orElseThrow(()->new RuntimeException("Exercise with id provided is not found"));
        //Retrieve Lesson by lessonId provided if not found, throw an exception
        Lesson lesson = lessonRepository.findById(lessonId)
                .orElseThrow(()->new RuntimeException("Lesson with Id provided not found"));

        //Builder to construct a user progress object
        UserProgress progress = UserProgress.builder()
                .appUser(user)
                .exercise(exercise)
                .userScore(userScore)
                .lesson(lesson)
                .build();
        //Persist the user progress object
         userProgressRepository.save(progress);
        return "saved";
    }

    @Override
    public UserProgressDTO findAllByUserIdAndLessonId(Long userId,Long lessonId) {
        //Retrieve app user by Id provided
        AppUser appUser = userRepository.findById(userId)
                .orElseThrow(()->new RuntimeException("User with id provided not found"));
        //Retrive a list of user progress objects containing, lessonId, exerciseId and user scores
        List<UserProgress> progressList = userProgressRepository.findAllByUserIdAndLessonId(userId,lessonId);
        //List to hold Lesson Objects
        List<LessonDTO>lessonDTOS = new ArrayList<>();
        LessonDTO lessonDTO = new LessonDTO();

        //List to hold exercise objects completed by a user
        List<ExerciseScoreDTO>exerciseScoreDTOS = new ArrayList<>();


        //Iterate through the progress lists
        for(UserProgress p: progressList){
            if(p.getLesson().getId().equals(lessonId)){
                lessonDTO.setId(lessonId);
                lessonDTO.setTitle(p.getLesson().getTitle());
                Exercise exercise = exerciseRepository.findById(p.getExercise().getId()).get();

                //Get Exercises done by user that belong to lessonId provided
                    ExerciseScoreDTO scoreDTO = ExerciseScoreDTO.builder()
                            .id(exercise.getId())
                            .maxScore(exercise.getScore())
                            .userScore(p.getUserScore())
                            .description(exercise.getDescription())
                            .build();
                    exerciseScoreDTOS.add(scoreDTO);


            }
        }
        lessonDTO.setExercises(exerciseScoreDTOS);

        lessonDTOS.add(lessonDTO);
        //Build a UserProgressDTO object
        UserProgressDTO userProgressDTO = UserProgressDTO.builder()
                .userId(appUser.getId())
                .username(appUser.getUsername())
                .lessons(lessonDTOS)
                .build();
        return userProgressDTO;
    }

    @Override
    public boolean isLessonComplete(Long userId, Long lessonId) {
        //Retrieve the user and lesson if they exists by userid and lessonId respectively
        Lesson lesson = lessonRepository.findById(lessonId)
                .orElseThrow(()->new RuntimeException("Lesson with id provided not found"));
        AppUser appUser = userRepository.findById(userId)
                .orElseThrow(()->new RuntimeException("User with id not found"));

        //Get Lesson exercises by lesson Id
        List<Exercise>lessonExercises = exerciseRepository.findAllByLesson_Id(lesson.getId());
        if(lessonExercises.isEmpty()){
            return false;
        }
        UserProgressDTO userProgressDTO = findAllByUserIdAndLessonId(appUser.getId(), lesson.getId());
        //Get completed exercises with there scores
        List<ExerciseScoreDTO>completedExercises = userProgressDTO.getLessons().get(0).getExercises();
        //If length of lesson exrcises is equal to lenght of exrcises attempted by the user it means the user finished all exrcises for that lesson
        return lessonExercises.size() == completedExercises.size() ? true : false;

    }

    @Override
    public String recommendUserForNextLessonOrNot(Long userId, Long lessonId) {
        // Check if the lesson tasks are complete, if not completed, do not recommend for next lesson
        boolean isCompleted = isLessonComplete(userId, lessonId);
        if(!isCompleted){
            return "User not recommended for next lesson";
        }

        // Get exercises for the lesson id provided
        List<Exercise>lessonExercises = exerciseRepository.findAllByLesson_Id(lessonId);
        // Get user scores from the completed exercises for the lesson id provided
        UserProgressDTO userProgressDTO = findAllByUserIdAndLessonId(userId, lessonId);
        List<ExerciseScoreDTO>completedExercises = userProgressDTO.getLessons().get(0).getExercises();


        // Calculate total expected score the lesson exercises and user scores from completed exercises
        int expectedScore = lessonExercises.stream().mapToInt(Exercise::getScore).sum();
        int userScore = completedExercises.stream().mapToInt(ExerciseScoreDTO::getUserScore).sum();

        if (expectedScore == 0) {
            return "Something went wrong! Try again later.";
        }
        //Calculate 60% for pass mark
        double recommendedScore = expectedScore * 0.6;

        //Make recommendation based on user score
        if (userScore < recommendedScore) {
            return "User failed this lesson, not recommended for the next lesson";
        } else {
            return "Excellent, user recommended for the next lesson";
        }
    }
}
