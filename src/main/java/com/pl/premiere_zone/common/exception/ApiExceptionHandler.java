package com.pl.premiere_zone.common.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.Instant;
import java.util.Map;

@Getter
@AllArgsConstructor
@Builder
public class ApiExceptionHandler {

    private Instant            timestamp;
    private int                status;
    private String             error;
    private String             message;
    private String             path;

    private Map<String, String> validationErrors;
}
