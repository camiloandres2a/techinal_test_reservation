package com.techical_test_riservi.reservation.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Builder
@Getter
@Setter
public class Branch {

    private UUID id;
    private Integer availableSeats;
    private String address;
    private List<Schedule> schedule;
}
