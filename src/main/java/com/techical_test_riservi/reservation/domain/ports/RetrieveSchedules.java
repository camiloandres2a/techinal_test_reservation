package com.techical_test_riservi.reservation.domain.ports;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

public interface RetrieveSchedules {
    Boolean isADayAvailable(UUID idBranch, LocalDate date);
    Boolean isHourAvailable(UUID idRestaurant, UUID idBranch, LocalTime hour);
    LocalTime getStartHour(UUID idBranch, LocalDate day);
    LocalTime getEndHour(UUID idBranch, LocalDate day);
}
