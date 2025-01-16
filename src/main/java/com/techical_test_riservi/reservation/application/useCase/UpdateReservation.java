package com.techical_test_riservi.reservation.application.useCase;

import com.techical_test_riservi.reservation.application.useCase.validators.*;
import com.techical_test_riservi.reservation.domain.Reservation;
import com.techical_test_riservi.reservation.domain.exceptions.ReservationNotFound;
import com.techical_test_riservi.reservation.domain.ports.RetrieveReservations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class UpdateReservation {

    private RetrieveReservations retrieveReservations;
    private BranchValidators branchValidators;
    private DayAvailableValidators dayAvailableValidators;
    private HourAvailableValidators hourAvailableValidators;
    private PeopleQuantityValidators peopleQuantityValidators;
    private RestaurantValidations restaurantValidations;

    @Autowired
    public UpdateReservation(RetrieveReservations retrieveReservations, BranchValidators branchValidators, DayAvailableValidators dayAvailableValidators, HourAvailableValidators hourAvailableValidators, PeopleQuantityValidators peopleQuantityValidators, RestaurantValidations restaurantValidations) {
        this.retrieveReservations = retrieveReservations;
        this.branchValidators = branchValidators;
        this.dayAvailableValidators = dayAvailableValidators;
        this.hourAvailableValidators = hourAvailableValidators;
        this.peopleQuantityValidators = peopleQuantityValidators;
        this.restaurantValidations = restaurantValidations;
    }

    public void execute(UUID reservationId, Reservation reservation) {
        UUID restaurantId = reservation.getRestaurant().getId();
        UUID branchId = reservation.getRestaurant().getBranches().get(0).getId();
        DayOfWeek reservationDate = reservation.getDate();
        LocalTime reservationTime = reservation.getTime();
        int peopleQuantity = reservation.getNumberPeople();

        if(retrieveReservations.getReservationById(reservationId).isEmpty()){
            throw new ReservationNotFound("Reservation id not found: " + reservationId);
        }

        branchValidators.execute(branchId);
        restaurantValidations.execute(restaurantId);

        dayAvailableValidators.execute(branchId, reservationDate);
        hourAvailableValidators.execute(branchId, reservationDate, reservationTime);

        peopleQuantityValidators.execute(branchId, reservationDate, reservationTime, peopleQuantity);

        Optional<Reservation> getExistingReservation = retrieveReservations.getReservationById(reservationId);

        retrieveReservations.updateReservationById(reservationId, reservation, getExistingReservation);
    }
}
