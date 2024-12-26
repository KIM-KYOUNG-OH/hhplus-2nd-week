package com.hhplus.lecture.interfaces.api.lectureReservation;

import com.hhplus.lecture.application.lectureReservation.LectureReservationFacade;
import com.hhplus.lecture.domain.lecture.model.AvailableLecturesDto;
import com.hhplus.lecture.domain.lecture.model.ReservedLectureDto;
import com.hhplus.lecture.interfaces.api.lectureReservation.model.AvailableLecturesResponse;
import com.hhplus.lecture.interfaces.api.lectureReservation.model.ReserveLectureRequest;
import com.hhplus.lecture.interfaces.api.lectureReservation.model.ReservedLecturesResponse;
import com.hhplus.lecture.interfaces.common.exception.InvalidParameterException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class LectureReservationController {

    private final LectureReservationFacade lectureReservationFacade;
    private final ModelMapper modelMapper;

    @GetMapping("/lectures/available")
    public ResponseEntity<AvailableLecturesResponse> getAvailableLectures(@RequestParam(value = "page", defaultValue = "0") int page,
                                                                          @RequestParam(value = "size", defaultValue = "10") int size,
                                                                          @RequestParam(value = "lectureDate") String lectureDateStr) {
        if (!lectureDateStr.matches("\\d{8}")) {
            throw new InvalidParameterException("lectureDate must be in YYYYMMDD format");
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        LocalDate lectureDate = LocalDate.parse(lectureDateStr, formatter);
        Page<AvailableLecturesDto> list = lectureReservationFacade.getAvailableLecturesBy(PageRequest.of(page, size), lectureDate);
        return ResponseEntity.ok(modelMapper.map(list, AvailableLecturesResponse.class));
    }

    @PostMapping("/lectures/reservation")
    public ResponseEntity<Void> reserveLecture(@RequestBody @Valid ReserveLectureRequest request) {
        lectureReservationFacade.reserveLecture(request.getMemberId(), request.getLectureId());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/lectures/{memberId}")
    public ResponseEntity<ReservedLecturesResponse> getReservedLectures(@PathVariable(value = "memberId") Long memberId) {
        List<ReservedLectureDto> reservedLectures = lectureReservationFacade.getReservedLectures(memberId);
        return ResponseEntity.ok(new ReservedLecturesResponse(reservedLectures));
    }
}
