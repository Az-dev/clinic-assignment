package com.webflux.clinic.services;

import com.webflux.clinic.dto.AppointmentDto;
import com.webflux.clinic.entities.Appointment;
import com.webflux.clinic.repos.AppointmentRepo;
import com.webflux.clinic.utils.CancelCause;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

@Service
@AllArgsConstructor
public class AppointmentService {
    private final AppointmentRepo appointmentRepo;

    public Flux<Appointment> filterByPatientName(String patientName) throws RuntimeException{
        return appointmentRepo.findAllByPatientNameAndDateGreaterThanEqual(patientName,LocalDate.now());
    }

    public Flux<Appointment> getAppointmentsHistoryByPatientName(String patientName) throws RuntimeException{
        return appointmentRepo.findAllByPatientNameAndDateLessThan(patientName,LocalDate.now());
    }

    public Flux<Appointment> filterByDate(LocalDate date) throws RuntimeException{
        return appointmentRepo.findAllByDate(date);
    }

    public Mono<Appointment> createAppointment(AppointmentDto appointment) throws RuntimeException{
        var newAppointment = new ModelMapper().map(appointment,Appointment.class);
        return appointmentRepo.save(newAppointment);
    }

    public Mono<Appointment> cancelAppointment(Long id, CancelCause cancelCause) throws RuntimeException{
        return appointmentRepo.findById(id)
                .switchIfEmpty(Mono.error(new RuntimeException("Appointment with ID " + id + " not found")))
                .flatMap(appointment -> {
                    appointment.setCanceled(true);
                    appointment.setCancellationReason(cancelCause.toString());
                    return appointmentRepo.save(appointment);
                });
    }
}
