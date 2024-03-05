package com.example.eventMangement.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.ArrayList;

@Data
public class EventDto {
    @NotNull
    @NotEmpty
    private String title;

    @NotEmpty @NotNull
    private String description;

    @NotNull
    private String date;

    @NotNull @NotEmpty
    private String location;

    @NotNull @NotEmpty
    private ArrayList<String> attendies;
}
