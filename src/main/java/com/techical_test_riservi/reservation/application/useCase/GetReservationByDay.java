package com.techical_test_riservi.reservation.application.useCase;

import com.techical_test_riservi.reservation.application.useCase.validators.Reservations;
import com.techical_test_riservi.reservation.domain.Reservation;
import com.techical_test_riservi.reservation.domain.ports.RetrieveReservations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class GetReservationByDay {

    private final RetrieveReservations retrieveReservations;
    private Reservations reservationsValidator;

    @Autowired
    public GetReservationByDay(RetrieveReservations retrieveReservations) {
        this.retrieveReservations = retrieveReservations;
    }

    public List<Reservation> execute(LocalDate day, UUID restaurantId, UUID branchId) {
        reservationsValidator.validateRestaurant(restaurantId);
        reservationsValidator.validateBranch(branchId);
        reservationsValidator.validateDayAvailability(branchId, day);

        return retrieveReservations.getReservationsByDay(day, restaurantId, branchId);
    }
}
