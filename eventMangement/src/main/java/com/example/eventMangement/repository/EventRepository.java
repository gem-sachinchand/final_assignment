package com.example.eventMangement.repository;

import com.example.eventMangement.model.EventModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface EventRepository extends MongoRepository<EventModel, String> {
    Boolean existsByLocationAndDate(String title, Date date);

    List<EventModel> findByAttendies(String username);

}
