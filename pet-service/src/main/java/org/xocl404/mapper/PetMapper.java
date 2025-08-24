package org.xocl404.mapper;

import org.springframework.stereotype.Component;
import org.xocl404.PetDto;
import org.xocl404.entity.Pet;

@Component
public class PetMapper {

    public Pet toEntity(PetDto dto) {
        return Pet.builder()
                .id(dto.getId())
                .name(dto.getName())
                .birthDate(dto.getBirthDate())
                .breed(dto.getBreed())
                .color(dto.getColor())
                .ownerId(dto.getOwnerId())
                .friendIds(dto.getFriendIds())
                .build();
    }

    public PetDto toDto(Pet entity) {
        return PetDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .birthDate(entity.getBirthDate())
                .breed(entity.getBreed())
                .color(entity.getColor())
                .ownerId(entity.getOwnerId())
                .friendIds(entity.getFriendIds())
                .build();
    }
}