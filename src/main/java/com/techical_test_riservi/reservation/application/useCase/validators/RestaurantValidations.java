package com.techical_test_riservi.reservation.application.useCase.validators;

import com.techical_test_riservi.reservation.domain.exceptions.RestaurantNotFound;
import com.techical_test_riservi.reservation.domain.ports.RetrieveRestaurant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class RestaurantValidations {

    private RetrieveRestaurant retrieveRestaurant;

    @Autowired
    public RestaurantValidations(RetrieveRestaurant retrieveRestaurant) {
        this.retrieveRestaurant = retrieveRestaurant;
    }

    public void execute(UUID restaurantId){
        if (!retrieveRestaurant.getRestaurant(restaurantId)) {
            throw new RestaurantNotFound("Restaurant not found");
        }
    }
}
