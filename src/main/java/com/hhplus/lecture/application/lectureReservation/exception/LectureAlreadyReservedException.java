package com.hhplus.lecture.application.lectureReservation.exception;

public class LectureAlreadyReservedException extends RuntimeException {

    public LectureAlreadyReservedException(String message) {
        super(message);
    }
}
