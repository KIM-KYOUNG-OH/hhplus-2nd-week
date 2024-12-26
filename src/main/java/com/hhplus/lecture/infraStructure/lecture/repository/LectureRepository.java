package com.hhplus.lecture.infraStructure.lecture.repository;

import com.hhplus.lecture.domain.lecture.model.AvailableLecturesDto;
import com.hhplus.lecture.domain.lecture.entity.Lecture;
import com.hhplus.lecture.domain.lecture.model.ReservedLectureDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface LectureRepository extends JpaRepository<Lecture, Long> {

    @Query(value = "select new com.hhplus.lecture.domain.lecture.model.AvailableLecturesDto(l.lectureId, l.title, l.lectureDate, l.lecturerName, count(lr)) " +
            "from Lecture l " +
            "left join LectureReservation lr on l.lectureId = lr.lecture.lectureId "+
            "where l.lectureDate = :lectureDate " +
            "group by l.lectureId " +
            "having count(lr) < 30")
    Page<AvailableLecturesDto> getAvailableLecturesBy(Pageable pageable, LocalDate lectureDate);

    Optional<Lecture> findByLectureId(Long lectureId);

    @Query(value = "select new com.hhplus.lecture.domain.lecture.model.ReservedLectureDto(l.lectureId, l.title, l.lectureDate, l.lecturerName) " +
            "from Lecture l " +
            "inner join fetch LectureReservation lr on l.lectureId = lr.lecture.lectureId " +
            "where lr.member.memberId = :memberId")
    List<ReservedLectureDto> getReservedLectures(Long memberId);
}
