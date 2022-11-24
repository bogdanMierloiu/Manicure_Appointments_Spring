package ro.musiclover.manicureappointments.service.interfaces;

import ro.musiclover.manicureappointments.model.appointment.*;
import ro.musiclover.manicureappointments.model.customer.CustomerDetailResponse;

import java.util.List;

public interface IAppointment {

    AppointmentResponse createAppointment(AppointmentRequest appointmentRequest);

    AppointmentResponse findById(Integer id);

    List<AppointmentResponse> findAll();


    void updateAppointmentDate(Integer id, RequestUpdateDate requestUpdateDate);

    void updateAppointmentTime(Integer id, RequestUpdateTime requestUpdateTime);

    void updateNailsServices(Integer id, RequestUpdateServices requestUpdateServices);

    void updateCustomer(Integer id, RequestUpdateCustomer requestUpdateCustomer);

    void delete(Integer id);


}
