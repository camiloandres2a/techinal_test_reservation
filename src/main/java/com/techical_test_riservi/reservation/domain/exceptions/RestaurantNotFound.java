package com.techical_test_riservi.reservation.domain.exceptions;

public class RestaurantNotFound extends RuntimeException{
    public RestaurantNotFound(String message) {
        super(message);
    }
}
