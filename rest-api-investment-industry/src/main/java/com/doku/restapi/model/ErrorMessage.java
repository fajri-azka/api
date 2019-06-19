package com.doku.restapi.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@AllArgsConstructor
@Getter
public class ErrorMessage {
    private String message;
    private String[] errors;
}
