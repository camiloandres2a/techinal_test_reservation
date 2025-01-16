package com.techical_test_riservi.reservation.application.useCase;

import com.techical_test_riservi.reservation.application.useCase.validators.*;
import com.techical_test_riservi.reservation.domain.Reservation;
import com.techical_test_riservi.reservation.domain.ports.RetrieveReservations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.UUID;

@Service
public class CreateReservation {

    private RetrieveReservations retrieveReservations;

    private BranchValidators branchValidators;
    private ClientValidations clientValidations;
    private DayAvailableValidators dayAvailableValidators;
    private HourAvailableValidators hourAvailableValidators;
    private PeopleQuantityValidators peopleQuantityValidators;
    private RestaurantValidations restaurantValidations;
    private CreateClient createClient;

    @Autowired
    public CreateReservation(RetrieveReservations retrieveReservations, BranchValidators branchValidators, ClientValidations clientValidations, DayAvailableValidators dayAvailableValidators, HourAvailableValidators hourAvailableValidators, PeopleQuantityValidators peopleQuantityValidators, RestaurantValidations restaurantValidations, CreateClient createClient) {
        this.retrieveReservations = retrieveReservations;
        this.branchValidators = branchValidators;
        this.clientValidations = clientValidations;
        this.dayAvailableValidators = dayAvailableValidators;
        this.hourAvailableValidators = hourAvailableValidators;
        this.peopleQuantityValidators = peopleQuantityValidators;
        this.restaurantValidations = restaurantValidations;
        this.createClient = createClient;
    }

    public Reservation execute(Reservation reservation) throws InterruptedException {

        String clientName = reservation.getClient().getName();
        String clientCellPhone = reservation.getClient().getCellPhoneNumber();
        UUID restaurantId = reservation.getRestaurant().getId();
        UUID branchId = reservation.getRestaurant().getBranches().get(0).getId();
        DayOfWeek reservationDate = reservation.getDate();
        LocalTime reservationTime = reservation.getTime();
        int peopleQuantity = reservation.getNumberPeople();

        clientValidations.execute(clientCellPhone);
        createClient.execute(clientName, clientCellPhone, reservation);

        restaurantValidations.execute(restaurantId);
        branchValidators.execute(branchId);

        dayAvailableValidators.execute(branchId, reservationDate);
        hourAvailableValidators.execute(branchId, reservationDate, reservationTime);

        peopleQuantityValidators.execute(branchId, reservationDate, reservationTime, peopleQuantity);
        return retrieveReservations.createReservation(reservation);
    }
}

