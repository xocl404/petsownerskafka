package org.xocl404.kafka;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.xocl404.*;
import org.xocl404.service.PetService;

@Component
@RequiredArgsConstructor
public class PetConsumer {
    private final PetProducer petProducer;

    private final PetService petService;

    @KafkaListener(topics = "create_pet", groupId = "pet-service")
    public void createPet(PetDto dto) {
        petProducer.sendDto(petService.save(dto));
    }

    @KafkaListener(topics = "update_pet", groupId = "pet-service")
    public void updatePet(PetDto dto) {
        petProducer.sendDto(petService.update(dto));
    }

    @KafkaListener(topics = "delete_pet_by_id", groupId = "pet-service")
    public void deletePetById(Long id) {
        petService.deleteById(id);
    }

    @KafkaListener(topics = "delete_all_pets", groupId = "pet-service")
    public void deleteAllPets(Long l) {
        petService.deleteAll();
    }


    @KafkaListener(topics = "get_pet_by_id", groupId = "pet-service")
    public void getPetById(Long id) {
        petProducer.sendDto(petService.getById(id));
    }

    @KafkaListener(topics = "get_pet_by_name", groupId = "pet-service")
    public void getPetByName(StringDto dto) {
        petProducer.sendPageDto(petService.getByName(dto.getString(), dto.getPageableDto()));
    }

    @KafkaListener(topics = "get_pet_by_breed", groupId = "pet-service")
    public void getPetByBreed(StringDto dto) {
        petProducer.sendPageDto(petService.getByBreed(dto.getString(), dto.getPageableDto()));
    }

    @KafkaListener(topics = "get_pet_by_color", groupId = "pet-service")
    public void getPetByColor(ColorDto dto) {
        petProducer.sendPageDto(petService.getByColor(dto.getColor(), dto.getPageableDto()));
    }

    @KafkaListener(topics = "get_pet_by_birthdate_between", groupId = "pet-service")
    public void getPetByBirthDateBetween(BirthDateDto dto) {
        petProducer.sendPageDto(
                petService.getByBirthDateBetween(dto.getStartDate(), dto.getEndDate(), dto.getPageableDto()));
    }

    @KafkaListener(topics = "get_all_pets", groupId = "pet-service")
    public void getAllPets(PageableDto pageableDto) {
        petProducer.sendPageDto(petService.getAll(pageableDto));
    }
}

