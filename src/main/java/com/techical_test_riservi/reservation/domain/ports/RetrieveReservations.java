package com.techical_test_riservi.reservation.domain.ports;

import com.techical_test_riservi.reservation.domain.Reservation;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RetrieveReservations {

    Reservation getReservationById(UUID reservationId);
    List<Reservation> getReservationsByCellPhoneNumber(String clientCellPhoneNumber);
    List<Reservation> getReservationsByDay(LocalDate day, UUID restaurantId, UUID branchId);
    void deleteReservationById(UUID reservationId);
    Reservation updateReservationById(UUID reservationId, Reservation reservation);
    List<Reservation> getQuantityPeopleThereAreAtTheSameTime(UUID branchId, LocalDate dayTaken, LocalTime hourTaken);
    Reservation createReservation(Reservation reservation);
}

 