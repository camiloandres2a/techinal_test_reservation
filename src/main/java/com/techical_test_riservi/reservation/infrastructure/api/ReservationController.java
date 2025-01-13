package com.techical_test_riservi.reservation.infrastructure.api;

import com.techical_test_riservi.reservation.application.useCase.*;
import com.techical_test_riservi.reservation.domain.Client;
import com.techical_test_riservi.reservation.domain.Reservation;
import com.techical_test_riservi.reservation.domain.Restaurant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "reservations/")
public class ReservationController {

    private final GetReservationByDay getReservationByDay;
    private final GetReservationById getReservationById;
    private final GetReservationsByCellPhoneNumberClient getReservationsByCellPhoneNumberClient;
    private final CreateReservation createReservation;
    private final UpdateReservation updateReservation;
    private final DeleteReservationById deleteReservationById;

    @Autowired
    public ReservationController(GetReservationByDay getReservationByDay, GetReservationById getReservationById, GetReservationsByCellPhoneNumberClient getReservationsByCellPhoneNumberClient, CreateReservation createReservation, UpdateReservation updateReservation, DeleteReservationById deleteReservationById) {
        this.getReservationByDay = getReservationByDay;
        this.getReservationById = getReservationById;
        this.getReservationsByCellPhoneNumberClient = getReservationsByCellPhoneNumberClient;
        this.createReservation = createReservation;
        this.updateReservation = updateReservation;
        this.deleteReservationById = deleteReservationById;
    }

    @PostMapping
    public Reservation createReservation(@RequestBody ReservationRequestDto dto) {
        return createReservation.execute(dto.toDomain());
    }

    @GetMapping("/{day_reservation}/restaurants/{id_restaurant}/branches/{branch_id}")
    public List<Reservation> getReservationsByDay(
            @PathVariable("day_reservation") LocalDate day,
            @PathVariable("id_restaurant") UUID restaurantId,
            @PathVariable("branch_id") UUID branchId
    ) {
        return getReservationByDay.execute(day, restaurantId, branchId);
    }

    @GetMapping("/{id_reservation}")
    public Reservation getReservationsById(
            @PathVariable("id_reservation") UUID id
    ) {
        return getReservationById.execute(id);
    }

    @GetMapping("/clients/phones/{phone}")
    public List<Reservation> getReservationsByCellPhoneNumber(
            @PathVariable("phone") String cellPhoneNumber
    ) {
        return getReservationsByCellPhoneNumberClient.execute(cellPhoneNumber);
    }

    @PutMapping("{id_reservation}")
    public void updateReservation(
            @PathVariable("id_reservation") UUID idReservation,
            @RequestBody ReservationRequestDto dto
    ) {
        updateReservation.execute(idReservation, dto.toDomain());
    }

    @DeleteMapping("{id_reservation}")
    public void deleteReservation(
            @PathVariable("id_reservation") UUID idReservation
    ){
        deleteReservationById.execute(idReservation);
    }
}
