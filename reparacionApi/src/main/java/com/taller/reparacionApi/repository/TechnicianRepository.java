package com.taller.reparacionApi.repository;

import com.taller.reparacionApi.model.Technician;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.Optional;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TechnicianRepository extends MongoRepository<Technician, String> {

    Optional<Technician> findByUsername(String username);

    Optional<Technician> findByGmail(String gmail);

    Optional<Technician> findByEmployeeId(String employeeId);

    List<Technician> findByRole(String role);

    List<Technician> findByStatus(String status);

    List<Technician> findByRoleAndStatus(String role, String status);

    boolean existsByUsername(String username);

    boolean existsByGmail(String gmail);

    boolean existsByEmployeeId(String employeeId);
}