package com.taller.reparacionApi.dto;


import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TechnicianDTO {

    private String id;
    private String username;
    private String gmail;
    private String role;
    private String status;
    private String employeeId;
    private ProfileDTO profile;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class ProfileDTO {
        private String firstName;
        private String lastName;
        private String phone;
        private String address;
        private String avatar;
    }
}