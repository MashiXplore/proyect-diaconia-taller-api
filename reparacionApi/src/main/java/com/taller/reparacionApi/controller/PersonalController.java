package com.taller.reparacionApi.controller;

import com.taller.reparacionApi.dto.PersonalDTO;
import com.taller.reparacionApi.model.Personal;
import com.taller.reparacionApi.service.PersonalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/personals")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class PersonalController {

    private final PersonalService personalService;

    @GetMapping
    public ResponseEntity<List<PersonalDTO>> getAllPersonals() {
        List<PersonalDTO> personals = personalService.getAllPersonals();
        return ResponseEntity.ok(personals);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonalDTO> getPersonalById(@PathVariable String id) {
        return personalService.getPersonalById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/fullname/{fullName}")
    public ResponseEntity<PersonalDTO> getPersonalByFullName(@PathVariable String fullName) {
        return personalService.getPersonalByFullName(fullName)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/agency/{agencyName}")
    public ResponseEntity<List<PersonalDTO>> getPersonalsByAgencyName(@PathVariable String agencyName) {
        List<PersonalDTO> personals = personalService.getPersonalsByAgencyName(agencyName);
        return ResponseEntity.ok(personals);
    }

    @PostMapping
    public ResponseEntity<?> createPersonal(@RequestBody Personal personal) {
        try {
            PersonalDTO created = personalService.createPersonal(personal);
            return ResponseEntity.status(HttpStatus.CREATED).body(created);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updatePersonal(@PathVariable String id, @RequestBody Personal personal) {
        return personalService.updatePersonal(id, personal)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePersonal(@PathVariable String id) {
        if (personalService.deletePersonal(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
