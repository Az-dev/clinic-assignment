package com.webflux.clinic.dto;

import com.webflux.clinic.utils.CancelCause;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.relational.core.mapping.Column;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class AppointmentDto {

    @NotNull(message = "date cannot be null")
    private LocalDate date;

    @NotNull(message = "time cannot be null")
    private LocalTime time;


    @Value("false")
    private boolean canceled;

    @NotNull
    @NotBlank
    private CancelCause cancellationReason;

    @NotNull
    @NotBlank
    private String patientName;
}
