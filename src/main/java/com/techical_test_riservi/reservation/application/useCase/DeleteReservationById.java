package com.techical_test_riservi.reservation.application.useCase;

import com.techical_test_riservi.reservation.domain.exceptions.ReservationNotFound;
import com.techical_test_riservi.reservation.domain.ports.RetrieveReservations;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DeleteReservationById {

    private RetrieveReservations retrieveReservations;

    public void execute(UUID idReservation) {
        if(retrieveReservations.getReservationById(idReservation) == null){
            throw new ReservationNotFound("Reservation id not found: " + idReservation);
        }
        retrieveReservations.deleteReservationById(idReservation);
    }
}
