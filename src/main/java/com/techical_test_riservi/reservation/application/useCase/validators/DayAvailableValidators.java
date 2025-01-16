package com.techical_test_riservi.reservation.application.useCase.validators;

import com.techical_test_riservi.reservation.domain.exceptions.DateNotAvailable;
import com.techical_test_riservi.reservation.domain.ports.RetrieveSchedules;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.util.UUID;

@Service
public class DayAvailableValidators {

    private RetrieveSchedules retrieveSchedules;

    @Autowired
    public DayAvailableValidators(RetrieveSchedules retrieveSchedules) {
        this.retrieveSchedules = retrieveSchedules;
    }

    public void execute(UUID branchId, DayOfWeek date){
        if (!retrieveSchedules.isADayAvailable(branchId, date)) {
            throw new DateNotAvailable("Date is not available because the branch is closed .");
        }
    }
}
