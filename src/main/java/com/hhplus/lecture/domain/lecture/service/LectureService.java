package com.hhplus.lecture.domain.lecture.service;

import com.hhplus.lecture.domain.lecture.model.AvailableLecturesDto;
import com.hhplus.lecture.domain.lecture.entity.Lecture;
import com.hhplus.lecture.domain.lecture.model.ReservedLectureDto;
import com.hhplus.lecture.infraStructure.lecture.repository.LectureRepository;
import com.hhplus.lecture.interfaces.common.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LectureService {

    private final LectureRepository lectureRepository;

    @Transactional(readOnly = true)
    public Lecture findBy(Long lectureId) {
        return lectureRepository.findByLectureId(lectureId).orElseThrow(() -> new NotFoundException(String.format("%s(lectureId) is not found", lectureId)));
    }

    @Transactional(readOnly = true)
    public Page<AvailableLecturesDto> getAvailableLecturesBy(Pageable pageRequest, LocalDate lectureDate) {
        return lectureRepository.getAvailableLecturesBy(pageRequest, lectureDate);
    }

    @Transactional(readOnly = true)
    public List<ReservedLectureDto> getReservedLectures(Long memberId) {
        return lectureRepository.getReservedLectures(memberId);
    }
}
