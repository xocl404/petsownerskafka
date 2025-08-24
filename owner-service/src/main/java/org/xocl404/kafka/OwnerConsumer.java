package org.xocl404.kafka;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.xocl404.BirthDateDto;
import org.xocl404.OwnerDto;
import org.xocl404.PageableDto;
import org.xocl404.StringDto;
import org.xocl404.service.OwnerService;

@Component
@RequiredArgsConstructor
public class OwnerConsumer {
    private final OwnerProducer ownerProducer;

    private final OwnerService ownerService;

    @KafkaListener(topics = "create_owner", groupId = "owner-service")
    public void createOwner(OwnerDto dto) {
        ownerProducer.sendDto(ownerService.save(dto));
    }

    @KafkaListener(topics = "update_owner", groupId = "owner-service")
    public void updateOwner(OwnerDto dto) {
        ownerProducer.sendDto(ownerService.update(dto));
    }

    @KafkaListener(topics = "delete_owner_by_id", groupId = "owner-service")
    public void deleteOwnerById(Long id) {
        ownerService.deleteById(id);
    }

    @KafkaListener(topics = "delete_all_owners", groupId = "owner-service")
    public void deleteAllOwners(Long l) {
        ownerService.deleteAll();
    }


    @KafkaListener(topics = "get_owner_by_id", groupId = "owner-service")
    public void getOwnerById(Long id) {
        ownerProducer.sendDto(ownerService.getById(id));
    }

    @KafkaListener(topics = "get_owner_by_name", groupId = "owner-service")
    public void getOwnerByName(StringDto dto) {
        ownerProducer.sendPageDto(ownerService.getByName(dto.getString(), dto.getPageableDto()));
    }

    @KafkaListener(topics = "get_owner_by_birthdate_between", groupId = "owner-service")
    public void getOwnerByBirthDateBetween(BirthDateDto dto) {
        ownerProducer.sendPageDto(
                ownerService.getByBirthDateBetween(dto.getStartDate(), dto.getEndDate(), dto.getPageableDto()));
    }

    @KafkaListener(topics = "get_all_owners", groupId = "owner-service")
    public void getAllOwners(PageableDto pageableDto) {
        ownerProducer.sendPageDto(ownerService.getAll(pageableDto));
    }
}

