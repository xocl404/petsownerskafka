package org.xocl404.mapper;

import org.springframework.stereotype.Component;
import org.xocl404.OwnerDto;
import org.xocl404.entity.Owner;

@Component
public class OwnerMapper {
    public Owner toEntity(OwnerDto dto) {
        return Owner.builder()
                .id(dto.getId())
                .name(dto.getName())
                .birthDate(dto.getBirthDate())
                .petIds(dto.getPetIds())
                .build();
    }

    public OwnerDto toDto(Owner entity) {
        return OwnerDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .birthDate(entity.getBirthDate())
                .petIds(entity.getPetIds())
                .build();
    }
}