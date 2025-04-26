package com.example.demo1.Handlers;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseWrapper<T> {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm dd-MM-yy")
    private LocalDateTime time;
    private int status;
    private String message;
    private T data;
    private boolean isError;
}
