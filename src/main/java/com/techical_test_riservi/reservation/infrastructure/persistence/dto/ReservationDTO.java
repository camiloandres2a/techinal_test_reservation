package com.techical_test_riservi.reservation.infrastructure.persistence.dto;

import com.techical_test_riservi.reservation.domain.Branch;
import com.techical_test_riservi.reservation.domain.Client;
import com.techical_test_riservi.reservation.domain.Reservation;
import com.techical_test_riservi.reservation.domain.Restaurant;
import lombok.Getter;
import lombok.Setter;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Collections;
import java.util.UUID;

@Getter
@Setter
public class ReservationDTO {
    private String reservationId;
    private String clientId;
    private String branchId;
    private String dateTaken;
    private LocalTime hourTaken;
    private Integer numberPeople;

    public ReservationDTO(String reservationId, String clientId, String branchId, String dateTaken, LocalTime hourTaken, Integer numberPeople) {
        this.reservationId = reservationId;
        this.clientId = clientId;
        this.branchId = branchId;
        this.dateTaken = dateTaken;
        this.hourTaken = hourTaken;
        this.numberPeople = numberPeople;
    }

    public Reservation toEntity(){
        return Reservation.builder()
                .id(UUID.fromString(this.reservationId))
                .client(Client.builder()
                        .id(UUID.fromString(this.clientId))
                        .build())
                .restaurant(Restaurant.builder()
                        .branches(Collections.singletonList(
                                Branch.builder()
                                        .id(UUID.fromString(this.branchId))
                                        .build())
                        )
                        .build())
                .date(DayOfWeek.valueOf(this.dateTaken))
                .time(this.hourTaken)
                .numberPeople(this.numberPeople)
                .build();
    }
}
