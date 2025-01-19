package com.techical_test_riservi.reservation.application.useCase;

import com.techical_test_riservi.reservation.application.useCase.validators.BranchValidators;
import com.techical_test_riservi.reservation.application.useCase.validators.DayAvailableValidators;
import com.techical_test_riservi.reservation.domain.Reservation;
import com.techical_test_riservi.reservation.domain.ports.RetrieveReservations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.util.List;
import java.util.UUID;

@Service
public class GetReservationByDay {

    private final RetrieveReservations retrieveReservations;
    private BranchValidators branchValidators;
    private DayAvailableValidators dayAvailableValidators;

    @Autowired
    public GetReservationByDay(RetrieveReservations retrieveReservations, BranchValidators branchValidators, DayAvailableValidators dayAvailableValidators) {
        this.retrieveReservations = retrieveReservations;
        this.branchValidators = branchValidators;
        this.dayAvailableValidators = dayAvailableValidators;
    }

    public List<Reservation> execute(DayOfWeek day, String branchId) {
        branchValidators.execute(UUID.fromString(branchId));
        dayAvailableValidators.execute(UUID.fromString(branchId), day);

        return retrieveReservations.getReservationsByDay(day, UUID.fromString(branchId));
    }
}
