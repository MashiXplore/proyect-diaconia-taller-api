package com.taller.reparacionApi.controller;

import com.taller.reparacionApi.dto.TechnicianDTO;
import com.taller.reparacionApi.model.Technician;
import com.taller.reparacionApi.service.TechnicianService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/technicians")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class TechnicianController {

    private final TechnicianService technicianService;

    @GetMapping
    public ResponseEntity<List<TechnicianDTO>> getAllTechnicians() {
        List<TechnicianDTO> technicians = technicianService.getAllTechnicians();
        return ResponseEntity.ok(technicians);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TechnicianDTO> getTechnicianById(@PathVariable String id) {
        return technicianService.getTechnicianById(id)
                .map(technician -> ResponseEntity.ok(technician))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<TechnicianDTO> getTechnicianByUsername(@PathVariable String username) {
        return technicianService.getTechnicianByUsername(username)
                .map(technician -> ResponseEntity.ok(technician))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<TechnicianDTO> getTechnicianByEmail(@PathVariable String email) {
        return technicianService.getTechnicianByEmail(email)
                .map(technician -> ResponseEntity.ok(technician))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<TechnicianDTO> getTechnicianByEmployeeId(@PathVariable String employeeId) {
        return technicianService.getTechnicianByEmployeeId(employeeId)
                .map(technician -> ResponseEntity.ok(technician))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/role/{role}")
    public ResponseEntity<List<TechnicianDTO>> getTechniciansByRole(@PathVariable String role) {
        List<TechnicianDTO> technicians = technicianService.getTechniciansByRole(role);
        return ResponseEntity.ok(technicians);
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<TechnicianDTO>> getTechniciansByStatus(@PathVariable String status) {
        List<TechnicianDTO> technicians = technicianService.getTechniciansByStatus(status);
        return ResponseEntity.ok(technicians);
    }

    @PostMapping
    public ResponseEntity<?> createTechnician(@RequestBody Technician technician) {
        try {
            TechnicianDTO createdTechnician = technicianService.createTechnician(technician);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdTechnician);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateTechnician(@PathVariable String id, @RequestBody Technician technician) {
        return technicianService.updateTechnician(id, technician)
                .map(updatedTechnician -> ResponseEntity.ok(updatedTechnician))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTechnician(@PathVariable String id) {
        if (technicianService.deleteTechnician(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}