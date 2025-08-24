package org.xocl404.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.*;

@Entity
@Table(name = "owners")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Owner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Builder.Default
    @Column(name = "pet_ids")
    private List<Long> petIds = new ArrayList<>();

    public void addPet(Long petId) {
        petIds.add(petId);
    }

    public void removePet(Long petId) {
        petIds.remove(petId);
    }
}