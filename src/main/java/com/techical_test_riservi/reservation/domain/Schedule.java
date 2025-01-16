package com.techical_test_riservi.reservation.domain;

import lombok.Builder;
import lombok.Getter;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.UUID;

@Builder
@Getter
public class Schedule {
    private UUID id;
    private DayOfWeek day;
    private LocalTime startTime;
    private LocalTime endTime;

}
