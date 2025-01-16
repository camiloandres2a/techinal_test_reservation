package com.techical_test_riservi.reservation.application.useCase;

import com.techical_test_riservi.reservation.domain.Client;
import com.techical_test_riservi.reservation.domain.Reservation;
import com.techical_test_riservi.reservation.domain.ports.RetrieveClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CreateClient {

    private RetrieveClients retrieveClients;

    @Autowired
    public CreateClient(RetrieveClients retrieveClients) {
        this.retrieveClients = retrieveClients;
    }

    public void execute(String clientName, String clientCellPhone, Reservation reservation){
        var creatingUUId = UUID.randomUUID();
        var clientRetrieved = retrieveClients.isClientExistByCellPhone(clientCellPhone);
        if (!clientRetrieved) {
            retrieveClients.createClient(Client.instantiate(clientCellPhone, clientName, reservation, creatingUUId));
        }else{
            reservation.getClient().setId(creatingUUId);
        }
    }


}
