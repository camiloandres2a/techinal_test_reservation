package com.techical_test_riservi.reservation.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Builder
@Getter
@Setter
public class Restaurant {

    private UUID id;
    private String name;
    private List<Branch> branches;
}
