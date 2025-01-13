package com.techical_test_riservi.reservation.application.useCase;

import com.techical_test_riservi.reservation.application.useCase.validators.Reservations;
import com.techical_test_riservi.reservation.domain.Client;
import com.techical_test_riservi.reservation.domain.Reservation;
import com.techical_test_riservi.reservation.domain.exceptions.*;
import com.techical_test_riservi.reservation.domain.ports.*;
import com.techical_test_riservi.reservation.infrastructure.api.ReservationRequestDto;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

@Service
public class CreateReservation {

    private RetrieveReservations retrieveReservations;
    private Reservations reservationsValidator;

    public Reservation execute(Reservation reservation) {

        String clientName = reservation.getClient().getName();
        String clientCellPhone = reservation.getClient().getCellPhoneNumber();
        UUID restaurantId = reservation.getRestaurant().getId();
        UUID branchId = reservation.getRestaurant().getBranches().get(0).getId();
        LocalDate reservationDate = reservation.getDate();
        LocalTime reservationTime = reservation.getTime();
        int peopleQuantity = reservation.getQuantityPeople();

        reservationsValidator.validateClient(clientName, clientCellPhone);
        reservationsValidator.validateRestaurant(restaurantId);
        reservationsValidator.validateBranch(branchId);
        reservationsValidator.validateDayAvailability(branchId, reservationDate);
        reservationsValidator.validateHourAvailability(branchId, reservationDate, reservationTime);
        reservationsValidator.validatePeopleQuantity(branchId, reservationDate, reservationTime, peopleQuantity);

        return retrieveReservations.createReservation(reservation);
    }

}

