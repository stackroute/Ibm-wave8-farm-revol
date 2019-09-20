package com.stackroute.config;
import com.stackroute.domain.Crop;
import com.stackroute.domain.Farmer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;

import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.*;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;
@EnableKafka
@Configuration
public class KafkaConfiguration {
    @Bean
    public ConsumerFactory<String, Crop> cropFactory() {
        Map<String, Object> config = new HashMap<>();
        JsonDeserializer<Crop> deserializer = new JsonDeserializer<>(Crop.class);
        deserializer.setRemoveTypeHeaders(false);
        deserializer.addTrustedPackages("*");
        deserializer.setUseTypeMapperForKey(true);
        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "172.23.238.238:9092");
        config.put(ConsumerConfig.GROUP_ID_CONFIG, "group_crop");
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, deserializer);
//        config.put(JsonDeserializer.TRUSTED_PACKAGES, "com.stackroute.booking.model");
        return new DefaultKafkaConsumerFactory<>(config, new StringDeserializer(),deserializer);
    }
    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Crop> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, Crop> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(cropFactory());
        return factory;
    }
    @Bean
    public ProducerFactory<String, Crop> producerFactoryCrop(){
        Map<String, Object> config = new HashMap<>();
        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return new DefaultKafkaProducerFactory<>(config);
    }
    @Bean
    public KafkaTemplate<String, Crop> kafkaTemplateCrop() {
        return new KafkaTemplate<>(producerFactoryCrop());
    }
}