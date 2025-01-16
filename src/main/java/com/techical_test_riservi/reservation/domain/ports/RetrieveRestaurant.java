package com.techical_test_riservi.reservation.domain.ports;

import java.util.UUID;

public interface RetrieveRestaurant {
    Boolean getRestaurant(UUID id);
}
