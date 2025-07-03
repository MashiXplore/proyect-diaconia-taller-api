package com.taller.reparacionApi.config;

import com.taller.reparacionApi.model.Technician;
import com.taller.reparacionApi.repository.TechnicianRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final TechnicianRepository technicianRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        if (!technicianRepository.existsByUsername("tech1")) {
            Technician technician = Technician.builder()
                    .username("tech1")
                    .gmail("tech1@example.com")
                    .password(passwordEncoder.encode("123456"))
                    .role("TECHNICIAN")
                    .status("ACTIVE")
                    .employeeId("EMP001")
                    .profile(Technician.Profile.builder()
                            .firstName("Enrique")
                            .lastName("Tarqui")
                            .phone("123456789")
                            .address("La Paz")
                            .avatar("")
                            .build())
                    .build();

            technicianRepository.save(technician);
            System.out.println("Default technician created.");
        }
    }
}
