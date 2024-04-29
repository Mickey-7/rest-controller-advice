package com.example.validationspringboot.advice;

import com.example.validationspringboot.error.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@RestControllerAdvice
public class AdviceController {
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex){
//        Map<String, String> errors = new HashMap<>();
//        ex.getBindingResult().getAllErrors().forEach((error) -> {
//            String fieldName = ((FieldError) error).getField();
//            String errorMessage = error.getDefaultMessage();
//            errors.put(fieldName, errorMessage);
//        });
//        return errors;
//    }

//    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationExceptions(MethodArgumentNotValidException ex){
        List<ErrorResponse.ErrorDetails> errorDetails =  new ArrayList<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errorDetails.add(new ErrorResponse.ErrorDetails(errorMessage, ""));
        });
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setErrorDetails(errorDetails);
        errorResponse.setErrorResponseCode(HttpStatus.BAD_REQUEST.value());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }
    /* output on postman
    {
        "errorResponseCode": 400,
        "errorDetails": [
            {
                "message": "Name is mandatory",
                "errorCode": ""
            }
        ]
    }
    */

    @ExceptionHandler(MissingRequestHeaderException.class)
    public ResponseEntity<ErrorResponse> handleMissingRequestHeaderException(MissingRequestHeaderException ex){
        List<ErrorResponse.ErrorDetails> errorDetails =  new ArrayList<>();
        errorDetails.add(new ErrorResponse.ErrorDetails(ex.getMessage(), ""));
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setErrorDetails(errorDetails);
        errorResponse.setErrorResponseCode(HttpStatus.BAD_REQUEST.value());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }
    /* postman output
    {
    "errorResponseCode": 400,
        "errorDetails": [
            {
                "message": "Required request header 'header-id' for method parameter type String is not present",
                "errorCode": ""
            }
        ]
    }

    to pass through need to add
    header-id on Headers on postman
    Headers
        Key                 Value           Bulk Edit
        header-id           any-value
    */
}
