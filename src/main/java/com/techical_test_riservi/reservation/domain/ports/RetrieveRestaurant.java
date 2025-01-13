package com.techical_test_riservi.reservation.domain.ports;

import com.techical_test_riservi.reservation.domain.Restaurant;

import java.util.Optional;
import java.util.UUID;

public interface RetrieveRestaurant {
    Optional<Restaurant> getRestaurant(UUID id);
}
