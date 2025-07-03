package com.taller.reparacionApi.service;

import com.taller.reparacionApi.dto.EquipmentDTO;
import com.taller.reparacionApi.model.Equipment;
import com.taller.reparacionApi.repository.EquipmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EquipmentService {

    private final EquipmentRepository equipmentRepository;

    public List<EquipmentDTO> getAllEquipments() {
        return equipmentRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public Optional<EquipmentDTO> getEquipmentById(String id) {
        return equipmentRepository.findById(id)
                .map(this::convertToDTO);
    }

    public Optional<EquipmentDTO> getEquipmentByTagNumber(String tagNumber) {
        return equipmentRepository.findByTagNumber(tagNumber)
                .map(this::convertToDTO);
    }

    public Optional<EquipmentDTO> getEquipmentBySerialNumber(String serialNumber) {
        return equipmentRepository.findBySerialNumber(serialNumber)
                .map(this::convertToDTO);
    }

    public List<EquipmentDTO> getEquipmentsByType(String equipmentType) {
        return equipmentRepository.findByEquipmentType(equipmentType)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public EquipmentDTO createEquipment(Equipment equipment) {
        if (equipmentRepository.existsByTagNumber(equipment.getTagNumber())) {
            throw new RuntimeException("Tag number already exists");
        }
        if (equipmentRepository.existsBySerialNumber(equipment.getSerialNumber())) {
            throw new RuntimeException("Serial number already exists");
        }
        Equipment saved = equipmentRepository.save(equipment);
        return convertToDTO(saved);
    }

    public Optional<EquipmentDTO> updateEquipment(String id, Equipment equipment) {
        return equipmentRepository.findById(id)
                .map(existing -> {
                    if (equipment.getTagNumber() != null) existing.setTagNumber(equipment.getTagNumber());
                    if (equipment.getEquipmentType() != null) existing.setEquipmentType(equipment.getEquipmentType());
                    if (equipment.getBrand() != null) existing.setBrand(equipment.getBrand());
                    if (equipment.getModel() != null) existing.setModel(equipment.getModel());
                    if (equipment.getSerialNumber() != null) existing.setSerialNumber(equipment.getSerialNumber());
                    if (equipment.getEntryDate() != null) existing.setEntryDate(equipment.getEntryDate());
                    if (equipment.getObservations() != null) existing.setObservations(equipment.getObservations());
                    if (equipment.getPcSpecs() != null) existing.setPcSpecs(equipment.getPcSpecs());

                    Equipment updated = equipmentRepository.save(existing);
                    return convertToDTO(updated);
                });
    }

    public boolean deleteEquipment(String id) {
        if (equipmentRepository.existsById(id)) {
            equipmentRepository.deleteById(id);
            return true;
        }
        return false;
    }

    private EquipmentDTO convertToDTO(Equipment equipment) {
        EquipmentDTO.PcSpecsDTO specsDTO = null;
        if (equipment.getPcSpecs() != null) {
            specsDTO = EquipmentDTO.PcSpecsDTO.builder()
                    .processor(equipment.getPcSpecs().getProcessor())
                    .ram(equipment.getPcSpecs().getRam())
                    .hardDrive(equipment.getPcSpecs().getHardDrive())
                    .ssd(equipment.getPcSpecs().getSsd())
                    .graphicsCard(equipment.getPcSpecs().getGraphicsCard())
                    .motherboard(equipment.getPcSpecs().getMotherboard())
                    .powerSupply(equipment.getPcSpecs().getPowerSupply())
                    .operatingSystem(equipment.getPcSpecs().getOperatingSystem())
                    .other(equipment.getPcSpecs().getOther())
                    .build();
        }

        return EquipmentDTO.builder()
                .id(equipment.getId())
                .tagNumber(equipment.getTagNumber())
                .equipmentType(equipment.getEquipmentType())
                .brand(equipment.getBrand())
                .model(equipment.getModel())
                .serialNumber(equipment.getSerialNumber())
                .entryDate(equipment.getEntryDate() != null ? equipment.getEntryDate().toString() : null)
                .observations(equipment.getObservations())
                .pcSpecs(specsDTO)
                .build();
    }
}

