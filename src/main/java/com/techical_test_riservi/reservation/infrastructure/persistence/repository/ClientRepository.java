package com.techical_test_riservi.reservation.infrastructure.persistence.repository;

import com.techical_test_riservi.reservation.infrastructure.persistence.entities.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<ClientEntity, String> {

    Boolean existsByCellphoneNumber(String cellphone);
    Optional<ClientEntity> findByCellphoneNumber(String cellphone);
}
