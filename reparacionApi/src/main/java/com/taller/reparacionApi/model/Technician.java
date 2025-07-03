package com.taller.reparacionApi.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "technicians")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Technician {

    @Id
    private String id;

    private String username;
    private String gmail;
    private String password;
    private String role;
    private String status;
    private Profile profile;
    private String employeeId;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Profile {
        private String firstName;
        private String lastName;
        private String phone;
        private String address;
        private String avatar;
    }
}
