package com.hhplus.lecture.domain.lecture.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class ReservedLectureDto {

    private Long lectureId;
    private String title;
    private LocalDate lectureDate;
    private String lecturerName;

    public ReservedLectureDto(Long lectureId, String title, LocalDate lectureDate, String lecturerName) {
        this.lectureId = lectureId;
        this.title = title;
        this.lectureDate = lectureDate;
        this.lecturerName = lecturerName;
    }
}
