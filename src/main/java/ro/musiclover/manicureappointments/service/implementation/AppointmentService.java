package ro.musiclover.manicureappointments.service.implementation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Service
@Transactional
@RequiredArgsConstructor
public class AppointmentService extends Base<Appointment> implements IAppointment {
    private final AppointmentRepository appointmentRepository;
    private final AppointmentMapper appointmentMapper;
    private final ManicuristRepository manicuristRepository;
    private final ManicuristMapper manicuristMapper;
    private final CustomerRepository customerRepository;
    private final NailsServiceRepository nailsServiceRepository;

    @Override
    public AppointmentResponse createAppointment(AppointmentRequest appointmentRequest) {
        Appointment appointmentToSave = appointmentMapper.map(appointmentRequest);

        appointmentToSave.setManicurist(manicuristRepository.findById(appointmentRequest.getManicuristId()).orElseThrow(
                        () -> new BusinessException("Manicurist not found")
                )
        )
        ;

        appointmentToSave.setCustomer(customerRepository.findById(appointmentRequest.getCustomerId()).orElseThrow(
                        () -> new BusinessException("Customer not found")
                )
        );

        List<NailsService> nailsServices = new ArrayList<>();
        for (Integer id : appointmentRequest.getNailsServicesIds()) {
            NailsService nailsService = nailsServiceRepository.findById(id).orElseThrow(
                    () -> new BusinessException("NailsService not found")
            );
            nailsServices.add(nailsService);
        }
        appointmentToSave.getNailsServices().addAll(nailsServices);

        appointmentRepository.save(appointmentToSave);

        AppointmentResponse appointmentForResponse = appointmentMapper.map(appointmentToSave);

        return appointmentForResponse;
    }

    @Override
    public AppointmentResponse findById(Integer id) {
        return appointmentMapper.map(appointmentRepository.findById(id).orElseThrow(
                        () -> new BusinessException("Appointment not found")
                )
        );
    }

    @Override
    public List<AppointmentResponse> findAll() {
        return appointmentMapper.map(appointmentRepository.findAll());
    }

    @Override
    public void updateAppointmentDate(Integer id, AppointmentRequest appointmentRequest) {
        Appointment appointmentToUpdate = appointmentRepository.findById(id).orElseThrow(
                () -> new BusinessException("Appointment not found")
        );
        appointmentToUpdate.setAppointmentDate(appointmentRequest.getAppointmentDate());
    }

    @Override
    public void updateAppointmentTime(Integer id, AppointmentRequest appointmentRequest) {
        Appointment appointmentToUpdate = appointmentRepository.findById(id).orElseThrow(
                () -> new BusinessException("Appointment not found")
        );
        appointmentToUpdate.setAppointmentTime(appointmentRequest.getAppointmentTime());
    }

    @Override
    public void delete(Integer id) {
        Appointment appointmentToDelete = appointmentRepository.findById(id).orElseThrow(
                () -> new BusinessException("Appointment not found")
        );
        appointmentRepository.delete(appointmentToDelete);
    }


}

