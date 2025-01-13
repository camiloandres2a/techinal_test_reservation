package com.techical_test_riservi.reservation.domain.ports;

import com.techical_test_riservi.reservation.domain.Client;

import java.util.Optional;

public interface RetrieveClients {
    Boolean isClientExistByCellPhone(String cellPhone);
    void createClient(String name, String cellPhoneNumber);
    Client getClientByCellPhone(String cellPhone);
}
