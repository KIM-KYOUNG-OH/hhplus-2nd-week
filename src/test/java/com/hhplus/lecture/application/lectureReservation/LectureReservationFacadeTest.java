package com.hhplus.lecture.application.lectureReservation;

import com.hhplus.lecture.application.lectureReservation.exception.LectureAlreadyReservedException;
import com.hhplus.lecture.application.lectureReservation.exception.MaximumCapacityExceededException;
import com.hhplus.lecture.domain.lecture.entity.Lecture;
import com.hhplus.lecture.domain.lecture.service.LectureService;
import com.hhplus.lecture.domain.lectureReservation.service.LectureReservationService;
import com.hhplus.lecture.domain.member.entity.Member;
import com.hhplus.lecture.domain.member.service.MemberService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LectureReservationFacadeTest {

    @Mock
    private LectureService lectureService;

    @Mock
    private MemberService memberService;

    @Mock
    private LectureReservationService lectureReservationService;

    @InjectMocks
    private LectureReservationFacade lectureReservationFacade;

    @Test
    public void 특강신청_실패_이미신청한특강예외() {
        Long memberId = 1L;
        Long lectureId = 1L;

        when(lectureReservationService.isLectureAlreadyReservedBy(memberId, lectureId)).thenReturn(true);

        assertThrows(LectureAlreadyReservedException.class, () -> {
            lectureReservationFacade.reserveLecture(memberId, lectureId);
        });

        verify(lectureReservationService, times(1)).isLectureAlreadyReservedBy(memberId, lectureId);
    }

    @Test
    public void 특강신청_실패_정원초과예외() {
        Long memberId = 1L;
        Long lectureId = 1L;

        when(lectureReservationService.isLectureAlreadyReservedBy(memberId, lectureId)).thenReturn(false);
        when(lectureReservationService.getCurrentEnrollmentBy(lectureId)).thenReturn(30);

        assertThrows(MaximumCapacityExceededException.class, () -> {
            lectureReservationFacade.reserveLecture(memberId, lectureId);
        });

        verify(lectureReservationService, times(1)).isLectureAlreadyReservedBy(memberId, lectureId);
        verify(lectureReservationService, times(1)).getCurrentEnrollmentBy(lectureId);
    }

    @Test
    public void 특강신청_성공() {
        Long memberId = 1L;
        Long lectureId = 1L;

        when(lectureReservationService.isLectureAlreadyReservedBy(memberId, lectureId)).thenReturn(false);
        when(lectureReservationService.getCurrentEnrollmentBy(lectureId)).thenReturn(25);
        Member member = mock(Member.class);
        Lecture lecture = mock(Lecture.class);
        when(memberService.findBy(memberId)).thenReturn(member);
        when(lectureService.findBy(lectureId)).thenReturn(lecture);
        doNothing().when(lectureReservationService).reserveLecture(member, lecture);

        lectureReservationFacade.reserveLecture(memberId, lectureId);

        verify(lectureReservationService, times(1)).isLectureAlreadyReservedBy(memberId, lectureId);
        verify(lectureReservationService, times(1)).getCurrentEnrollmentBy(lectureId);
        verify(lectureReservationService, times(1)).reserveLecture(member, lecture);
    }
}