package com.techical_test_riservi.reservation.domain.exceptions;

public class ClientNotFound extends RuntimeException{
    public ClientNotFound(String message) {
        super(message);
    }
}
