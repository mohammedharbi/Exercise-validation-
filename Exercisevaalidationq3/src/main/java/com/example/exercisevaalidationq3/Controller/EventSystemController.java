package com.example.exercisevaalidationq3.Controller;

import com.example.exercisevaalidationq3.Model.EventSystem;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/event-system")
public class EventSystemController {

    ArrayList<EventSystem> events = new ArrayList<>();
    //• Create a new event (ID , description , capacity, startDate , endDate)
    @PostMapping("/add")
    public ResponseEntity addEvent(@RequestBody @Valid EventSystem event, Errors errors) {
        if (errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        events.add(event);
        return ResponseEntity.ok().body("add successful");
    }
    //• Display all event .
    @GetMapping("/get")
    public ResponseEntity getEvents() {
        return ResponseEntity.ok().body(events);
    }
    //• Update a event
    @PutMapping("/update/{index}")
    public ResponseEntity updateEvent(@PathVariable int index, @RequestBody @Valid EventSystem event,Errors errors) {
        if (errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        events.set(index, event);
        return ResponseEntity.ok().body("update successful");
    }
    //• Delete a event
    @DeleteMapping("/Delete/{index}")
    public ResponseEntity deleteEvent(@PathVariable int index) {
        events.remove(index);
        return ResponseEntity.ok().body("delete successful");
    }
    //• Change capacity
    @PutMapping("/update-capacity/{index}/{capacity}")
    public ResponseEntity updateEventCapacity(@PathVariable int index, @PathVariable @Valid @Min(value = 25, message = "capacity has to be higher than 24") int capacity) {

        events.get(index).setCapacity(capacity);
        return ResponseEntity.ok().body("capacity updated successful");
    }
    //• Search for a event by given id
    @GetMapping("/get-event-id/{ID}")
    public ResponseEntity getEventById(@PathVariable @Valid @Min(value = 2, message = "capacity has to be higher than 24")int ID) {

        ArrayList<EventSystem> tempevent = new ArrayList<>();

        for (EventSystem event : events) {
            if (event.getID() == ID) {tempevent.add(event);}
        }
        return ResponseEntity.ok().body(tempevent);
    }
}
