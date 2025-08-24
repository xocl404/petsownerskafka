package org.xocl404.kafka;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.xocl404.PageDto;
import org.xocl404.PetDto;

@Component
@RequiredArgsConstructor
public class PetProducer {
    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void sendDto(PetDto dto) {
        kafkaTemplate.send("pet_response", dto);
    }

    public void sendPageDto(PageDto<PetDto> pageDto) {
        kafkaTemplate.send("pet_response_page", pageDto);
    }
}
