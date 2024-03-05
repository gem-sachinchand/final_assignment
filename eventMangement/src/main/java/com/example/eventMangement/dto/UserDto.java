package com.example.eventMangement.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class UserDto {
    @Id
    private String userName;

    @NotNull @NotEmpty
    private String password;

}