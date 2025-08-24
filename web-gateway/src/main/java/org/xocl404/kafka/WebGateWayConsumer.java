package org.xocl404.kafka;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.xocl404.OwnerDto;
import org.xocl404.PageDto;
import org.xocl404.PetDto;
import org.xocl404.service.ResponseStore;

import java.util.List;

@Component
@RequiredArgsConstructor
public class WebGateWayConsumer {
    private final ResponseStore responseStore;

    @KafkaListener(topics = "pet_response", groupId = "web-gateway")
    public void petResponse(PetDto dto) {
        responseStore.addPetResponse(dto);
    }

    @KafkaListener(topics = "pet_response_page", groupId = "web-gateway")
    public void petResponsePage(PageDto<PetDto> pageDto) {
        responseStore.addPetPageResponse(pageDto);
    }

    @KafkaListener(topics = "owner_response", groupId = "web-gateway")
    public void ownerResponse(OwnerDto dto) {
        responseStore.addOwnerResponse(dto);
    }

    @KafkaListener(topics = "owner_response_page", groupId = "web-gateway")
    public void ownerResponsePage(PageDto<OwnerDto> pageDto) {
        responseStore.addOwnerPageResponse(pageDto);
    }
}
