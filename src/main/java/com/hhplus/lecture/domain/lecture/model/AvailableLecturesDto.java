package com.hhplus.lecture.domain.lecture.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class AvailableLecturesDto {

    private Long lectureId;
    private String title;
    private LocalDate lectureDate;
    private String lecturerName;
    private Long currentEnrollment;

    public AvailableLecturesDto(Long lectureId, String title, LocalDate lectureDate, String lecturerName, Long currentEnrollment) {
        this.lectureId = lectureId;
        this.title = title;
        this.lectureDate = lectureDate;
        this.lecturerName = lecturerName;
        this.currentEnrollment = currentEnrollment;
    }
}
