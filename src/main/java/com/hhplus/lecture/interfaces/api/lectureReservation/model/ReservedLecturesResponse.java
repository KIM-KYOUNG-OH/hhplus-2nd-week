package com.hhplus.lecture.interfaces.api.lectureReservation.model;

import com.hhplus.lecture.domain.lecture.model.ReservedLectureDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReservedLecturesResponse {

    private List<ReservedLectureDto> reservedLectures;
}
