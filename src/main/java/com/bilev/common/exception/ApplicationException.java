package com.bilev.common.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ApplicationException extends RuntimeException {

    private ExceptionCode exceptionCode;

    private String message;

    public ApplicationException(final ExceptionCode exceptionCode, final String message) {
        this.exceptionCode = exceptionCode;
        this.message = message;
    }
}
