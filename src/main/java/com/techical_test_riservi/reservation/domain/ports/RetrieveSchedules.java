package com.techical_test_riservi.reservation.domain.ports;

import com.techical_test_riservi.reservation.domain.Schedule;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.UUID;

public interface RetrieveSchedules {
    Boolean isADayAvailable(UUID idBranch, DayOfWeek date);
    Boolean isHourAvailable(UUID idRestaurant, UUID idBranch, LocalTime hour);
    Schedule getRangeHour(UUID idBranch, DayOfWeek day);
    LocalTime getEndHour(UUID idBranch, DayOfWeek day);
}
