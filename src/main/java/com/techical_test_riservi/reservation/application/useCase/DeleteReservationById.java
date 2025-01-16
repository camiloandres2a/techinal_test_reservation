package com.techical_test_riservi.reservation.application.useCase;

import com.techical_test_riservi.reservation.domain.Reservation;
import com.techical_test_riservi.reservation.domain.exceptions.ReservationNotFound;
import com.techical_test_riservi.reservation.domain.ports.RetrieveReservations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class DeleteReservationById {

    private RetrieveReservations retrieveReservations;

    @Autowired
    public DeleteReservationById(RetrieveReservations retrieveReservations) {
        this.retrieveReservations = retrieveReservations;
    }

    public void execute(UUID idReservation) {

        Optional<Reservation> reservation = retrieveReservations.getReservationById(idReservation);
        if( reservation.isEmpty()){
            throw new ReservationNotFound("Reservation id not found: " + idReservation);
        }
        retrieveReservations.deleteReservationById(String.valueOf(idReservation));
    }
}



