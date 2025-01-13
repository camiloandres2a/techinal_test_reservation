package com.techical_test_riservi.reservation.infrastructure.persistence.adapters;

import com.techical_test_riservi.reservation.domain.Reservation;
import com.techical_test_riservi.reservation.domain.ports.RetrieveReservations;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

@Component
public class RetrieveReservationMySql implements RetrieveReservations {
    @Override
    public Reservation getReservationById(UUID id) {
        return null;
    }

    @Override
    public List<Reservation> getReservationsByCellPhoneNumber(String cellPhoneNumber) {
        return null;
    }

    @Override
    public List<Reservation> getReservationsByDay(LocalDate day, UUID restaurantId, UUID branchId) {
        return List.of();
    }

    @Override
    public void deleteReservationById(UUID reservationId) {

    }

    @Override
    public Reservation updateReservationById(UUID reservationId, Reservation reservation) {
        return null;
    }

    @Override
    public List<Reservation> getQuantityPeopleThereAreAtTheSameTime(UUID branchId, LocalDate dayTaken, LocalTime hourTaken) {
        return List.of();
    }

    @Override
    public Reservation createReservation(Reservation reservation) {
        return null;
    }
}
