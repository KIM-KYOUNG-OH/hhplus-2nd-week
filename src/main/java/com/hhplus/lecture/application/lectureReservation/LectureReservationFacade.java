package com.hhplus.lecture.application.lectureReservation;

import com.hhplus.lecture.application.lectureReservation.exception.LectureAlreadyReservedException;
import com.hhplus.lecture.application.lectureReservation.exception.MaximumCapacityExceededException;
import com.hhplus.lecture.domain.lecture.entity.Lecture;
import com.hhplus.lecture.domain.lecture.model.AvailableLecturesDto;
import com.hhplus.lecture.domain.lecture.model.ReservedLectureDto;
import com.hhplus.lecture.domain.lecture.service.LectureService;
import com.hhplus.lecture.domain.lectureReservation.service.LectureReservationService;
import com.hhplus.lecture.domain.member.entity.Member;
import com.hhplus.lecture.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
@RequiredArgsConstructor
public class LectureReservationFacade {

    private final LectureService lectureService;
    private final MemberService memberService;
    private final LectureReservationService lectureReservationService;
    private static final int MAX_CAPACITY_OF_LECTURE = 30;

    public Page<AvailableLecturesDto> getAvailableLecturesBy(Pageable pageRequest, LocalDate lectureDate) {
        return lectureService.getAvailableLecturesBy(pageRequest, lectureDate);
    }

    public void reserveLecture(Long memberId, Long lectureId) {
        boolean isReserved = lectureReservationService.isLectureAlreadyReservedBy(memberId, lectureId);
        if (isReserved) {
            throw new LectureAlreadyReservedException(String.format("Lecture is already reserved by %s(memberId)", memberId));
        }

        int currentEnrollment = lectureReservationService.getCurrentEnrollmentBy(lectureId);

        if (currentEnrollment >= MAX_CAPACITY_OF_LECTURE) {
            throw new MaximumCapacityExceededException(String.format("%s(lectureId) is fully booked", lectureId));
        }

        Member findMember = memberService.findBy(memberId);
        Lecture findLecture = lectureService.findBy(lectureId);
        lectureReservationService.reserveLecture(findMember, findLecture);
    }

    public List<ReservedLectureDto> getReservedLectures(Long memberId) {
        return lectureService.getReservedLectures(memberId);
    }
}
