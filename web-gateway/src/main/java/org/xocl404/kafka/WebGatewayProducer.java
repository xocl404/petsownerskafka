package org.xocl404.kafka;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.xocl404.PageableDto;

@Component
@RequiredArgsConstructor
public class WebGatewayProducer {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void sendDto(String topic, Object dto) {
        kafkaTemplate.send(topic, dto);
    }

    public void sendId(String topic, Long id) {
        kafkaTemplate.send(topic, id);
    }

    public void sendPageable(String topic, PageableDto pageableDto) {
        kafkaTemplate.send(topic, pageableDto);
    }

    public void sendNothing(String topic) {
        kafkaTemplate.send(topic, -1);
    }
}
