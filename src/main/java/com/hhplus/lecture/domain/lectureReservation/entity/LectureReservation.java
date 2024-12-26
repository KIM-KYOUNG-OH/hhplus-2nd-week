package com.hhplus.lecture.domain.lectureReservation.entity;

import com.hhplus.lecture.domain.lecture.entity.Lecture;
import com.hhplus.lecture.domain.lectureReservation.model.LectureReservationId;
import com.hhplus.lecture.domain.member.entity.Member;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder(access = AccessLevel.PROTECTED)
public class LectureReservation {

    @EmbeddedId
    private LectureReservationId id;

    @MapsId("lectureId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lecture_id", nullable = false, foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Lecture lecture;

    @MapsId("memberId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false, foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Member member;

    @CreationTimestamp
    @Column(nullable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDateTime updatedAt;

    public static LectureReservation of(Member member, Lecture lecture) {
        return LectureReservation.builder()
                .lecture(lecture)
                .member(member)
                .build();
    }
}
