package com.techical_test_riservi.reservation.application.useCase.validators;

import com.techical_test_riservi.reservation.domain.Client;
import com.techical_test_riservi.reservation.domain.Reservation;
import com.techical_test_riservi.reservation.domain.exceptions.*;
import com.techical_test_riservi.reservation.domain.ports.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;
import java.util.List;

public class Reservations {
    private RetrieveClients retrieveClients;
    private RetrieveSchedules retrieveSchedules;
    private RetrieveBranches retrieveBranches;
    private RetrieveReservations retrieveReservations;
    private RetrieveRestaurant retrieveRestaurant;

    public void validateClient(String clientName, String clientCellPhone) {
        if (!Client.isValidCellPhoneNumber(clientCellPhone)) {
            throw new CellPhoneNotValidException("The cell phone number is invalid");
        }
        if (!retrieveClients.isClientExistByCellPhone(clientCellPhone)) {
            retrieveClients.createClient(clientName, clientCellPhone);
        }
    }

    public void validateRestaurant(UUID restaurantId) {
        if (retrieveRestaurant.getRestaurant(restaurantId).isEmpty()) {
            throw new RestaurantNotFound("Restaurant not found");
        }
    }

    public void validateBranch(UUID branchId) {
        if (retrieveBranches.getBranch(branchId).isEmpty()) {
            throw new BranchNotFound("Branch not found");
        }
    }

    public void validateDayAvailability(UUID branchId, LocalDate date) {
        if (!retrieveSchedules.isADayAvailable(branchId, date)) {
            throw new DateNotAvailable("Date is not available because the branch is closed .");
        }
    }

    public void validateHourAvailability(UUID branchId, LocalDate date, LocalTime time) {
        LocalTime startHourBranch = retrieveSchedules.getStartHour(branchId, date);
        LocalTime endHourBranch = retrieveSchedules.getEndHour(branchId, date);

        if(!isTimeInRange(time, startHourBranch, endHourBranch)) {
            throw new DateNotAvailable("Time is not available because the branch is closed .");
        }
    }

    public void validatePeopleQuantity(UUID branchId, LocalDate date, LocalTime time, int peopleQuantity) {
        int availableSeats = retrieveBranches.getAvailableSeatsInTheBranch(branchId);
        List<Reservation> reservationsAtSameTime = retrieveReservations.getQuantityPeopleThereAreAtTheSameTime(branchId, date, time);

        int totalPeopleAlreadyAtSameTime = reservationsAtSameTime.stream()
                .mapToInt(Reservation::getQuantityPeople)
                .sum();

        int remainingSeats = availableSeats - totalPeopleAlreadyAtSameTime;

        if (peopleQuantity > remainingSeats) {
            LocalTime startHourBranch = retrieveSchedules.getStartHour(branchId, date);
            LocalTime endHourBranch = retrieveSchedules.getEndHour(branchId, date);

            LocalTime timeRequiredByTheClient = time;

            while (timeRequiredByTheClient.isBefore(endHourBranch)) {
                if (isTimeInRange(timeRequiredByTheClient, startHourBranch, endHourBranch)) {
                    List<Reservation> nextReservations = retrieveReservations
                            .getQuantityPeopleThereAreAtTheSameTime(branchId, date, timeRequiredByTheClient);
                    int totalPeopleForNextHour = nextReservations.stream()
                            .mapToInt(Reservation::getQuantityPeople)
                            .sum();

                    int remainingSeatsForNextHour = availableSeats - totalPeopleForNextHour;

                    if (peopleQuantity <= remainingSeatsForNextHour) {
                        throw new DateAvailable("Number of people exceeds the number of people authorized for this time. The maximum should be for this time: "
                                + remainingSeatsForNextHour + " but we have free space for the number of people you require for: " + timeRequiredByTheClient);
                    }
                }

                timeRequiredByTheClient = timeRequiredByTheClient.plusHours(1);
            }

            throw new DateNotAvailable("We do not have the amount of people space you require for today");
        }
    }


    public boolean isTimeInRange(LocalTime timeToCheck, LocalTime startTime, LocalTime endTime) {
        return !timeToCheck.isBefore(startTime) && !timeToCheck.isAfter(endTime);
    }
}
