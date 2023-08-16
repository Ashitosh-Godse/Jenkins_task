package com.epam.advice;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ExceptionResponse {
String timeStamp;
String error;
String status;
String path;
}
