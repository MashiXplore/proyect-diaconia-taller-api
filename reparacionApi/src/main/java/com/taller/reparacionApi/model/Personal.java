package com.taller.reparacionApi.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "personals")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Personal {

    @Id
    private String id;

    private String fullName;

    private List<Agency> agencies;
    private List<PositionHistory> positionHistory;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Agency {
        private String name;
        private String city;
        private String address;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class PositionHistory {
        private String positionTitle;
        private String startDate;
        private String endDate;
        private String notes;
    }
}
