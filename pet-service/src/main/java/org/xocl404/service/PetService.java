package org.xocl404.service;


import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.xocl404.Color;
import org.xocl404.PageDto;
import org.xocl404.PageableDto;
import org.xocl404.PetDto;
import org.xocl404.entity.Pet;
import org.xocl404.mapper.PetMapper;
import org.xocl404.repository.PetRepository;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class PetService {
    private final PetRepository petRepository;
    private final PetMapper petMapper;

    @Transactional
    public PetDto save(PetDto dto) {
        Pet pet = petRepository.save(petMapper.toEntity(dto));
        return petMapper.toDto(pet);
    }

    @Transactional
    public void deleteById(long id) {
        Pet pet = petRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Pet not found"));
        petRepository.deleteById(id);
    }

    @Transactional
    public void deleteAll() {
        petRepository.deleteAll();
    }

    @Transactional
    public PetDto update(PetDto dto) {
        Pet oldPet = petRepository.findById(dto.getId())
                .orElseThrow(() -> new EntityNotFoundException("Pet not found with id: " + dto.getId()));
        if (oldPet.getOwnerId() != dto.getOwnerId()) {
            throw new IllegalArgumentException("You can not change owner id");
        }
        return petMapper.toDto(petRepository.save(petMapper.toEntity(dto)));
    }

    @Transactional(readOnly = true)
    public PetDto getById(long id) {
        return petRepository.findById(id)
                .map(petMapper::toDto)
                .orElseThrow(() -> new EntityNotFoundException("Pet not found with id: " + id));
    }

    @Transactional(readOnly = true)
    public PageDto<PetDto> getByName(String name, PageableDto pageableDto) {
        return PageDto.fromPage(petRepository.findByName(name, pageableDto.toPageable())
                .map(petMapper::toDto));
    }

    @Transactional(readOnly = true)
    public PageDto<PetDto> getByBreed(String breed, PageableDto pageableDto) {
        return PageDto.fromPage(petRepository.findByBreed(breed, pageableDto.toPageable())
                .map(petMapper::toDto));
    }

    @Transactional(readOnly = true)
    public PageDto<PetDto> getByColor(Color color, PageableDto pageableDto) {
        return PageDto.fromPage(petRepository.findByColor(color, pageableDto.toPageable())
                .map(petMapper::toDto));
    }

    @Transactional(readOnly = true)
    public PageDto<PetDto> getByBirthDateBetween(LocalDate startDate
            , LocalDate endDate, PageableDto pageableDto) {
        return PageDto.fromPage(petRepository.findByBirthDateBetween(startDate, endDate, pageableDto.toPageable())
                .map(petMapper::toDto));
    }

    @Transactional(readOnly = true)
    public PageDto<PetDto> getAll(PageableDto pageableDto) {
        return PageDto.fromPage(petRepository.findAll(pageableDto.toPageable())
                .map(petMapper::toDto));
    }
}
