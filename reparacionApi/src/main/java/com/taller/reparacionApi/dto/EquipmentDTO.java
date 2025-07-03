package com.taller.reparacionApi.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EquipmentDTO {

    private String id;
    private String tagNumber;
    private String equipmentType;
    private String brand;
    private String model;
    private String serialNumber;
    private String entryDate;
    private String observations;
    private PcSpecsDTO pcSpecs;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class PcSpecsDTO {
        private String processor;
        private String ram;
        private String hardDrive;
        private String ssd;
        private String graphicsCard;
        private String motherboard;
        private String powerSupply;
        private String operatingSystem;
        private String other;
    }
}
