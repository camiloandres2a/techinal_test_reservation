package com.techical_test_riservi.reservation.application.useCase;

import com.techical_test_riservi.reservation.application.useCase.validators.Reservations;
import com.techical_test_riservi.reservation.domain.Reservation;
import com.techical_test_riservi.reservation.domain.exceptions.ReservationNotFound;
import com.techical_test_riservi.reservation.domain.ports.RetrieveReservations;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Service
public class UpdateReservation {

    private RetrieveReservations retrieveReservations;
    private Reservations reservationsValidator;

    public void execute(UUID reservationId, Reservation reservation) {
        String clientName = reservation.getClient().getName();
        String clientCellPhone = reservation.getClient().getCellPhoneNumber();
        UUID restaurantId = reservation.getRestaurant().getId();
        UUID branchId = reservation.getRestaurant().getBranches().get(0).getId();
        LocalDate reservationDate = reservation.getDate();
        LocalTime reservationTime = reservation.getTime();
        int peopleQuantity = reservation.getQuantityPeople();

        if(retrieveReservations.getReservationById(reservationId) == null){
            throw new ReservationNotFound("Reservation id not found: " + reservationId);
        }

        reservationsValidator.validateClient(clientName, clientCellPhone);
        reservationsValidator.validateRestaurant(restaurantId);
        reservationsValidator.validateBranch(branchId);
        reservationsValidator.validateDayAvailability(branchId, reservationDate);
        reservationsValidator.validateHourAvailability(branchId, reservationDate, reservationTime);
        reservationsValidator.validatePeopleQuantity(branchId, reservationDate, reservationTime, peopleQuantity);

        retrieveReservations.updateReservationById(reservationId, reservation);
    }
}
