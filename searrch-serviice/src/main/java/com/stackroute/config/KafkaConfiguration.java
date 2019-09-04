package com.stackroute.config;

import com.stackroute.domain.Farmer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@EnableKafka
@Configuration
public class KafkaConfiguration {

    @Bean
    public ConsumerFactory<String, Farmer> farmerFactory() {
        Map<String, Object> config = new HashMap<>();
        JsonDeserializer<Farmer> deserializer = new JsonDeserializer<>(Farmer.class);
        deserializer.setRemoveTypeHeaders(false);
        deserializer.addTrustedPackages("*");
        deserializer.setUseTypeMapperForKey(true);

        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        config.put(ConsumerConfig.GROUP_ID_CONFIG, "group_farmer");
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, deserializer);
//        config.put(JsonDeserializer.TRUSTED_PACKAGES, "com.stackroute.booking.model");
        return new DefaultKafkaConsumerFactory<>(config, new StringDeserializer(),deserializer);
    }
    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Farmer> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, Farmer> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(farmerFactory());
        return factory;
    }

}
