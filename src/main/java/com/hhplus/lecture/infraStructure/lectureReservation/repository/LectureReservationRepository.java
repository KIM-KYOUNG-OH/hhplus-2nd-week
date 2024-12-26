package com.hhplus.lecture.infraStructure.lectureReservation.repository;

import com.hhplus.lecture.domain.lectureReservation.entity.LectureReservation;
import com.hhplus.lecture.domain.lectureReservation.model.LectureReservationId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface LectureReservationRepository extends JpaRepository<LectureReservation, LectureReservationId> {
    @Query("select case when count (lr) > 0 then true else false end " +
            "from LectureReservation lr " +
            "where lr.member.memberId = :memberId " +
            "and lr.lecture.lectureId = :lectureId")
    boolean isLectureAlreadyReservedBy(Long memberId, Long lectureId);

    @Query("select count(lr) from LectureReservation lr where lr.lecture.lectureId = :lectureId")
    int getCurrentEnrollmentBy(Long lectureId);
}
