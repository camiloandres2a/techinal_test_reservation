package com.techical_test_riservi.reservation.application.useCase;

import com.techical_test_riservi.reservation.domain.Reservation;
import com.techical_test_riservi.reservation.domain.exceptions.ReservationNotFound;
import com.techical_test_riservi.reservation.domain.ports.RetrieveReservations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class GetReservationById {

    private final RetrieveReservations retrieveReservations;

    @Autowired
    public GetReservationById(RetrieveReservations retrieveReservations) {
        this.retrieveReservations = retrieveReservations;
    }

    public Optional<Reservation> execute(UUID id){
        var reservationById = retrieveReservations.getReservationById(id);
        if(reservationById.isEmpty()){
            throw new ReservationNotFound("Reservation not found");
        }
        return reservationById;
    }
}
