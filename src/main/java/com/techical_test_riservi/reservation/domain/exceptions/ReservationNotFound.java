package com.techical_test_riservi.reservation.domain.exceptions;

public class ReservationNotFound extends RuntimeException{
    public ReservationNotFound(String message) {
        super(message);
    }
}
