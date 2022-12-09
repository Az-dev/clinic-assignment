package com.webflux.clinic.repos;

import com.webflux.clinic.entities.Appointment;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.time.LocalDate;

@Repository
public interface AppointmentRepo extends ReactiveCrudRepository<Appointment,Long> {
    public Flux<Appointment> findAllByDate(LocalDate date);

    public Flux<Appointment> findAllByPatientNameAndDateGreaterThanEqual(String patientName, LocalDate date);

    public Flux<Appointment> findAllByPatientNameAndDateLessThan(String patientName, LocalDate date);
}
