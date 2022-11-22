package ro.musiclover.manicureappointments.service.implementation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.musiclover.manicureappointments.entity.Appointment;
import ro.musiclover.manicureappointments.entity.Customer;
import ro.musiclover.manicureappointments.entity.Manicurist;
import ro.musiclover.manicureappointments.entity.NailsService;
import ro.musiclover.manicureappointments.exception.BusinessException;
import ro.musiclover.manicureappointments.mapper.AppointmentMapper;
import ro.musiclover.manicureappointments.mapper.ManicuristMapper;
import ro.musiclover.manicureappointments.model.appointment.AppointmentRequest;
import ro.musiclover.manicureappointments.model.appointment.AppointmentResponse;
import ro.musiclover.manicureappointments.repository.AppointmentRepository;
import ro.musiclover.manicureappointments.repository.CustomerRepository;
import ro.musiclover.manicureappointments.repository.ManicuristRepository;
import ro.musiclover.manicureappointments.repository.NailsServiceRepository;
import ro.musiclover.manicureappointments.service.interfaces.IAppointment;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AppointmentService implements IAppointment {
    private final AppointmentRepository appointmentRepository;
    private final AppointmentMapper appointmentMapper;
    private final ManicuristRepository manicuristRepository;
    private final ManicuristMapper manicuristMapper;
    private final CustomerRepository customerRepository;
    private final NailsServiceRepository nailsServiceRepository;

    @Override
    public AppointmentResponse createAppointment(AppointmentRequest appointmentRequest) {
        Appointment appointmentToSave = appointmentMapper.map(appointmentRequest);
        Manicurist manicurist = manicuristRepository.findById(appointmentRequest.getManicuristId()).orElseThrow(
                () -> new BusinessException("Manicurist not found")
        );
        appointmentToSave.setManicurist(manicurist);
        Customer customer = customerRepository.findById(appointmentRequest.getCustomerId()).orElseThrow(
                () -> new BusinessException("Customer not found")
        );
        appointmentToSave.setCustomer(customer);
        List<NailsService> nailsServices = new ArrayList<>();
        for (Integer id : appointmentRequest.getNailsServicesIds()) {
            NailsService nailsService = nailsServiceRepository.findById(id).orElseThrow(
                    () -> new BusinessException("NailsService not found")
            );
            nailsServices.add(nailsService);
            appointmentToSave.setNailsServices((List<NailsService>) nailsService);
        }

        Appointment appointmentForResponse = appointmentRepository.save(appointmentToSave);
        return appointmentMapper.map(appointmentForResponse);
    }
}

