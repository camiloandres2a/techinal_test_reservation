package com.techical_test_riservi.reservation.infrastructure.api;

import com.techical_test_riservi.reservation.domain.Branch;
import com.techical_test_riservi.reservation.domain.Client;
import com.techical_test_riservi.reservation.domain.Reservation;
import com.techical_test_riservi.reservation.domain.Restaurant;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collections;
import java.util.UUID;

public class ReservationRequestDto {
    private String clientName;
    private String clientCellphoneNumber;
    private UUID restaurantId;
    private UUID branchId;
    private LocalDate date;
    private LocalTime time;
    private Integer quantityPeople;

    public Reservation toDomain(){
        return Reservation.builder()
                .client(this.toDomainClient())
                .restaurant(this.toDomainRestaurant())
                .date(this.date)
                .time(this.time)
                .quantityPeople(this.quantityPeople)
                .build();
    }

    private Client toDomainClient(){
        return Client.builder()
                .name(this.clientName)
                .cellPhoneNumber(this.clientCellphoneNumber)
                .build();
    }

    private Restaurant toDomainRestaurant(){
        return Restaurant.builder()
                .id(this.restaurantId)
                .branches(Collections.singletonList(Branch.builder()
                        .id(this.branchId)
                        .build()))
                .build();
    }
}
