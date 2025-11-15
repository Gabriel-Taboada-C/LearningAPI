package com.gabriel.practice.Exception;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/* Dto estándar de error  */
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@Builder
public class ErrorResponse {
    
    private int status;
    private String error;
    private String message;
    private String path;
    private LocalDateTime timestamp;

}
