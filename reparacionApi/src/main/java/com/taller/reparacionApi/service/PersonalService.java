package com.taller.reparacionApi.service;

import com.taller.reparacionApi.dto.PersonalDTO;
import com.taller.reparacionApi.model.Personal;
import com.taller.reparacionApi.repository.PersonalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PersonalService {

    private final PersonalRepository personalRepository;

    public List<PersonalDTO> getAllPersonals() {
        return personalRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public Optional<PersonalDTO> getPersonalById(String id) {
        return personalRepository.findById(id)
                .map(this::convertToDTO);
    }

    public Optional<PersonalDTO> getPersonalByFullName(String fullName) {
        return personalRepository.findByFullName(fullName)
                .map(this::convertToDTO);
    }

    public List<PersonalDTO> getPersonalsByAgencyName(String agencyName) {
        return personalRepository.findByAgencies_Name(agencyName)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public PersonalDTO createPersonal(Personal personal) {
        if (personalRepository.existsByFullName(personal.getFullName())) {
            throw new RuntimeException("Personal with that name already exists");
        }
        Personal saved = personalRepository.save(personal);
        return convertToDTO(saved);
    }

    public Optional<PersonalDTO> updatePersonal(String id, Personal personal) {
        return personalRepository.findById(id)
                .map(existing -> {
                    if (personal.getFullName() != null) existing.setFullName(personal.getFullName());
                    if (personal.getAgencies() != null) existing.setAgencies(personal.getAgencies());
                    if (personal.getPositionHistory() != null) existing.setPositionHistory(personal.getPositionHistory());
                    Personal updated = personalRepository.save(existing);
                    return convertToDTO(updated);
                });
    }

    public boolean deletePersonal(String id) {
        if (personalRepository.existsById(id)) {
            personalRepository.deleteById(id);
            return true;
        }
        return false;
    }

    private PersonalDTO convertToDTO(Personal personal) {
        List<PersonalDTO.AgencyDTO> agencies = null;
        if (personal.getAgencies() != null) {
            agencies = personal.getAgencies().stream()
                    .map(a -> PersonalDTO.AgencyDTO.builder()
                            .name(a.getName())
                            .city(a.getCity())
                            .address(a.getAddress())
                            .build())
                    .collect(Collectors.toList());
        }

        List<PersonalDTO.PositionHistoryDTO> positions = null;
        if (personal.getPositionHistory() != null) {
            positions = personal.getPositionHistory().stream()
                    .map(p -> PersonalDTO.PositionHistoryDTO.builder()
                            .positionTitle(p.getPositionTitle())
                            .startDate(p.getStartDate())
                            .endDate(p.getEndDate())
                            .notes(p.getNotes())
                            .build())
                    .collect(Collectors.toList());
        }

        return PersonalDTO.builder()
                .id(personal.getId())
                .fullName(personal.getFullName())
                .agencies(agencies)
                .positionHistory(positions)
                .build();
    }
}
