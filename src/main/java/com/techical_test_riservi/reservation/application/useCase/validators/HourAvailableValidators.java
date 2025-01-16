package com.techical_test_riservi.reservation.application.useCase.validators;

import com.techical_test_riservi.reservation.domain.Schedule;
import com.techical_test_riservi.reservation.domain.exceptions.DateNotAvailable;
import com.techical_test_riservi.reservation.domain.ports.RetrieveSchedules;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.UUID;

@Service
public class HourAvailableValidators {

    private RetrieveSchedules retrieveSchedules;

    @Autowired
    public HourAvailableValidators(RetrieveSchedules retrieveSchedules) {
        this.retrieveSchedules = retrieveSchedules;
    }

    public void execute(UUID branchId, DayOfWeek date, LocalTime time) {
        Schedule hourBranch = retrieveSchedules.getRangeHour(branchId, date);

        if(!isTimeInRange(time, hourBranch.getStartTime(), hourBranch.getEndTime())) {
            throw new DateNotAvailable("Time is not available because the branch is closed .");
        }
    }

    private boolean isTimeInRange(LocalTime timeToCheck, LocalTime startTime, LocalTime endTime) {
        return !timeToCheck.isBefore(startTime) && !timeToCheck.isAfter(endTime);
    }
}
