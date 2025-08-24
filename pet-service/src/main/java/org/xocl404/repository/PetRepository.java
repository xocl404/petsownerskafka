package org.xocl404.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.xocl404.Color;
import org.xocl404.entity.Pet;

import java.time.LocalDate;

@Repository
public interface PetRepository extends JpaRepository<Pet, Long> {
    Page<Pet> findByName(String name, Pageable pageable);

    Page<Pet> findByBreed(String breed, Pageable pageable);

    Page<Pet> findByColor(Color color, Pageable pageable);

    @Query("SELECT p FROM Pet p WHERE p.birthDate BETWEEN :startDate AND :endDate")
    Page<Pet> findByBirthDateBetween(
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate,
            Pageable pageable);
}