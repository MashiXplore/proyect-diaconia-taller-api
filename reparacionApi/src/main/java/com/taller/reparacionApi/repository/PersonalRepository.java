package com.taller.reparacionApi.repository;

import com.taller.reparacionApi.model.Personal;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonalRepository extends MongoRepository<Personal, String> {

    Optional<Personal> findByFullName(String fullName);

    List<Personal> findByAgencies_Name(String agencyName);

    boolean existsByFullName(String fullName);
}
