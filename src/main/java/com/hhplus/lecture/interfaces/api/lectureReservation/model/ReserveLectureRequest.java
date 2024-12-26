package com.hhplus.lecture.interfaces.api.lectureReservation.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class ReserveLectureRequest {

    private Long memberId;
    private Long lectureId;
}
