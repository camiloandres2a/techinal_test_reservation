package com.techical_test_riservi.reservation.application.useCase;

import com.techical_test_riservi.reservation.domain.exceptions.CellPhoneNotValidException;
import com.techical_test_riservi.reservation.domain.Client;
import com.techical_test_riservi.reservation.domain.Reservation;
import com.techical_test_riservi.reservation.domain.exceptions.ClientNotFound;
import com.techical_test_riservi.reservation.domain.exceptions.ReservationNotFound;
import com.techical_test_riservi.reservation.domain.ports.RetrieveClients;
import com.techical_test_riservi.reservation.domain.ports.RetrieveReservations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetReservationsByCellPhoneNumberClient {

    private final RetrieveReservations retrieveReservations;
    private final RetrieveClients retrieveClients;

    @Autowired
    public GetReservationsByCellPhoneNumberClient(RetrieveReservations retrieveReservations, RetrieveClients retrieveClients) {
        this.retrieveReservations = retrieveReservations;
        this.retrieveClients = retrieveClients;
    }

    public List<Reservation> execute(String cellPhoneNumber) {
        if(!Client.isValidCellPhoneNumber(cellPhoneNumber)){
            throw new CellPhoneNotValidException("The cell phone number is invalid");
        }
        if(!retrieveClients.isClientExistByCellPhone(cellPhoneNumber)){
            throw new ClientNotFound("Client not found");
        }
        var reservationsList = retrieveReservations.getReservationsByCellPhoneNumber(cellPhoneNumber);
        if(reservationsList.isEmpty()){
            throw new ReservationNotFound("There are no reservations associated with the cell phone number: " + cellPhoneNumber);
        }
        return reservationsList;
    }
}
