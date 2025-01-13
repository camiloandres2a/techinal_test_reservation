package com.techical_test_riservi.reservation.domain;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalTime;

@Builder
@Getter
public class Reservation {
    private Client client;
    private Restaurant  restaurant;
    private LocalDate date;
    private LocalTime time;
    private Integer quantityPeople;



}
