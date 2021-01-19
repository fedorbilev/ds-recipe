package com.bilev.common.exception;

import lombok.Builder;

@Builder
public class ExceptionDto {

    public ExceptionCode exceptionCode;

    public String message;
}
