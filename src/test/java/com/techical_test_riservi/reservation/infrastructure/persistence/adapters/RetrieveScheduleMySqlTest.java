package com.techical_test_riservi.reservation.infrastructure.persistence.adapters;

import com.techical_test_riservi.reservation.infrastructure.persistence.entities.ScheduleEntity;
import com.techical_test_riservi.reservation.infrastructure.persistence.repository.ScheduleRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Collections;
import java.util.UUID;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class RetrieveScheduleMySqlTest {

    @InjectMocks
    private RetrieveScheduleMySql retrieveScheduleMySql;

    @Mock
    private ScheduleRepository scheduleRepository;

    @Test
    void isADayAvailable() {
        String idBranch = "f18b1cc5-d076-4421-a3d0-84f824fe5a2f";
        String day = "WEDNESDAY";

        var existsByBranchEntityIdAndDate = true;
        Mockito.when(scheduleRepository.existsByBranchEntityIdAndDate(idBranch, DayOfWeek.valueOf(day)))
                .thenReturn(existsByBranchEntityIdAndDate);

        //when
        var result = retrieveScheduleMySql.isADayAvailable(UUID.fromString(idBranch), DayOfWeek.valueOf(day));

        //THEN
        assertEquals(existsByBranchEntityIdAndDate, result);
        Mockito.verify(scheduleRepository).existsByBranchEntityIdAndDate(idBranch, DayOfWeek.valueOf(day));

    }

    @Test
    void getRangeHour() {
        String idBranch = "f18b1cc5-d076-4421-a3d0-84f824fe5a2f";
        String day = "WEDNESDAY";

        List<ScheduleEntity> scheduleEntity = Collections.singletonList(
                ScheduleEntity.builder()
                        .id(String.valueOf(UUID.randomUUID()))
                        .date(DayOfWeek.valueOf("WEDNESDAY"))
                        .startHour(LocalTime.parse("09:00:00"))
                        .endHour(LocalTime.parse("15:00:00"))
                        .build()
        );

        Mockito.when(scheduleRepository.findByBranchEntityIdAndDate(idBranch, DayOfWeek.valueOf(day)))
                .thenReturn(scheduleEntity);

        //when
        var result = retrieveScheduleMySql.getRangeHour(UUID.fromString(idBranch), DayOfWeek.valueOf(day));

        assertEquals(scheduleEntity.get(0).getStartHour(), result.getStartTime());
        assertEquals(scheduleEntity.get(0).getEndHour(), result.getEndTime());
        assertEquals(scheduleEntity.get(0).getDate(), result.getDay());
        Mockito.verify(scheduleRepository).findByBranchEntityIdAndDate(idBranch, DayOfWeek.valueOf(day));

    }

}