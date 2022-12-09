package com.webflux.clinic.controllers;

import com.webflux.clinic.dto.AppointmentDto;
import com.webflux.clinic.entities.Appointment;
import com.webflux.clinic.services.AppointmentService;
import com.webflux.clinic.utils.CancelCause;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/appointment")
public class AppointmentController {
    private final AppointmentService appointmentService;

//    @GetMapping(value = "",produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
//    Flux<Appointment> getAppointmentsByDate(@RequestParam("date") String date){
//        try{
//            return appointmentService.filterByDate(LocalDate.parse(date));
//        }catch (RuntimeException ex){
//            return Flux.error(ex);
//        }
//    }

    @GetMapping(value = "/today",produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    Flux<Appointment> getAppointmentsOfToday(){
        try{
            return appointmentService.filterByDate(LocalDate.now());
        }catch (RuntimeException ex){
            return Flux.error(ex);
        }
    }

    @GetMapping(value = "/{date}",produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    Flux<Appointment> filterAppointmentsByDate(@PathVariable("date") String date){
        try{
            return appointmentService.filterByDate(LocalDate.parse(date));
        }catch (RuntimeException ex){
            return Flux.error(ex);
        }
    }

    @GetMapping(value = "/patient/appointments",produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    Flux<Appointment> getAppointmentsOfPatient(@RequestParam("name") String name){
        try{
            return appointmentService.filterByPatientName(name);
        }catch (RuntimeException ex){
            return Flux.error(ex);
        }
    }

    @GetMapping(value = "/patient/history",produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    Flux<Appointment> getPatientAppointmentsHistory(@RequestParam("name") String name){
        try{
            return appointmentService.getAppointmentsHistoryByPatientName(name);
        }catch (RuntimeException ex){
            return Flux.error(ex);
        }
    }

    @PostMapping(value = "/new")
    Mono<ResponseEntity<Appointment>> createNewAppointment(@RequestBody AppointmentDto appointment){
        return appointmentService.createAppointment(appointment).map(appointment1 -> {
            return new ResponseEntity<>(appointment1,HttpStatus.CREATED);
        });
    }

    @PutMapping(value = "/{id}/cancel")
    Mono<ResponseEntity<Appointment>> cancelAppointment(@PathVariable("id") Long id,@RequestParam("cancelCause") CancelCause cancelCause){
        return appointmentService.cancelAppointment(id,cancelCause)
                .map(appointment -> new ResponseEntity<>(appointment,HttpStatus.ACCEPTED));
    }

}
