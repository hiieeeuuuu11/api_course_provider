package com.example.api_course_producer.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
public enum ErrorCode {
    UNCATEGORIZED_EXCEPTION(9999, "Uncategorized error", HttpStatus.INTERNAL_SERVER_ERROR),
    UNAUTHENTICATED(1001, "Unauthenticated", HttpStatus.UNAUTHORIZED),
    UNAUTHORIZED(1002, "You do not have permission", HttpStatus.FORBIDDEN),
    BAD_CREDENTIAL(1003, "Bad credential", HttpStatus.UNAUTHORIZED),
    URL_NOT_FOUND(1004, "No handler found for {httpMethod} {url}", HttpStatus.NOT_FOUND),
    METHOD_NOT_SUPPORTED(1005, "Method {httpMethod} is not support for this request", HttpStatus.METHOD_NOT_ALLOWED),
    PARAMETER_MISSING(1006, "Parameter {parameter} is missing", HttpStatus.BAD_REQUEST),
    PARAMETER_TYPE_MISMATCH(1007, "{parameter} should be of type {type}", HttpStatus.BAD_REQUEST),
    INVALID_KEY(1008, "Uncategorized error", HttpStatus.BAD_REQUEST),
    NOT_FOUND(1009, "Resource not found", HttpStatus.NOT_FOUND);

    ErrorCode(int code, String message, HttpStatusCode statusCode) {
        this.code = code;
        this.message = message;
        this.statusCode = statusCode;
    }

    private final int code;
    private final String message;
    private final HttpStatusCode statusCode;
}
