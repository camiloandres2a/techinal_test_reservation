package com.techical_test_riservi.reservation.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.UUID;

@Builder
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Reservation {
    private UUID id;
    private Client client;
    private Restaurant  restaurant;
    private DayOfWeek date;
    private LocalTime time;
    private Integer numberPeople;





}
