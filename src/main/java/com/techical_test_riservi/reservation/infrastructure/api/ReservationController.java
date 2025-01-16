package com.techical_test_riservi.reservation.infrastructure.api;

import com.techical_test_riservi.reservation.application.useCase.*;
import com.techical_test_riservi.reservation.domain.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(path = "reservations/")
public class ReservationController {

    private final GetReservationByDay getReservationByDay;
    private final GetReservationById getReservationById;
    private final CreateReservation createReservation;
    private final UpdateReservation updateReservation;
    private final DeleteReservationById deleteReservationById;

    @Autowired
    public ReservationController(GetReservationByDay getReservationByDay, GetReservationById getReservationById, CreateReservation createReservation, UpdateReservation updateReservation, DeleteReservationById deleteReservationById) {
        this.getReservationByDay = getReservationByDay;
        this.getReservationById = getReservationById;
        this.createReservation = createReservation;
        this.updateReservation = updateReservation;
        this.deleteReservationById = deleteReservationById;
    }

    @PostMapping
    public ResponseEntity<Reservation> createReservation(@RequestBody ReservationRequestDto dto) throws InterruptedException {
        return ResponseEntity.ok(createReservation.execute(dto.toDomain()));
    }

    @GetMapping("/{day_reservation}/branches/{branch_id}")
    public ResponseEntity<List<Reservation>> getReservationsByDay(
            @PathVariable("day_reservation") DayOfWeek day,
            @PathVariable("branch_id") String branchId
    ) {
        return ResponseEntity.ok(getReservationByDay.execute(day, branchId));
    }

    @GetMapping("/{id_reservation}")
    public ResponseEntity<Optional<Reservation>> getReservationsById(
            @PathVariable("id_reservation") UUID id
    ) {
        return ResponseEntity.ok(getReservationById.execute(id));
    }

    @PutMapping("{id_reservation}")
    public ResponseEntity<Void> updateReservation(
            @PathVariable("id_reservation") UUID idReservation,
            @RequestBody ReservationUpdateRequestDto dto
    ) {
        updateReservation.execute(idReservation, dto.toDomain());
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("{id_reservation}")
    public ResponseEntity<Void> deleteReservation(
            @PathVariable("id_reservation") UUID idReservation
    ) {
        deleteReservationById.execute(idReservation);
        return ResponseEntity.noContent().build();
    }
}
