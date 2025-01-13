package com.techical_test_riservi.reservation.domain;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.UUID;

public class Schedule {
    private UUID id;
    private DayOfWeek day;
    private LocalTime startTime;
    private LocalTime endTime;

    public Boolean isDateValid(LocalTime reservationTime) {
        //TODO: Validar si la fecha esta en el rango
        return null;
    }
}
