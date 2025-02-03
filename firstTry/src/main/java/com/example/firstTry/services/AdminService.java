package com.example.firstTry.services;

import com.example.firstTry.model.Doctor;
import com.example.firstTry.repository.DoctorRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminService {
    private final DoctorRepository doctorRepository;

    public List<Doctor> getPendingDoctors() {
        return doctorRepository.findByEnabledFalse();
    }

    @Transactional
    public Doctor approveDoctor(Long doctorId) {
        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new RuntimeException("Doctor not found with ID: " + doctorId));

        doctor.setEnabled(true);
        return doctorRepository.save(doctor);
    }
}
