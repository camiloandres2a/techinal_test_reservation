package com.techical_test_riservi.reservation.infrastructure.persistence.adapters;

import com.techical_test_riservi.reservation.domain.Branch;
import com.techical_test_riservi.reservation.domain.Client;
import com.techical_test_riservi.reservation.domain.Reservation;
import com.techical_test_riservi.reservation.domain.Restaurant;
import com.techical_test_riservi.reservation.infrastructure.persistence.entities.ReservationEntity;
import com.techical_test_riservi.reservation.infrastructure.persistence.repository.ReservationRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class RetrieveReservationMySqlTest {

    @InjectMocks
    RetrieveReservationMySql retrieveReservationMySql;

    @Mock
    private ReservationRepository reservationRepository;

    @Test
    void getReservationById() {
        UUID branchId = UUID.fromString("744844fe-9e5e-41aa-9578-f3f7602a6511");

        ReservationEntity reservation1 = ReservationEntity
                .builder()
                .id(String.valueOf(UUID.randomUUID()))
                .dateTaken(DayOfWeek.FRIDAY.toString())
                .clientId(String.valueOf(UUID.randomUUID()))
                .branchId(branchId.toString())
                .hourTaken(LocalTime.parse("09:00:00"))
                .numberPeople(10)
                .build();

        Optional<ReservationEntity> mockReservationList = Optional.of(reservation1);

        // Mock
        Mockito.when(reservationRepository.findById(branchId.toString()))
                .thenReturn(mockReservationList);

        //When
        var result = retrieveReservationMySql.getReservationById(branchId);

        assertTrue(result.isPresent());
        assertEquals(mockReservationList.get().getId(), result.get().getId().toString());
    }

    @Test
    void getReservationsByDay() {
        UUID branchId = UUID.fromString("744844fe-9e5e-41aa-9578-f3f7602a6511");
        String dayOfWeek = "WEDNESDAY";

        ReservationEntity reservation1 = ReservationEntity
                .builder()
                .id(String.valueOf(UUID.randomUUID()))
                .dateTaken(dayOfWeek)
                .clientId(String.valueOf(UUID.randomUUID()))
                .branchId(branchId.toString())
                .hourTaken(LocalTime.parse("09:00:00"))
                .numberPeople(10)
                .build();

        List<ReservationEntity> mockReservationList = Collections.singletonList(reservation1);

        //Mock
        Mockito.when(reservationRepository.findByDateTakenAndBranchId(dayOfWeek, branchId.toString()))
                .thenReturn(mockReservationList);

        //when
        var result = retrieveReservationMySql.getReservationsByDay(DayOfWeek.valueOf(dayOfWeek), branchId);

        //Then
        assertFalse(result.isEmpty());
        assertEquals(mockReservationList.size(), result.size());
        assertEquals(mockReservationList.get(0).getId(), result.get(0).getId().toString());
    }

    @Test
    void deleteReservationById() {
        String reservationId = "744844fe-9e5e-41aa-9578-f3f7602a6511";

        //mock
        Mockito.doNothing().when(reservationRepository).deleteById(reservationId);

        //then
        retrieveReservationMySql.deleteReservationById(reservationId);

        //then
        Mockito.verify(reservationRepository).deleteById(reservationId);
    }

    @Test
    void updateReservationById() {
        UUID reservationID = UUID.fromString("744844fe-9e5e-41aa-9578-f3f7602a6511");
        Reservation reservation = Reservation.builder()
                .client(
                        Client.builder()
                                .id(UUID.randomUUID())
                                .name("Camilo")
                                .cellPhoneNumber("3203549323")
                                .build()
                )
                .restaurant(Restaurant.builder()
                        .branches(Collections.singletonList(
                                Branch.builder()
                                        .id(UUID.randomUUID())
                                        .build()
                        ))
                        .id(UUID.randomUUID())
                        .build()
                )
                .date(DayOfWeek.valueOf("TUESDAY"))
                .time(LocalTime.parse("11:00:00"))
                .numberPeople(12)
                .build();

        Reservation existingReservation = Reservation.builder()
                .client(
                        Client.builder()
                                .id(UUID.randomUUID())
                                .name("Camilo")
                                .cellPhoneNumber("3203549323")
                                .build()
                )
                .restaurant(Restaurant.builder()
                        .branches(Collections.singletonList(
                                Branch.builder()
                                        .id(UUID.randomUUID())
                                        .build()
                        ))
                        .id(UUID.randomUUID())
                        .build()
                )
                .date(DayOfWeek.valueOf("WEDNESDAY"))
                .time(LocalTime.parse("09:00:00"))
                .numberPeople(10)
                .build();

        ReservationEntity mockReservationList = ReservationEntity
                .builder()
                .id(String.valueOf(UUID.randomUUID()))
                .dateTaken(String.valueOf(reservation.getDate()))
                .clientId(String.valueOf(reservation.getClient().getId()))
                .branchId(String.valueOf(reservation.getRestaurant().getBranches().get(0).getId()))
                .hourTaken(reservation.getTime())
                .numberPeople(reservation.getNumberPeople())
                .build();

        Mockito.when(reservationRepository.save(Mockito.any(ReservationEntity.class)))
                .thenReturn(mockReservationList);

        //when

        var client = retrieveReservationMySql.updateReservationById(reservationID, reservation, Optional.ofNullable(existingReservation));

        //Then

        assertNotNull(client);
        assertEquals(mockReservationList.getNumberPeople(), client.getNumberPeople());
        assertEquals(mockReservationList.getDateTaken(), client.getDate().toString());
        assertEquals(mockReservationList.getHourTaken(), client.getTime());
        assertEquals(mockReservationList.getClientId(), client.getClient().getId().toString());
    }

    @Test
    void getReservationsBy() {
        UUID branchId = UUID.fromString("744844fe-9e5e-41aa-9578-f3f7602a6511");
        String dayOfWeek = "WEDNESDAY";
        LocalTime localTime = LocalTime.parse("09:00:00");

        ReservationEntity reservation1 = ReservationEntity
                .builder()
                .id(String.valueOf(UUID.randomUUID()))
                .dateTaken(dayOfWeek)
                .clientId(String.valueOf(UUID.randomUUID()))
                .branchId(branchId.toString())
                .hourTaken(localTime)
                .numberPeople(10)
                .build();

        List<ReservationEntity> mockReservationList = Collections.singletonList(reservation1);

        // Mock
        Mockito.when(reservationRepository.findByBranchIdAndDateTakenAndHourTaken(branchId.toString(), dayOfWeek, localTime))
                .thenReturn(mockReservationList);

        // when
        var clientResult = retrieveReservationMySql.getReservationsBy(branchId, dayOfWeek,localTime);

        // then
        assertEquals(mockReservationList.size(), clientResult.size());
        assertEquals(branchId, clientResult.get(0).getRestaurant().getBranches().get(0).getId());


        Assertions.assertThat(clientResult)
                .isNotNull()
                .hasSize(1)
                .first()
                .satisfies(r -> {
                    Assertions.assertThat(r.getDate()).isEqualTo(DayOfWeek.valueOf(dayOfWeek));
                    Assertions.assertThat(r.getTime()).isEqualTo(localTime);
                    Assertions.assertThat(r.getNumberPeople()).isEqualTo(mockReservationList.get(0).getNumberPeople());
                    Assertions.assertThat(r.getClient().getId()).isEqualTo(UUID.fromString(mockReservationList.get(0).getClientId()));
                    Assertions.assertThat(r.getRestaurant().getBranches().get(0).getId()).isEqualTo(UUID.fromString(mockReservationList.get(0).getBranchId()));
                });
    }

    @Test
    void createReservation() {
        UUID customId = UUID.randomUUID();
        Reservation reservation = Reservation.builder()
                .client(
                        Client.builder()
                                .id(UUID.randomUUID())
                                .name("Camilo")
                                .cellPhoneNumber("3203549323")
                                .build()
                )
                .restaurant(Restaurant.builder()
                        .branches(Collections.singletonList(
                                Branch.builder()
                                        .id(UUID.randomUUID())
                                        .build()
                        ))
                        .id(UUID.randomUUID())
                        .build()
                )
                .date(DayOfWeek.valueOf("WEDNESDAY"))
                .time(LocalTime.parse("09:00:00"))
                .numberPeople(10)
                .build();

        ReservationEntity reservation1 = ReservationEntity
                .builder()
                .id(customId.toString())
                .dateTaken(String.valueOf(reservation.getDate()))
                .clientId(reservation.getClient().getId().toString())
                .branchId(reservation.getRestaurant().getBranches().get(0).getId().toString())
                .hourTaken(reservation.getTime())
                .numberPeople(reservation.getNumberPeople())
                .build();

        // Mock
        Mockito.when(reservationRepository.save(Mockito.any(ReservationEntity.class)))
                .thenReturn(reservation1);

        var result = retrieveReservationMySql.createReservation(reservation);

        Assertions.assertThat(result)
                .isNotNull()
                .satisfies(r -> {
                    Assertions.assertThat(r.getDate()).isEqualTo(DayOfWeek.valueOf(reservation1.getDateTaken()));
                    Assertions.assertThat(r.getTime()).isEqualTo(reservation1.getHourTaken());
                    Assertions.assertThat(r.getNumberPeople()).isEqualTo(reservation1.getNumberPeople());
                    Assertions.assertThat(r.getClient().getId()).isEqualTo(UUID.fromString(reservation1.getClientId()));
                    Assertions.assertThat(r.getRestaurant().getBranches().get(0).getId()).isEqualTo(UUID.fromString(reservation1.getBranchId()));
                });
    }
}