package com.techical_test_riservi.reservation.domain.ports;

import com.techical_test_riservi.reservation.domain.Reservation;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RetrieveReservations {

    Optional<Reservation> getReservationById(UUID reservationId);
    List<Reservation> getReservationsByDay(DayOfWeek day, UUID branchId);
    void deleteReservationById(String reservationId);
    Reservation updateReservationById(UUID reservationId, Reservation reservation, Optional<Reservation> existingReservation);
    List<Reservation> getReservationsBy(UUID branchId, String dayTaken, LocalTime hourTaken);
    Reservation createReservation(Reservation reservation);
}

 