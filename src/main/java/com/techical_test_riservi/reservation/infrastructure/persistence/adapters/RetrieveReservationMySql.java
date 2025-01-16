package com.techical_test_riservi.reservation.infrastructure.persistence.adapters;

import com.techical_test_riservi.reservation.domain.Reservation;
import com.techical_test_riservi.reservation.domain.ports.RetrieveReservations;
import com.techical_test_riservi.reservation.infrastructure.persistence.entities.ReservationEntity;
import com.techical_test_riservi.reservation.infrastructure.persistence.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class RetrieveReservationMySql implements RetrieveReservations {

    private final ReservationRepository reservationRepository;
    @Autowired
    public RetrieveReservationMySql(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    @Override
    public Optional<Reservation> getReservationById(UUID id) {
        return reservationRepository.findById(id.toString()).map(ReservationEntity::toEntityUpdate);
    }

    @Override
    public List<Reservation> getReservationsByDay(DayOfWeek day, UUID branchId) {
        var result = reservationRepository.findByDateTakenAndBranchId(String.valueOf(day), String.valueOf(branchId));
        return result.stream().map(ReservationEntity::toEntity).collect(Collectors.toList());
    }

    @Override
    public void deleteReservationById(String reservationId) {
        reservationRepository.deleteById(reservationId);
    }

    @Override
    public Reservation updateReservationById(UUID reservationId, Reservation reservation, Optional<Reservation> existingReservation) {
        ReservationEntity updatingReservation = ReservationEntity.from(existingReservation.get());
        updatingReservation.setBranchId(String.valueOf(reservation.getRestaurant().getBranches().get(0).getId()));
        updatingReservation.setRestaurantId(String.valueOf(reservation.getRestaurant().getId()));
        updatingReservation.setDateTaken(String.valueOf(reservation.getDate()));
        updatingReservation.setHourTaken(reservation.getTime());
        updatingReservation.setNumberPeople(reservation.getNumberPeople());

        return reservationRepository.save(updatingReservation).toEntity();
    }

    @Override
    public List<Reservation> getReservationsBy(UUID branchId, String date, LocalTime time) {
        var result = reservationRepository.findByBranchIdAndDateTakenAndHourTaken(branchId.toString(), date, time);
        return result.stream()
                .map(ReservationEntity::toEntity)
                .collect(Collectors
                        .toList());
    }


    @Override
    public Reservation createReservation(Reservation reservation) {
        reservation.setId(UUID.randomUUID());
        var record = ReservationEntity.from(reservation);
        return reservationRepository.save(record).toEntity();
    }

}
