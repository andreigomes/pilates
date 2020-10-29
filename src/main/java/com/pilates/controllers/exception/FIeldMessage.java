package com.pilates.controllers.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FIeldMessage {

    private String fieldName;
    private String message;
}
