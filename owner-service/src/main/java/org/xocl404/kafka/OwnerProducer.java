package org.xocl404.kafka;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.xocl404.OwnerDto;
import org.xocl404.PageDto;

@Component
@RequiredArgsConstructor
public class OwnerProducer {
    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void sendDto(OwnerDto dto) {
        kafkaTemplate.send("owner_response", dto);
    }

    public void sendPageDto(PageDto<OwnerDto> pageDto) {
        kafkaTemplate.send("owner_response_page", pageDto);
    }
}
