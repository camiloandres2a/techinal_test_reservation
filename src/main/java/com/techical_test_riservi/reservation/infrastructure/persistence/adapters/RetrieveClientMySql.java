package com.techical_test_riservi.reservation.infrastructure.persistence.adapters;

import com.techical_test_riservi.reservation.domain.Client;
import com.techical_test_riservi.reservation.domain.ports.RetrieveClients;
import com.techical_test_riservi.reservation.infrastructure.persistence.entities.ClientEntity;
import com.techical_test_riservi.reservation.infrastructure.persistence.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class RetrieveClientMySql implements RetrieveClients {

    private ClientRepository clientRepository;

    @Autowired
    public RetrieveClientMySql(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public Boolean isClientExistByCellPhone(String cellPhone) {
        return clientRepository.existsByCellphoneNumber(cellPhone);
    }

    @Override
    public Client createClient(Client client) {
        var record = ClientEntity.from(client);
        return clientRepository.save(record).toDomain();
    }

    @Override
    public Optional<Client> getClientByCellPhone(String cellPhone) {
        return clientRepository.findByCellphoneNumber(cellPhone).map(ClientEntity::toDomain);
    }
}
