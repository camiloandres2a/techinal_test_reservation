package com.techical_test_riservi.reservation.domain;

import lombok.Builder;
import lombok.Getter;

import java.util.UUID;
import java.util.List;

@Builder
@Getter
public class Branch {

    private UUID id;
    private Integer availableSeats;
    private String address;
    private List<Schedule> schedule;
}
