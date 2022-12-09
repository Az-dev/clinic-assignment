package com.webflux.clinic.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table("appointment")
public class Appointment {

    @Id
    @Column("id")
    private Long id;

    @Column("date")
    private LocalDate date;
    @Column("time")
    private LocalTime time;
    @Column("canceled")
    private boolean canceled;
    @Column("cancellation_reason")
    private String cancellationReason;
    @Column("patient_name")
    private String patientName;
}
