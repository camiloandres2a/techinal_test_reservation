package com.techical_test_riservi.reservation.infrastructure.persistence.adapters;

import com.techical_test_riservi.reservation.infrastructure.persistence.repository.RestaurantRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class RetrieveRestaurantMySqlTest {

    @InjectMocks
    private RetrieveRestaurantMySql retrieveRestaurantMySql;

    @Mock
    private RestaurantRepository restaurantRepository;

    @Test
    void getRestaurant() {
        String id = "744844fe-9e5e-41aa-9578-f3f7602a6511";

        //mock
        var existsRestaurant = true;
        Mockito.when(restaurantRepository.existsById(id))
                .thenReturn(existsRestaurant);

        //when
        var result = retrieveRestaurantMySql.getRestaurant(UUID.fromString(id));

        //then
        assertNotNull(result);
        assertEquals(existsRestaurant, result);

    }
}