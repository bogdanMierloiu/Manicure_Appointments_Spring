package ro.musiclover.manicureappointments.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.musiclover.manicureappointments.entity.Appointment;
import ro.musiclover.manicureappointments.entity.Customer;
import ro.musiclover.manicureappointments.entity.NailsCare;
import ro.musiclover.manicureappointments.exception.BusinessException;
import ro.musiclover.manicureappointments.mapper.AppointmentMapper;
import ro.musiclover.manicureappointments.model.EmailDetails;
import ro.musiclover.manicureappointments.model.appointment.*;
import ro.musiclover.manicureappointments.model.customer.CustomerResponseForAppointment;
import ro.musiclover.manicureappointments.model.manicurist.ManicuristResponseForAppointment;

import ro.musiclover.manicureappointments.repository.*;


import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional
@RequiredArgsConstructor
public class AppointmentService {
    private final AppointmentRepository appointmentRepository;
    private final AppointmentMapper appointmentMapper;
    private final ManicuristRepository manicuristRepository;
    private final CustomerRepository customerRepository;
    private final NailsCareRepository nailsCareRepository;

    private final EmailService emailService;

    public AppointmentResponse createAppointment(AppointmentRequest appointmentRequest) {
        for (Appointment appointment : appointmentRepository.findAll()) {
            if (appointment.getAppointmentDateTime().equals(appointmentRequest.getAppointmentDateTime())) {
                throw new BusinessException("You already have an appointment at this date and time");
            }
        }

        Appointment appointmentToSave = appointmentMapper.map(appointmentRequest);

        appointmentToSave.setManicurist(manicuristRepository.findById(appointmentRequest.getManicuristId()).orElseThrow(
                () -> new BusinessException("Manicurist not found")));

        Customer customer = customerRepository.findById(appointmentRequest.getCustomerId()).orElseThrow(
                () -> new BusinessException("Customer not found"));
        appointmentToSave.setCustomer(customer);

        List<NailsCare> nailsCares = new ArrayList<>();
        for (Integer id : appointmentRequest.getNailsServicesIds()) {
            NailsCare nailsCare = nailsCareRepository.findById(id).orElseThrow(
                    () -> new BusinessException("NailsServiceWebController not found")
            );
            nailsCares.add(nailsCare);
        }
        appointmentToSave.getNailsCares().addAll(nailsCares);

        appointmentRepository.save(appointmentToSave);

        AppointmentResponse appointmentForResponse = new AppointmentResponse();

        appointmentForResponse.setId(appointmentToSave.getId());
        appointmentForResponse.setAppointmentDateTime(appointmentToSave.getAppointmentDateTime());


        ManicuristResponseForAppointment manicuristResponse = new ManicuristResponseForAppointment();
        manicuristResponse.setFirstName(appointmentToSave.getManicurist().getFirstName());
        manicuristResponse.setLastName(appointmentToSave.getManicurist().getLastName());

        CustomerResponseForAppointment customerResponse = new CustomerResponseForAppointment();
        customerResponse.setId(appointmentToSave.getCustomer().getId());
        customerResponse.setFirstName(appointmentToSave.getCustomer().getFirstName());
        customerResponse.setLastName(appointmentToSave.getCustomer().getLastName());

        List<NailsCare> serviceResponses = new ArrayList<>();
        for (NailsCare nailsCare : appointmentToSave.getNailsCares()) {
            NailsCare nailsCareResponse = new NailsCare();
            nailsCareResponse.setId(nailsCare.getId());
            nailsCareResponse.setServiceName(nailsCare.getServiceName());
            nailsCareResponse.setPrice(nailsCare.getPrice());
            serviceResponses.add(nailsCareResponse);
        }

        appointmentForResponse.setManicurist(manicuristResponse);
        appointmentForResponse.setCustomer(customerResponse);
        appointmentForResponse.getNailsCares().addAll(serviceResponses);

        EmailDetails emailDetails = new EmailDetails();
        emailDetails.setRecipient(customer.getEmail());
        emailDetails.setSubject("Appointment confirmed");
        emailDetails.setMsgBody("Your appointment is confirmed at: " + appointmentToSave.getAppointmentDateTime());
        emailService.sendSimpleMail(emailDetails);
        return appointmentForResponse;
    }


    public AppointmentResponse findById(Integer id) {
        return appointmentMapper.map(appointmentRepository.findById(id).orElseThrow(
                        () -> new BusinessException("AppointmentResponse not found")
                )
        );
    }


    public List<AppointmentResponse> findByAppointmentDate(LocalDateTime date) {
        return appointmentMapper.map(appointmentRepository.findByAppointmentDateTime(date));
    }


    public List<AppointmentResponse> findAll() {
        return appointmentMapper.map(appointmentRepository.findAllByOrderByAppointmentDateTimeDesc());
    }


    public void updateAppointmentDate(Integer id, RequestUpdateDate requestUpdateDate) {
        ro.musiclover.manicureappointments.entity.Appointment appointmentToUpdate = appointmentRepository.findById(id).orElseThrow(
                () -> new BusinessException("AppointmentResponse not found")
        );
        appointmentToUpdate.setAppointmentDateTime(requestUpdateDate.getAppointmentDateTime());
    }


//    public void updateAppointmentTime(Integer id, RequestUpdateTime requestUpdateTime) {
//        ro.musiclover.manicureappointments.entity.Appointment appointmentToUpdate = appointmentRepository.findById(id).orElseThrow(
//                () -> new BusinessException("AppointmentResponse not found")
//        );
//        appointmentToUpdate.setAppointmentDateTime(requestUpdateTime.getAppointmentTime());
//    }


    public void updateNailsServices(Integer id, RequestUpdateServices requestUpdateServices) {
        Appointment appointmentToUpdate = appointmentRepository.findById(id).orElseThrow(
                () -> new BusinessException("AppointmentResponse not found")
        );

        Set<NailsCare> nailsCares = new HashSet<>();
        for (Integer serviceId : requestUpdateServices.getNailsServicesIds()) {
            NailsCare nailsCare = nailsCareRepository.findById(serviceId).orElseThrow(
                    () -> new BusinessException("Service not found")
            );
            nailsCares.add(nailsCare);
        }
        appointmentToUpdate.setNailsCares(nailsCares);
    }

    public void updateCustomer(Integer id, RequestUpdateCustomer requestUpdateCustomer) {
        Appointment appointmentToUpdate = appointmentRepository.findById(id).orElseThrow(
                () -> new BusinessException("AppointmentResponse not found")
        );
        Customer customer = customerRepository.findById(requestUpdateCustomer.getCustomerId()).orElseThrow(
                () -> new BusinessException("AppointmentResponse not found")
        );
        appointmentToUpdate.setCustomer(customer);
    }

    public void delete(Integer id) {
        ro.musiclover.manicureappointments.entity.Appointment appointmentToDelete = appointmentRepository.findById(id).orElseThrow(
                () -> new BusinessException("AppointmentResponse not found")
        );
        appointmentRepository.delete(appointmentToDelete);
    }

    public List<AppointmentResponse> listBetween(LocalDateTime dateFrom, LocalDateTime dateTo) {
        return appointmentMapper.map(appointmentRepository.findByAppointmentDateBetween(dateFrom, dateTo));
    }


}

