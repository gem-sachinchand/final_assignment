package com.example.eventMangement.service;

import com.example.eventMangement.dto.EventDto;
import com.example.eventMangement.model.EventModel;
import com.example.eventMangement.repository.EventRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

@Document
@Service
@Slf4j
public class EventService {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    EventRepository eventRepository;

    @Autowired
    private MongoOperations mongoOperations;

    public ResponseEntity<Object> getAllEventsList(){
        log.info("In get All Events service");
        return new ResponseEntity<>(eventRepository.findAll(),new HttpHeaders(), HttpStatus.OK);
    }

    public ResponseEntity<Object> createNewEvent(EventDto data){
        log.info("In create new event service");
        try {
            log.info("parsing date");
            // Parse the string to a Date object
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date utilDate = sdf.parse(data.getDate());
            // Convert the java.util.Date object to a java.sql.Date object
            Date date = new Date(utilDate.getTime());
            //checking whether event is already present
            log.info(date.toString());
            log.info(data.getLocation());
        if(!eventRepository.existsByLocationAndDate(data.getLocation(),date)) {
            log.info("event is not present");
            EventModel eventData = modelMapper.map(data, EventModel.class);
            return new ResponseEntity<>(eventRepository.save(eventData), new HttpHeaders(), HttpStatus.OK);
        }else {
            log.info("event is present");
            return new ResponseEntity<>("Event already present with same location and data",new HttpHeaders(), HttpStatus.BAD_REQUEST);
        }
        } catch (ParseException e) {
            System.out.println("Error parsing date: " + e.getMessage());
            return new ResponseEntity<>(e.getMessage(),new HttpHeaders(), HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<Object> updateEvent(String id, EventDto data){
        log.info("In update event service");
        log.info(id);
        if(eventRepository.findById(id)==null){
            log.info("event with id is not present");
            return new ResponseEntity<>("Id is not present",new HttpHeaders(), HttpStatus.BAD_REQUEST);
        }
        EventModel eventData = modelMapper.map(data, EventModel.class);
        eventData.setId(id);
        return new ResponseEntity<>(eventRepository.save(eventData), new HttpHeaders(), HttpStatus.OK);
    }

    public ResponseEntity<Object> deleteByDataId(String id){
        log.info("in deletebyId service");
        log.info(id);
        if(eventRepository.findById(id)==null){
            log.info("event with id is not present");
            return new ResponseEntity<>("Event with Id is not present",new HttpHeaders(), HttpStatus.BAD_REQUEST);
        }
        eventRepository.deleteById(id);
        return new ResponseEntity<>("Item deleted Successfully",new HttpHeaders(), HttpStatus.OK);
    }

    public ResponseEntity<Object> findbyUserNames(String userName){
        log.info("In find by username service");
        return new ResponseEntity<>(eventRepository.findByAttendies(userName), new HttpHeaders(), HttpStatus.OK);
    }

    public ResponseEntity<Object> removeAttendee(String id, String attendeeName) {
        log.info("In remove attendee service");
        log.info(id, attendeeName);
        Optional<EventModel> data = eventRepository.findById(id);
        if (data==null) {
            log.info("event with id is not present");
            return new ResponseEntity<>("Event with Id is not present",new HttpHeaders(), HttpStatus.BAD_REQUEST);
        }
        if(!data.get().getAttendies().contains(attendeeName)){
            return new ResponseEntity<>("No one is present with attendee name: " + attendeeName,new HttpHeaders(), HttpStatus.OK);
        }
        data.get().getAttendies().remove(attendeeName);
        EventModel eventData = modelMapper.map(data, EventModel.class);
        return new ResponseEntity<>(eventRepository.save(eventData),new HttpHeaders(), HttpStatus.OK);
    }

    public ResponseEntity<Object> addAttendee(String id, String attendeeName) {
        log.info("In add attendee service");
        log.info(id, attendeeName);
        Optional<EventModel> data = eventRepository.findById(id);
        if (data==null) {
            log.info("event with id is not present");
            return new ResponseEntity<>("Event with Id is not present",new HttpHeaders(), HttpStatus.BAD_REQUEST);
        }
        if(data.get().getAttendies().contains(attendeeName)){
            return new ResponseEntity<>("Someone is already present with attendee name: "+attendeeName,new HttpHeaders(), HttpStatus.OK);
        }
        data.get().getAttendies().add(attendeeName);
        EventModel eventData = modelMapper.map(data, EventModel.class);
        return new ResponseEntity<>(eventRepository.save(eventData),new HttpHeaders(), HttpStatus.OK);
    }
}
