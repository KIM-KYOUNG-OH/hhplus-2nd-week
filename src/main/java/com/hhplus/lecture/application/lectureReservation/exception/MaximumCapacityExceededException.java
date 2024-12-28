package com.hhplus.lecture.application.lectureReservation.exception;

public class MaximumCapacityExceededException extends RuntimeException {

    public MaximumCapacityExceededException(String message) {
        super(message);
    }
}
