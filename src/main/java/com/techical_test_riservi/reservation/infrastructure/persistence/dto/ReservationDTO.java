package com.techical_test_riservi.reservation.infrastructure.persistence.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

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
}
