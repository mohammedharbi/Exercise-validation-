package com.example.exercisevaalidationq2.Controller;

import com.example.exercisevaalidationq2.ApiResponse.ApiResponse;
import com.example.exercisevaalidationq2.Model.ProjectTracker;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/project-tracker")
public class ProjectTrackerController {

    ArrayList<ProjectTracker> projectTrackers = new ArrayList<>();

    @PostMapping("/add")
    public ResponseEntity addProject(@RequestBody @Valid ProjectTracker projectTracker, Errors errors) {
        if (errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        projectTrackers.add(projectTracker);
        return ResponseEntity.status(200).body("Project successfully added");
    }

    //• Create a new project (ID,title , description , status, companyName)
    @GetMapping("/get")
    public ResponseEntity getProject() {
        return ResponseEntity.ok().body(projectTrackers);
    }
    //• Display all project .
    @PutMapping("/update/{index}")
    public ResponseEntity updateProject(@PathVariable int index, @RequestBody @Valid ProjectTracker projectTracker, Errors errors) {
        if (errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        projectTrackers.set(index, projectTracker);
        return ResponseEntity.ok().body("Successfully updated project tracker");
    }
    //• Update a project
    @DeleteMapping("/delete/{index}")
    public ResponseEntity deleteProject(@PathVariable int index) {
        projectTrackers.remove(index);
        return ResponseEntity.ok().body("Successfully deleted project tracker");
    }
    //• Delete a project
    @PutMapping("/update-status/{index}")
    public ResponseEntity updateProjectStatus(@PathVariable int index) {
        for (ProjectTracker projectTracker : projectTrackers) {
            if (projectTracker.getStatus().equals("Not Started")){projectTracker.setStatus("in Progress");
            }else if (projectTracker.getStatus().equals("in Progress")){projectTracker.setStatus("Completed");}

        }
        return ResponseEntity.ok().body("Successfully updated status project tracker");
    }
    //• Change the project status as done or not done
    @GetMapping("/search/{title}")
    public ArrayList searchProject(@PathVariable String title) {
        ArrayList<ProjectTracker> filteredProjectTrackers = new ArrayList<>();
        for (ProjectTracker projectTracker : projectTrackers) {
            if (projectTracker.getTitle().toLowerCase().contains(title.toLowerCase())) {filteredProjectTrackers.add(projectTracker);}
        }
        return filteredProjectTrackers;
    }
    //• Search for a project by given title
    @GetMapping("/get-allprojects/{companyName}")
    public ArrayList getAllProjects(@PathVariable String companyName) {
        ArrayList<ProjectTracker> filteredProjectTrackers = new ArrayList<>();
        for (ProjectTracker projectTracker : projectTrackers) {
            if (projectTracker.getCompanyName().toLowerCase().contains(companyName.toLowerCase())) {filteredProjectTrackers.add(projectTracker);}
        }
        return filteredProjectTrackers;
    }
    //• Display All project for one company by companyName.
}
