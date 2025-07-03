package com.taller.reparacionApi.repository;

import com.taller.reparacionApi.model.Equipment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EquipmentRepository extends MongoRepository<Equipment, String> {

    Optional<Equipment> findByTagNumber(String tagNumber);

    Optional<Equipment> findBySerialNumber(String serialNumber);

    List<Equipment> findByEquipmentType(String equipmentType);

    List<Equipment> findByBrand(String brand);

    List<Equipment> findByModel(String model);

    boolean existsByTagNumber(String tagNumber);

    boolean existsBySerialNumber(String serialNumber);
}

