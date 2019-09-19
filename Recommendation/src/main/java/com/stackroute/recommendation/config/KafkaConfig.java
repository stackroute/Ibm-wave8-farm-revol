package com.stackroute.recommendation.config;

import com.stackroute.recommendation.model.*;
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
import java.util.List;
import java.util.Map;

@EnableKafka
@Configuration

public class KafkaConfig {

    @Bean
    public ConsumerFactory<String, ConsumerDTORecommendation> consumerFactory() {
        Map<String, Object> config = new HashMap<>();
        JsonDeserializer<ConsumerDTORecommendation> deserializer = new JsonDeserializer<>(ConsumerDTORecommendation.class);
        deserializer.setRemoveTypeHeaders(false);
        deserializer.addTrustedPackages("*");
        deserializer.setUseTypeMapperForKey(true);

        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "kafka.demo:9092");
        config.put(ConsumerConfig.GROUP_ID_CONFIG, "group_consumer");
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, deserializer);
        return new DefaultKafkaConsumerFactory<>(config, new StringDeserializer(),deserializer);
    }
    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, ConsumerDTORecommendation> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, ConsumerDTORecommendation> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }

    @Bean
    public ConsumerFactory<String, FarmerDTORecommendation> farmerConsumerFactory() {
        Map<String, Object> config = new HashMap<>();
        JsonDeserializer<FarmerDTORecommendation> deserializer = new JsonDeserializer<>(FarmerDTORecommendation.class);
        deserializer.setRemoveTypeHeaders(false);
        deserializer.addTrustedPackages("*");
        deserializer.setUseTypeMapperForKey(true);

        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "kafka.demo:9092");
        config.put(ConsumerConfig.GROUP_ID_CONFIG, "group_farmer");
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, deserializer);
        return new DefaultKafkaConsumerFactory<>(config, new StringDeserializer(),deserializer);
    }
    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, FarmerDTORecommendation> kafkaListenerContainerFactoryFarmer() {
        ConcurrentKafkaListenerContainerFactory<String, FarmerDTORecommendation> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(farmerConsumerFactory());
        return factory;
    }


    @Bean
    public ConsumerFactory<String, BookingDTORecommendation> bookingConsumerFactory() {
        Map<String, Object> config = new HashMap<>();
        JsonDeserializer<BookingDTORecommendation> deserializer = new JsonDeserializer<>(BookingDTORecommendation.class);
        deserializer.setRemoveTypeHeaders(false);
        deserializer.addTrustedPackages("*");
        deserializer.setUseTypeMapperForKey(true);

        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "kafka.demo:9092");
        config.put(ConsumerConfig.GROUP_ID_CONFIG, "booking");
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, deserializer);
        return new DefaultKafkaConsumerFactory<>(config, new StringDeserializer(),deserializer);
    }
    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, BookingDTORecommendation> kafkaListenerContainerFactoryBooking() {
        ConcurrentKafkaListenerContainerFactory<String, BookingDTORecommendation> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(bookingConsumerFactory());
        return factory;
    }

    @Bean
    public ConsumerFactory<String, String> consumerFactory1() {
        Map<String, Object> config = new HashMap<>();
        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "kafka.demo:9092");
        config.put(ConsumerConfig.GROUP_ID_CONFIG, "group_email");
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        return new DefaultKafkaConsumerFactory<>(config);
    }
    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactoryRecommendConsumer() {
        ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory();
        factory.setConsumerFactory(consumerFactory1());
        return factory;
    }

    @Bean
    public ConsumerFactory<String, RecommendedLandsFromConsumer> farmerConsumerFactory1() {
        Map<String, Object> config = new HashMap<>();
        JsonDeserializer<RecommendedLandsFromConsumer> deserializer = new JsonDeserializer<>(RecommendedLandsFromConsumer.class);
        deserializer.setRemoveTypeHeaders(false);
        deserializer.addTrustedPackages("*");
        deserializer.setUseTypeMapperForKey(true);

        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "kafka.demo:9092");
        config.put(ConsumerConfig.GROUP_ID_CONFIG, "recommendations");
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, deserializer);
        return new DefaultKafkaConsumerFactory<>(config, new StringDeserializer(),deserializer);
    }
    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, RecommendedLandsFromConsumer> kafkaListenerContainerFactoryRecommendedLandsToConsumer() {
        ConcurrentKafkaListenerContainerFactory<String, RecommendedLandsFromConsumer> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(farmerConsumerFactory1());
        return factory;
    }





        @Bean
    public ProducerFactory<String, String> producerFactoryLandsOfFarmer(){
        Map<String, Object> config = new HashMap<>();

        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "kafka.demo:9092");
        config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);

        return new DefaultKafkaProducerFactory<>(config);
    }

    @Bean
    public KafkaTemplate<String, String>  kafkaTemplateLandsOfFarmer() {
        return new KafkaTemplate<>(producerFactoryLandsOfFarmer());
    }

    @Bean
    public ProducerFactory<String, Farmers> producerFactoryCo(){
        Map<String, Object> config = new HashMap<>();

        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "kafka.demo:9092");
        config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);

        return new DefaultKafkaProducerFactory<>(config);
    }

    @Bean
    public KafkaTemplate<String,Farmers> kafkaTemplateRecommendedFarmers() {
        return new KafkaTemplate<>(producerFactoryCo());
    }

}