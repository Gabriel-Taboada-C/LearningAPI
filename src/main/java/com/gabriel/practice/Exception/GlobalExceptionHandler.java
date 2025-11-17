package com.gabriel.practice.Exception;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {

    private final MessageService messageService;

    @ExceptionHandler (UserNotFoundException.class)
    public ResponseEntity <ErrorResponse> handleUserNotFound (
        UserNotFoundException ex, HttpServletRequest request) 
        {
        String message = messageService.getMessage(
            ex.getMessageKey(),
            ex.getArgs());

        ErrorResponse error = ErrorResponse.builder()
            .status(HttpStatus.NOT_FOUND.value())
            .error("Not Found")
            .message(message)
            .code(ex.getErrorCode())
            .path(request.getRequestURI())
            .timestamp(LocalDateTime.now())
            .build();
        
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    //Para Validaciones:
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidation(
        MethodArgumentNotValidException ex,
        HttpServletRequest request
    ) {
        String errors = ex.getBindingResult().getFieldErrors()
        .stream()
        .map(f -> f.getField() + ":" + f.getDefaultMessage())
        .collect(Collectors.joining(","));

        ErrorResponse error = ErrorResponse.builder()
            .status(HttpStatus.BAD_REQUEST.value())
            .error("Validation Error")
            .message(errors)
            .code(ErrorCode.VALIDATION_ERROR)
            .path(request.getRequestURI())
            .timestamp(LocalDateTime.now())
            .build();

        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity <ErrorResponse> handleGeneric(
        Exception ex,
        HttpServletRequest request) {
        
        String message = messageService.getMessage("server.error");

        ErrorResponse error = ErrorResponse.builder()
            .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
            .error("Internal Server Error")
            .message(message)
            .code(ErrorCode.INTERNAL_ERROR)
            .path(request.getRequestURI())
            .timestamp(LocalDateTime.now())
            .build();

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }

}

/* Faltaria Agregar el service en userController y userService (no existe)*/
