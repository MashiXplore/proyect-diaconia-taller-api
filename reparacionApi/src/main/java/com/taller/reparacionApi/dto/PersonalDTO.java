package com.taller.reparacionApi.dto;

import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PersonalDTO {

    private String id;
    private String fullName;
    private List<AgencyDTO> agencies;
    private List<PositionHistoryDTO> positionHistory;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class AgencyDTO {
        private String name;
        private String city;
        private String address;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class PositionHistoryDTO {
        private String positionTitle;
        private String startDate;
        private String endDate;
        private String notes;
    }
}
