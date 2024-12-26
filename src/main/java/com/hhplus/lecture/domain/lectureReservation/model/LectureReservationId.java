package com.hhplus.lecture.domain.lectureReservation.model;

import java.io.Serializable;

public record LectureReservationId(
        Long lectureId,
        Long memberId
) implements Serializable {}
