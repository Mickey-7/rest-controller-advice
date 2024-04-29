package com.example.validationspringboot.error;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
public class ErrorResponse {
    private Integer errorResponseCode;
    private List<ErrorDetails> errorDetails;

    @Data
    @AllArgsConstructor
    public static class ErrorDetails {
        private String message;
        private String errorCode;


    }

}
