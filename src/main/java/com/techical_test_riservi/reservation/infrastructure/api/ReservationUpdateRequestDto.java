package com.techical_test_riservi.reservation.infrastructure.api;

import com.techical_test_riservi.reservation.domain.Branch;
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
public class ReservationUpdateRequestDto {
    private UUID restaurantId;
    private UUID branchId;
    private DayOfWeek date;
    private LocalTime time;
    private Integer quantityPeople;

    public Reservation toDomain(){
        return Reservation.builder()
                .restaurant(this.toDomainRestaurant())
                .date(this.date)
                .time(this.time)
                .numberPeople(this.quantityPeople)
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
