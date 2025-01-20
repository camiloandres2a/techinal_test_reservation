package com.techical_test_riservi.reservation.infrastructure.persistence.adapters;

import com.techical_test_riservi.reservation.domain.Client;
import com.techical_test_riservi.reservation.infrastructure.persistence.entities.ClientEntity;
import com.techical_test_riservi.reservation.infrastructure.persistence.repository.ClientRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.Random;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class RetrieveClientMySqlTest {

    @InjectMocks
    private RetrieveClientMySql retrieveClientMySql;

    @Mock
    private ClientRepository clientRepository;

    @Test
    void isClientExistByCellPhone() {
        String cellPhoneNumber = "123456789";

        Mockito.when(clientRepository.existsByCellphoneNumber(cellPhoneNumber))
                .thenReturn(true);

        var result = retrieveClientMySql.isClientExistByCellPhone(cellPhoneNumber);

        assertTrue(result);
    }

    @Test
    void createClient() {
        var id = UUID.randomUUID();
        Client client = Client.builder()
                .id(id)
                .name("Camilo")
                .cellPhoneNumber("3204568765")
                .build();

        ClientEntity mockClientEntity = ClientEntity.builder()
                .id(String.valueOf(id))
                .name("Camilo")
                .cellphoneNumber("3204568765")
                .build();

        // Mock
        Mockito.when(clientRepository.save(Mockito.any(ClientEntity.class)))
                .thenReturn(mockClientEntity);

        // when
        var result = retrieveClientMySql.createClient(client);

        // Then
        assertNotNull(result);
        assertEquals(mockClientEntity.getId(), result.getId().toString());
        assertEquals(mockClientEntity.getName(), result.getName());
        assertEquals(mockClientEntity.getCellphoneNumber(), result.getCellPhoneNumber());

        Mockito.verify(clientRepository).save(Mockito.any(ClientEntity.class));
    }



    @Test
    void getClientByCellPhone() {
        String cellPhoneNumber = "3209876547";

        ClientEntity mockClientEntity = ClientEntity.builder()
                .id(String.valueOf(UUID.randomUUID()))
                .name("Camilo")
                .cellphoneNumber("3204568765")
                .build();

        Optional<ClientEntity> mockClientEntityOptional = Optional.of(mockClientEntity);

        //mock
        Mockito.when(clientRepository.findByCellphoneNumber(cellPhoneNumber))
                .thenReturn(mockClientEntityOptional);

        //when
        var result = retrieveClientMySql.getClientByCellPhone(cellPhoneNumber);

        assertEquals(mockClientEntityOptional.get().getId(), result.get().getId().toString());
        assertEquals(mockClientEntityOptional.get().getName(), result.get().getName());
        assertEquals(mockClientEntityOptional.get().getCellphoneNumber(), result.get().getCellPhoneNumber());
        Mockito.verify(clientRepository).findByCellphoneNumber(cellPhoneNumber);

    }
}