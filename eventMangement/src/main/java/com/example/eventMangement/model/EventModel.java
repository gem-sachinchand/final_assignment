package com.example.eventMangement.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Date;

@Data
@Document
public class EventModel {

    @Id
    private String id;


    private String title;


    private String description;


    private Date date;


    private String location;

    private ArrayList<String> attendies;
}
