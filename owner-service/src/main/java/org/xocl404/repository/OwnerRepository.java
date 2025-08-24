package org.xocl404.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.xocl404.entity.Owner;

import java.time.LocalDate;

@Repository
public interface OwnerRepository extends JpaRepository<Owner, Long> {
    Page<Owner> findByName(String name, Pageable pageable);

    @Query("SELECT o FROM Owner o WHERE  o.birthDate BETWEEN :startDate AND :endDate")
    Page<Owner> findByBirthDateBetween(
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate,
            Pageable pageable);
}
