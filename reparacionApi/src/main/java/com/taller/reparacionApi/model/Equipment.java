package com.taller.reparacionApi.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document(collection = "equipments")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Equipment {

    @Id
    private String id;

    private String tagNumber;
    private String equipmentType;
    private String brand;
    private String model;
    private String serialNumber;
    private LocalDate entryDate;
    private String observations;

    private PcSpecs pcSpecs;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class PcSpecs {
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
