package org.xocl404.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {

    @Bean
    public NewTopic createOwnerTopic() {
        return TopicBuilder.name("create_owner")
                .partitions(1)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic updateOwnerTopic() {
        return TopicBuilder.name("update_owner")
                .partitions(1)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic deleteOwnerByIdTopic() {
        return TopicBuilder.name("delete_owner_by_id")
                .partitions(1)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic deleteAllOwnersTopic() {
        return TopicBuilder.name("delete_all_owners")
                .partitions(1)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic getOwnerByIdTopic() {
        return TopicBuilder.name("get_owner_by_id")
                .partitions(1)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic getOwnerByNameTopic() {
        return TopicBuilder.name("get_owner_by_name")
                .partitions(1)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic getOwnerByBirthDateBetween() {
        return TopicBuilder.name("get_owner_by_birthdate_between")
                .partitions(1)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic getAllOwnersTopic() {
        return TopicBuilder.name("get_all_owners")
                .partitions(1)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic createPetTopic() {
        return TopicBuilder.name("create_pet")
                .partitions(1)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic updatePetTopic() {
        return TopicBuilder.name("update_pet")
                .partitions(1)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic deletePetByIdTopic() {
        return TopicBuilder.name("delete_pet_by_id")
                .partitions(1)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic deleteAllPetsTopic() {
        return TopicBuilder.name("delete_all_pets")
                .partitions(1)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic getPetByIdTopic() {
        return TopicBuilder.name("get_pet_by_id")
                .partitions(1)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic getPetByNameTopic() {
        return TopicBuilder.name("get_pet_by_name")
                .partitions(1)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic getPetByColorTopic() {
        return TopicBuilder.name("get_pet_by_color")
                .partitions(1)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic getPetByBreedTopic() {
        return TopicBuilder.name("get_pet_by_breed")
                .partitions(1)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic getPetByBirthDateBetween() {
        return TopicBuilder.name("get_pet_by_birthdate_between")
                .partitions(1)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic getAllPetsTopic() {
        return TopicBuilder.name("get_all_pets")
                .partitions(1)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic ownerResponse() {
        return TopicBuilder.name("owner_response")
                .partitions(1)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic petResponse() {
        return TopicBuilder.name("pet_response")
                .partitions(1)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic ownerResponsePage() {
        return TopicBuilder.name("owner_response_page")
                .partitions(1)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic petResponsePage() {
        return TopicBuilder.name("pet_response_page")
                .partitions(1)
                .replicas(1)
                .build();
    }
}
