package com.hhplus.lecture.domain.lecture.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder(access = AccessLevel.PROTECTED)
public class Lecture {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long lectureId;

    @Column(length = 100, nullable = false)
    private String title;

    @Column(nullable = false)
    private LocalDate lectureDate;

    @Column(length = 100, nullable = false)
    private String lecturerName;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    public static Lecture withId(Long lectureId) {
        return Lecture.builder()
                .lectureId(lectureId)
                .build();
    }
}
