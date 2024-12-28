package com.hhplus.lecture.domain.lectureReservation.service;

import com.hhplus.lecture.domain.lecture.entity.Lecture;
import com.hhplus.lecture.domain.lectureReservation.entity.LectureReservation;
import com.hhplus.lecture.domain.member.entity.Member;
import com.hhplus.lecture.infraStructure.lectureReservation.repository.LectureReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LectureReservationService {

    private final LectureReservationRepository lectureReservationRepository;

    @Transactional(readOnly = true)
    public boolean isLectureAlreadyReservedBy(Long memberId, Long lectureId) {
        return lectureReservationRepository.isLectureAlreadyReservedBy(memberId, lectureId);
    }

    @Transactional(readOnly = true)
    public int getCurrentEnrollmentBy(Long lectureId) {
        return lectureReservationRepository.getCurrentEnrollmentBy(lectureId);
    }

    @Transactional
    public void reserveLecture(Member member, Lecture lecture) {
        LectureReservation info = LectureReservation.of(member, lecture);
        lectureReservationRepository.save(info);
    }
}
