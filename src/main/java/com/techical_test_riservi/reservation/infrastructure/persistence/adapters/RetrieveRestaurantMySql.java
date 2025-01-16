package com.techical_test_riservi.reservation.infrastructure.persistence.adapters;

import com.techical_test_riservi.reservation.domain.ports.RetrieveRestaurant;
import com.techical_test_riservi.reservation.infrastructure.persistence.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class RetrieveRestaurantMySql implements RetrieveRestaurant {

    private RestaurantRepository restaurantRepository;

    @Autowired
    public RetrieveRestaurantMySql(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    @Override
    public Boolean getRestaurant(UUID id) {
        return restaurantRepository.existsById(id.toString());
    }
}
