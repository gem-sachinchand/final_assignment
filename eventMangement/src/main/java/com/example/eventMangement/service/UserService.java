package com.example.eventMangement.service;

import com.example.eventMangement.dto.UserDto;
import com.example.eventMangement.model.UserModel;
import com.example.eventMangement.repository.UserRepository;
import com.example.eventMangement.utils.JwtUtils;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserService {

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    UserRepository userRepository;
    @Autowired
    ModelMapper modelMapper;
    public ResponseEntity<?> createUser(UserDto data){
        log.info("In create user service");
        UserModel userData = modelMapper.map(data, UserModel.class);
        return new  ResponseEntity<>(userRepository.save(userData),  new HttpHeaders(), HttpStatus.OK);
    }
    public ResponseEntity<String> generateToken(@Valid UserDto data){
        log.info("In generate auth token service");
        String name = data.getUserName();
        if (userRepository.existsByUserName(name)){
            return new ResponseEntity<>(jwtUtils.generateTokenFromUsername(name),new HttpHeaders(), HttpStatus.OK);
        }else {
            return new ResponseEntity<>("User Not Found",new HttpHeaders(), HttpStatus.NOT_FOUND);
        }
    }
}
