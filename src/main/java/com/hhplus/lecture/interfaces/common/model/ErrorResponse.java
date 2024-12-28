package com.hhplus.lecture.interfaces.common.model;

public record ErrorResponse(
        int code,
        String message
) {
}
