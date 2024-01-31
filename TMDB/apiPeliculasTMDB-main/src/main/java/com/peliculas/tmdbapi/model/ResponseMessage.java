package com.peliculas.tmdbapi.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
@Setter
@NoArgsConstructor
public class ResponseMessage <T>{
    private T data;
    private String message;


    public ResponseMessage(T data, String message) {
        this.data = data;
        this.message = message;
    }


}