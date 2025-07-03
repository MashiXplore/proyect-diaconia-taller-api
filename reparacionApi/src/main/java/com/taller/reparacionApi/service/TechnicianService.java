package com.taller.reparacionApi.service;

import com.taller.reparacionApi.dto.TechnicianDTO;
import com.taller.reparacionApi.model.Technician;
import com.taller.reparacionApi.repository.TechnicianRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TechnicianService {

    private final TechnicianRepository technicianRepository;
    private final PasswordEncoder passwordEncoder;

    public List<TechnicianDTO> getAllTechnicians() {
        return technicianRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public Optional<TechnicianDTO> getTechnicianById(String id) {
        return technicianRepository.findById(id)
                .map(this::convertToDTO);
    }

    public Optional<TechnicianDTO> getTechnicianByUsername(String username) {
        return technicianRepository.findByUsername(username)
                .map(this::convertToDTO);
    }

    public Optional<TechnicianDTO> getTechnicianByEmail(String email) {
        return technicianRepository.findByGmail(email)
                .map(this::convertToDTO);
    }

    public Optional<TechnicianDTO> getTechnicianByEmployeeId(String employeeId) {
        return technicianRepository.findByEmployeeId(employeeId)
                .map(this::convertToDTO);
    }

    public List<TechnicianDTO> getTechniciansByRole(String role) {
        return technicianRepository.findByRole(role)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<TechnicianDTO> getTechniciansByStatus(String status) {
        return technicianRepository.findByStatus(status)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public TechnicianDTO createTechnician(Technician technician) {
        if (technicianRepository.existsByUsername(technician.getUsername())) {
            throw new RuntimeException("Username already exists");
        }
        if (technicianRepository.existsByGmail(technician.getGmail())) {
            throw new RuntimeException("Email already exists");
        }
        if (technicianRepository.existsByEmployeeId(technician.getEmployeeId())) {
            throw new RuntimeException("Employee ID already exists");
        }

        // Encriptar password
        technician.setPassword(passwordEncoder.encode(technician.getPassword()));

        Technician savedTechnician = technicianRepository.save(technician);
        return convertToDTO(savedTechnician);
    }

    public Optional<TechnicianDTO> updateTechnician(String id, Technician technician) {
        return technicianRepository.findById(id)
                .map(existingTechnician -> {
                    if (technician.getUsername() != null) {
                        existingTechnician.setUsername(technician.getUsername());
                    }
                    if (technician.getGmail() != null) {
                        existingTechnician.setGmail(technician.getGmail());
                    }
                    if (technician.getPassword() != null) {
                        existingTechnician.setPassword(passwordEncoder.encode(technician.getPassword()));
                    }
                    if (technician.getRole() != null) {
                        existingTechnician.setRole(technician.getRole());
                    }
                    if (technician.getStatus() != null) {
                        existingTechnician.setStatus(technician.getStatus());
                    }
                    if (technician.getProfile() != null) {
                        existingTechnician.setProfile(technician.getProfile());
                    }
                    if (technician.getEmployeeId() != null) {
                        existingTechnician.setEmployeeId(technician.getEmployeeId());
                    }

                    Technician updatedTechnician = technicianRepository.save(existingTechnician);
                    return convertToDTO(updatedTechnician);
                });
    }

    public boolean deleteTechnician(String id) {
        if (technicianRepository.existsById(id)) {
            technicianRepository.deleteById(id);
            return true;
        }
        return false;
    }

    private TechnicianDTO convertToDTO(Technician technician) {
        TechnicianDTO.ProfileDTO profileDTO = null;
        if (technician.getProfile() != null) {
            profileDTO = TechnicianDTO.ProfileDTO.builder()
                    .firstName(technician.getProfile().getFirstName())
                    .lastName(technician.getProfile().getLastName())
                    .phone(technician.getProfile().getPhone())
                    .address(technician.getProfile().getAddress())
                    .avatar(technician.getProfile().getAvatar())
                    .build();
        }

        return TechnicianDTO.builder()
                .id(technician.getId())
                .username(technician.getUsername())
                .gmail(technician.getGmail())
                .role(technician.getRole())
                .status(technician.getStatus())
                .profile(profileDTO)
                .employeeId(technician.getEmployeeId())
                .build();
    }
}