package com.techical_test_riservi.reservation.application.useCase.validators;

import com.techical_test_riservi.reservation.domain.Reservation;
import com.techical_test_riservi.reservation.domain.Schedule;
import com.techical_test_riservi.reservation.domain.exceptions.DateAvailable;
import com.techical_test_riservi.reservation.domain.exceptions.DateNotAvailable;
import com.techical_test_riservi.reservation.domain.ports.RetrieveBranches;
import com.techical_test_riservi.reservation.domain.ports.RetrieveReservations;
import com.techical_test_riservi.reservation.domain.ports.RetrieveSchedules;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

@Service
public class PeopleQuantityValidators {

    private RetrieveBranches retrieveBranches;
    private RetrieveReservations retrieveReservations;
    private RetrieveSchedules retrieveSchedules;

    @Autowired
    public PeopleQuantityValidators(RetrieveBranches retrieveBranches, RetrieveReservations retrieveReservations, RetrieveSchedules retrieveSchedules) {
        this.retrieveBranches = retrieveBranches;
        this.retrieveReservations = retrieveReservations;
        this.retrieveSchedules = retrieveSchedules;
    }

    public void execute(UUID branchId, DayOfWeek date, LocalTime time, int peopleQuantity) {
        int availableSeats = retrieveBranches
                .getAvailableSeatsInTheBranch(branchId);
        List<Reservation> reservationsAtSameTime = retrieveReservations.getReservationsBy(branchId, String.valueOf(date), time);

        int totalPeopleAlreadyAtSameTime = reservationsAtSameTime.stream()
                .mapToInt(Reservation::getNumberPeople)
                .sum();

        int remainingSeats = availableSeats - totalPeopleAlreadyAtSameTime;

        if (peopleQuantity > remainingSeats) {
            Schedule hourBranch = retrieveSchedules.getRangeHour(branchId, date);
            LocalTime timeRequiredByTheClient = time;

            while (timeRequiredByTheClient.isBefore(hourBranch.getEndTime())) {
                if (isTimeInRange(timeRequiredByTheClient, hourBranch.getStartTime(), hourBranch.getEndTime())) {
                    List<Reservation> nextReservations = retrieveReservations
                            .getReservationsBy(branchId, String.valueOf(date), timeRequiredByTheClient);;
                    int totalPeopleForNextHour = nextReservations.stream()
                            .mapToInt(Reservation::getNumberPeople)
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
