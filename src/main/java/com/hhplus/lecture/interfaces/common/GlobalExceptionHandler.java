package com.hhplus.lecture.interfaces.common;

import com.hhplus.lecture.application.lectureReservation.exception.LectureAlreadyReservedException;
import com.hhplus.lecture.application.lectureReservation.exception.MaximumCapacityExceededException;
import com.hhplus.lecture.interfaces.common.exception.InvalidParameterException;
import com.hhplus.lecture.interfaces.common.exception.NotFoundException;
import com.hhplus.lecture.interfaces.common.model.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InvalidParameterException.class)
    @ResponseBody
    public ResponseEntity<ErrorResponse> handleInvalidParameterException(InvalidParameterException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST.value())
                .body(new ErrorResponse(HttpStatus.BAD_REQUEST.value(), ex.getMessage()));
    }

    @ExceptionHandler(LectureAlreadyReservedException.class)
    @ResponseBody
    public ResponseEntity<ErrorResponse> handleLectureAlreadyReservedException(LectureAlreadyReservedException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST.value())
                .body(new ErrorResponse(HttpStatus.BAD_REQUEST.value(), ex.getMessage()));
    }

    @ExceptionHandler(MaximumCapacityExceededException.class)
    @ResponseBody
    public ResponseEntity<ErrorResponse> handleMaximumCapacityExceededException(MaximumCapacityExceededException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST.value())
                .body(new ErrorResponse(HttpStatus.BAD_REQUEST.value(), ex.getMessage()));
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseBody
    public ResponseEntity<ErrorResponse> handleNotFoundException(NotFoundException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST.value())
                .body(new ErrorResponse(HttpStatus.BAD_REQUEST.value(), ex.getMessage()));
    }
}
