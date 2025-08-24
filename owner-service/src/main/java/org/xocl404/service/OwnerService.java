package org.xocl404.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.xocl404.OwnerDto;
import org.xocl404.PageDto;
import org.xocl404.PageableDto;
import org.xocl404.entity.Owner;
import org.xocl404.mapper.OwnerMapper;
import org.xocl404.repository.OwnerRepository;

import java.time.LocalDate;


@Service
@RequiredArgsConstructor
public class OwnerService {
    private final OwnerRepository ownerRepository;

    private final OwnerMapper ownerMapper;

    @Transactional
    public OwnerDto save(OwnerDto dto) {
        return ownerMapper.toDto(ownerRepository.save(ownerMapper.toEntity(dto)));
    }

    @Transactional
    public void deleteById(long id) {
        ownerRepository.deleteById(id);
    }

    @Transactional
    public void deleteAll() {
        ownerRepository.deleteAll();
    }

    @Transactional
    public OwnerDto update(OwnerDto dto) {
        Owner oldOwner = ownerRepository.findById(dto.getId())
                .orElseThrow(() -> new EntityNotFoundException("Owner not found with id: " + dto.getId()));
        if (dto.getPetIds() != oldOwner.getPetIds()) {
            throw new IllegalArgumentException("You can not change pet ids");
        }

        return ownerMapper.toDto(ownerRepository.save(ownerMapper.toEntity(dto)));
    }

    @Transactional(readOnly = true)
    public OwnerDto getById(long id) {
        return ownerRepository.findById(id)
                .map(ownerMapper::toDto)
                .orElseThrow(() -> new EntityNotFoundException("Owner not found with id: " + id));
    }

    @Transactional(readOnly = true)
    public PageDto<OwnerDto> getByName(String name, PageableDto pageableDto) {
        return PageDto.fromPage(ownerRepository.findByName(name, pageableDto.toPageable()).map(ownerMapper::toDto));
    }

    @Transactional(readOnly = true)
    public PageDto<OwnerDto> getByBirthDateBetween(LocalDate startDate, LocalDate endDate, PageableDto pageableDto) {
        return PageDto.fromPage(ownerRepository.findByBirthDateBetween(startDate, endDate, pageableDto.toPageable())
                .map(ownerMapper::toDto));
    }

    @Transactional(readOnly = true)
    public PageDto<OwnerDto> getAll(PageableDto pageableDto) {
        return PageDto.fromPage(ownerRepository.findAll(pageableDto.toPageable())
                .map(ownerMapper::toDto));
    }
}
