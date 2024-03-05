package com.example.eventMangement.controller;

import com.example.eventMangement.dto.EventDto;
import com.example.eventMangement.service.EventService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class EventController {

    @Autowired
    EventService eventsService;


    @GetMapping("/getEventsList")
    public ResponseEntity<Object> getEventsList(){
        log.info(" In get events controller");
        return eventsService.getAllEventsList();
    }

    @PostMapping("/createEvent")
    public ResponseEntity<Object> createNewEvent(@RequestBody @Valid EventDto data){
        log.info(" In Create by id controller");
        return eventsService.createNewEvent(data);
    }
    @PutMapping("/updateEvent")
    public ResponseEntity<Object> updateEvent(@RequestParam String id,@RequestBody @Valid EventDto data){
        log.info(" In Update by id controller");
        return eventsService.updateEvent(id,data);
    }

    @DeleteMapping("/deleteEvent")
    public ResponseEntity<Object> deleteEvent(@RequestParam String id){
        log.info(" In delete by id controller");
        return eventsService.deleteByDataId(id);
    }

    @GetMapping("/eventsByUserName")
    public ResponseEntity<Object> findbyUserNames(@RequestParam String userName){
        log.info("In find by username controller");
        log.info(userName);
        return eventsService.findbyUserNames(userName);
    }

    @PutMapping("/removeAttendee")
    public ResponseEntity<Object> removeAttendee(@RequestParam String id, @RequestParam String userName){
        log.info("In remove attendees controller");
        log.info(id, userName);
        return eventsService.removeAttendee(id, userName);
    }
    @PutMapping("/addAttendee")
    public ResponseEntity<Object> add(@RequestParam String id, @RequestParam String userName){
        log.info("In add attendee controller");
        log.info(id, userName);
        return eventsService.addAttendee(id, userName);
    }


}
