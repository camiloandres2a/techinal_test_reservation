package com.techical_test_riservi.reservation.domain;

import lombok.Builder;
import lombok.Getter;

import java.util.UUID;
import java.util.List;

@Builder
@Getter
public class Restaurant {

    private UUID id;
    private String name;
    private List<Branch> branches;
}
