package ro.musiclover.manicureappointments.service.implementation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.musiclover.manicureappointments.entity.Appointment;
import ro.musiclover.manicureappointments.entity.Customer;
import ro.musiclover.manicureappointments.entity.NailsService;
import ro.musiclover.manicureappointments.exception.BusinessException;
import ro.musiclover.manicureappointments.mapper.AppointmentMapper;
import ro.musiclover.manicureappointments.mapper.ManicuristMapper;
import ro.musiclover.manicureappointments.model.appointment.*;
import ro.musiclover.manicureappointments.model.customer.CustomerResponseForAppointment;
import ro.musiclover.manicureappointments.model.manicurist.ManicuristResponseForAppointment;
import ro.musiclover.manicureappointments.model.nails_services.NailsServiceResponse;
import ro.musiclover.manicureappointments.repository.*;
import ro.musiclover.manicureappointments.service.interfaces.IAppointment;

import java.util.ArrayList;
import java.util.List;

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
        for (Appointment appointment : appointmentRepository.findAll()) {
            if (appointment.getAppointmentDate().isEqual(appointmentRequest.getAppointmentDate()) &&
                    appointment.getAppointmentTime().equals(appointmentRequest.getAppointmentTime())) {
                throw new BusinessException("You already have an appointment at this date and time");
            }
        }

        Appointment appointmentToSave = appointmentMapper.map(appointmentRequest);

        appointmentToSave.setManicurist(manicuristRepository.findById(appointmentRequest.getManicuristId()).orElseThrow(
                () -> new BusinessException("Manicurist not found")));

        appointmentToSave.setCustomer(customerRepository.findById(appointmentRequest.getCustomerId()).orElseThrow(
                () -> new BusinessException("Customer not found")));

        List<NailsService> nailsServices = new ArrayList<>();
        for (Integer id : appointmentRequest.getNailsServicesIds()) {
            NailsService nailsService = nailsServiceRepository.findById(id).orElseThrow(
                    () -> new BusinessException("NailsService not found")
            );
            nailsServices.add(nailsService);
        }
        appointmentToSave.getNailsServices().addAll(nailsServices);

        appointmentRepository.save(appointmentToSave);

        AppointmentResponse appointmentForResponse = new AppointmentResponse();

        appointmentForResponse.setId(appointmentToSave.getId());
        appointmentForResponse.setAppointmentDate(appointmentToSave.getAppointmentDate());
        appointmentForResponse.setAppointmentTime(appointmentToSave.getAppointmentTime());

        ManicuristResponseForAppointment manicuristResponse = new ManicuristResponseForAppointment();
        manicuristResponse.setFirstName(appointmentToSave.getManicurist().getFirstName());
        manicuristResponse.setLastName(appointmentToSave.getManicurist().getLastName());

        CustomerResponseForAppointment customerResponse = new CustomerResponseForAppointment();
        customerResponse.setId(appointmentToSave.getCustomer().getId());
        customerResponse.setFirstName(appointmentToSave.getCustomer().getFirstName());
        customerResponse.setLastName(appointmentToSave.getCustomer().getLastName());

        List<NailsServiceResponse> serviceResponses = new ArrayList<>();
        for (NailsService nailsService: appointmentToSave.getNailsServices()){
            NailsServiceResponse nailsServiceResponse = new NailsServiceResponse();
            nailsServiceResponse.setId(nailsService.getId());
            nailsServiceResponse.setServiceName(nailsService.getServiceName());
            nailsServiceResponse.setPrice(nailsService.getPrice());
            serviceResponses.add(nailsServiceResponse);
        }

        appointmentForResponse.setManicurist(manicuristResponse);
        appointmentForResponse.setCustomer(customerResponse);
        appointmentForResponse.getNailsServices().addAll(serviceResponses);

        return appointmentForResponse;
    }

    @Override
    public AppointmentResponse findById(Integer id) {
        return appointmentMapper.map(appointmentRepository.findById(id).orElseThrow(
                        () -> new BusinessException("Appointment not found")
                )
        );
    }

//    @Override
//    public List<AppointmentResponse> findByAppointmentDate(LocalDate date) {
//        List<AppointmentResponse> listByDate = appointmentRepository.findByAppointmentDate(date);
//        return listByDate;
//    }

    @Override
    public List<AppointmentResponse> findAll() {
        return appointmentMapper.map(appointmentRepository.findAll());


    }

    @Override
    public void updateAppointmentDate(Integer id, RequestUpdateDate requestUpdateDate) {
        Appointment appointmentToUpdate = appointmentRepository.findById(id).orElseThrow(
                () -> new BusinessException("Appointment not found")
        );
        appointmentToUpdate.setAppointmentDate(requestUpdateDate.getAppointmentDate());
    }

    @Override
    public void updateAppointmentTime(Integer id, RequestUpdateTime requestUpdateTime) {
        Appointment appointmentToUpdate = appointmentRepository.findById(id).orElseThrow(
                () -> new BusinessException("Appointment not found")
        );
        appointmentToUpdate.setAppointmentTime(requestUpdateTime.getAppointmentTime());
    }

    @Override
    public void updateNailsServices(Integer id, RequestUpdateServices requestUpdateServices) {
        Appointment appointmentToUpdate = appointmentRepository.findById(id).orElseThrow(
                () -> new BusinessException("Appointment not found")
        );

        List<NailsService> nailsServices = new ArrayList<>();
        for (Integer serviceId : requestUpdateServices.getNailsServicesIds()) {
            NailsService nailsService = nailsServiceRepository.findById(serviceId).orElseThrow(
                    () -> new BusinessException("Service not found")
            );
            nailsServices.add(nailsService);
        }
        appointmentToUpdate.getNailsServices().addAll(nailsServices);
    }

    @Override
    public void updateCustomer(Integer id, RequestUpdateCustomer requestUpdateCustomer) {
        Appointment appointmentToUpdate = appointmentRepository.findById(id).orElseThrow(
                () -> new BusinessException("Appointment not found")
        );
        Customer customer = customerRepository.findById(requestUpdateCustomer.getCustomerId()).orElseThrow(
                () -> new BusinessException("Appointment not found")
        );
        appointmentToUpdate.setCustomer(customer);
    }

    @Override
    public void delete(Integer id) {
        Appointment appointmentToDelete = appointmentRepository.findById(id).orElseThrow(
                () -> new BusinessException("Appointment not found")
        );
        appointmentRepository.delete(appointmentToDelete);
    }


}

