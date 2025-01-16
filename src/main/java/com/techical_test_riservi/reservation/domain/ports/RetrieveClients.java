package com.techical_test_riservi.reservation.domain.ports;

import com.techical_test_riservi.reservation.domain.Client;

import java.util.Optional;

public interface RetrieveClients {
    Boolean isClientExistByCellPhone(String cellPhone);
    Client createClient(Client client);
    Optional<Client> getClientByCellPhone(String cellPhone);
}
