package com.hhplus.lecture.interfaces.api.lectureReservation.model;

import com.hhplus.lecture.domain.lecture.model.AvailableLecturesDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class AvailableLecturesResponse {

    private List<AvailableLecturesDto> content;

    private long totalElements;

    private int totalPages;

    private int currentPage;

    private int pageSize;
}
