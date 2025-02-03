package com.example.firstTry.services;

import com.example.firstTry.model.*;
import com.example.firstTry.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationRepository notificationRepository;
    private final SimpMessagingTemplate messagingTemplate;

    // Notify an Admin
    public void notifyAdmin(Admin admin, String message, NotificationType type) {
        Notification notification = new Notification();
        notification.setAdminRecipient(admin);
        notification.setMessage(message);
        notification.setType(type);
        notificationRepository.save(notification);

        messagingTemplate.convertAndSend("/topic/admin/" + admin.getId(), notification);
    }

    // Notify a Doctor
    public void notifyDoctor(Doctor doctor, String message, NotificationType type) {
        Notification notification = new Notification();
        notification.setDoctorRecipient(doctor);
        notification.setMessage(message);
        notification.setType(type);
        notificationRepository.save(notification);

        messagingTemplate.convertAndSend("/topic/doctor/" + doctor.getId(), notification);
    }

    // Notify a Patient
    public void notifyPatient(Patient patient, String message, NotificationType type) {
        Notification notification = new Notification();
        notification.setPatientRecipient(patient);
        notification.setMessage(message);
        notification.setType(type);
        notificationRepository.save(notification);

        messagingTemplate.convertAndSend("/topic/patient/" + patient.getId(), notification);
    }

    // Retrieve notifications
    public List<Notification> getAdminNotifications(Admin admin) {
        return notificationRepository.findByAdminRecipient(admin);
    }

    public List<Notification> getDoctorNotifications(Doctor doctor) {
        return notificationRepository.findByDoctorRecipient(doctor);
    }

    public List<Notification> getPatientNotifications(Patient patient) {
        return notificationRepository.findByPatientRecipient(patient);
    }
}
