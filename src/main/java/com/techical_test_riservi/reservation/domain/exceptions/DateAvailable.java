package com.techical_test_riservi.reservation.domain.exceptions;

public class DateAvailable extends RuntimeException{
    public DateAvailable(String message) {
        super(message);
    }
}
