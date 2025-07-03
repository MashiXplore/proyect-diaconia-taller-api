package com.taller.reparacionApi.controller;

import com.taller.reparacionApi.dto.EquipmentDTO;
import com.taller.reparacionApi.model.Equipment;
import com.taller.reparacionApi.service.EquipmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/equipments")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class EquipmentController {

    private final EquipmentService equipmentService;

    @GetMapping
    public ResponseEntity<List<EquipmentDTO>> getAllEquipments() {
        List<EquipmentDTO> equipments = equipmentService.getAllEquipments();
        return ResponseEntity.ok(equipments);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EquipmentDTO> getEquipmentById(@PathVariable String id) {
        return equipmentService.getEquipmentById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/tag/{tagNumber}")
    public ResponseEntity<EquipmentDTO> getEquipmentByTagNumber(@PathVariable String tagNumber) {
        return equipmentService.getEquipmentByTagNumber(tagNumber)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/serial/{serialNumber}")
    public ResponseEntity<EquipmentDTO> getEquipmentBySerialNumber(@PathVariable String serialNumber) {
        return equipmentService.getEquipmentBySerialNumber(serialNumber)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/type/{equipmentType}")
    public ResponseEntity<List<EquipmentDTO>> getEquipmentsByType(@PathVariable String equipmentType) {
        List<EquipmentDTO> equipments = equipmentService.getEquipmentsByType(equipmentType);
        return ResponseEntity.ok(equipments);
    }

    @PostMapping
    public ResponseEntity<?> createEquipment(@RequestBody Equipment equipment) {
        try {
            EquipmentDTO created = equipmentService.createEquipment(equipment);
            return ResponseEntity.status(HttpStatus.CREATED).body(created);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateEquipment(@PathVariable String id, @RequestBody Equipment equipment) {
        return equipmentService.updateEquipment(id, equipment)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEquipment(@PathVariable String id) {
        if (equipmentService.deleteEquipment(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
