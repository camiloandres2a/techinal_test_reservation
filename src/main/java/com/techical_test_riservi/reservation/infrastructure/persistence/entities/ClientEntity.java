package com.techical_test_riservi.reservation.infrastructure.persistence.entities;

import com.techical_test_riservi.reservation.domain.Client;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.util.UUID;

@Entity
@Table(name = "client")
@Builder
@AllArgsConstructor
public class ClientEntity {

    @Id
    @Column(length = 36)
    private String id;

    private String name;
    @Column(name = "cell_phone_number")
    private String cellphoneNumber;

    public ClientEntity() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCellphoneNumber() {
        return cellphoneNumber;
    }

    public void setCellphoneNumber(String cellphoneNumber) {
        this.cellphoneNumber = cellphoneNumber;
    }

    public Client toDomain(){
        return Client.builder()
                .id(UUID.fromString(this.id))
                .name(this.name)
                .cellPhoneNumber(this.cellphoneNumber)
                .build();
    }

    public static ClientEntity from(Client client){
        ClientEntity entity = new ClientEntity();
        entity.setId(String.valueOf(client.getId()));
        entity.setName(client.getName());
        entity.setCellphoneNumber(client.getCellPhoneNumber());

        return entity;
    }

}
