package ro.musiclover.manicureappointments.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.musiclover.manicureappointments.entity.Appointment;
import ro.musiclover.manicureappointments.entity.Customer;
import ro.musiclover.manicureappointments.entity.NailsCare;
import ro.musiclover.manicureappointments.exception.BusinessException;
import ro.musiclover.manicureappointments.mapper.AppointmentMapper;
import ro.musiclover.manicureappointments.model.utils.EmailDetails;
import ro.musiclover.manicureappointments.model.appointment.*;

import ro.musiclover.manicureappointments.repository.*;


import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional
@RequiredArgsConstructor
public class AppointmentService {
    private final AppointmentRepository appointmentRepository;
    private final ManicuristRepository manicuristRepository;
    private final CustomerRepository customerRepository;
    private final NailsCareRepository nailsCareRepository;
    private final EmailService emailService;
    private final AppointmentMapper appointmentMapper;

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
        Set<NailsCare> nailsCares = new HashSet<>();
        for (Integer id : appointmentRequest.getNailsServicesIds()) {
            NailsCare nailsCare = nailsCareRepository.findById(id).orElseThrow(
                    () -> new BusinessException("NailsServiceWebController not found")
            );
            nailsCares.add(nailsCare);
        }
        appointmentToSave.getNailsCares().addAll(nailsCares);
        EmailDetails emailDetails = new EmailDetails();
        emailDetails.setRecipient(customer.getEmail());
        emailDetails.setSubject("Appointment confirmed");
        emailDetails.setMsgBody("Your appointment is confirmed at: " + appointmentToSave.getAppointmentDateTime());
        emailService.sendSimpleMail(emailDetails);
        return appointmentMapper.map(appointmentRepository.save(appointmentToSave));
    }


    public AppointmentResponse findById(Integer id) {
        return appointmentMapper.map(appointmentRepository.findById(id).orElseThrow(
                        () -> new BusinessException("AppointmentResponse not found")
                )
        );
    }

    public List<AppointmentResponse> findByAppointmentByDate(DateRequest date) {
        return appointmentMapper.map(appointmentRepository.findByAppointmentDateBetween(date.getDate().atStartOfDay(), date.getDate().plusDays(1).atStartOfDay()));
    }


    public List<AppointmentResponse> findAll() {
        return appointmentMapper.map(appointmentRepository.findAllByOrderByAppointmentDateTimeDesc());
    }


    public void updateAppointmentDateTime(RequestUpdateDate requestUpdateDate) {
        for (Appointment appointment : appointmentRepository.findAll()) {
            if (appointment.getAppointmentDateTime().equals(requestUpdateDate.getAppointmentDateTime())) {
                throw new BusinessException("You already have an appointment at this date and time");
            }
        }
        Appointment appointmentToUpdate = appointmentRepository.findById(requestUpdateDate.getId()).orElseThrow(
                () -> new BusinessException("AppointmentResponse not found")
        );
        appointmentToUpdate.setAppointmentDateTime(requestUpdateDate.getAppointmentDateTime());
        EmailDetails emailDetails = new EmailDetails();
        emailDetails.setRecipient(appointmentToUpdate.getCustomer().getEmail());
        emailDetails.setSubject("Appointment date&time changed");
        emailDetails.setMsgBody("Your appointment is changed at: " + appointmentToUpdate.getAppointmentDateTime());
        emailService.sendSimpleMail(emailDetails);
    }

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

    public Integer revenueForPeriod(DateBetweenRequest dateBetweenRequest) {
        List<Appointment> appointments = appointmentRepository.findByAppointmentDateBetween(
                dateBetweenRequest.getDateFrom(), dateBetweenRequest.getDateTo());
        Integer total = 0;
        for (Appointment appointment : appointments) {
            for (NailsCare nailsCare : appointment.getNailsCares()) {
                total += nailsCare.getPrice();
            }
        }
        return total;
    }

    public Integer revenueForDay(DateRequest date) {
        List<Appointment> appointments = appointmentRepository.findByAppointmentDateBetween(
                date.getDate().atStartOfDay(), date.getDate().plusDays(1).atStartOfDay());
        var total = 0;
        for (Appointment appointment : appointments) {
            for (NailsCare nailsCare : appointment.getNailsCares()) {
                total += nailsCare.getPrice();
            }
        }
        return total;
    }


}

