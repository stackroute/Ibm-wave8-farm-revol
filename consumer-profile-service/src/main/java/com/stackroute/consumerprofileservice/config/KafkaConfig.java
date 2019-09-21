package com.stackroute.consumerprofileservice.config;

import com.stackroute.consumerprofileservice.model.*;
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
public class KafkaConfig {

    @Bean
    public ProducerFactory<String, Consumer> producerFactoryConsumer(){
        Map<String, Object> config = new HashMap<>();

        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "172.23.239.116:9092");
        config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);

        return new DefaultKafkaProducerFactory<>(config);
    }

    @Bean
    public KafkaTemplate<String, Consumer> kafkaTemplateConsumer() {
        return new KafkaTemplate<>(producerFactoryConsumer());
    }

    @Bean
    public ProducerFactory<String, ConsumerDTORecommendation> producerFactoryCo(){
        Map<String, Object> config = new HashMap<>();

        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "172.23.239.116:9092");
        config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);

        return new DefaultKafkaProducerFactory<>(config);
    }

    @Bean
    public KafkaTemplate<String,ConsumerDTORecommendation> kafkaTemplate1() {
        return new KafkaTemplate<>(producerFactoryCo());
    }

    @Bean
    public ProducerFactory<String, String> producerFactoryConsumer1(){
        Map<String, Object> config = new HashMap<>();

        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "172.23.239.116:9092");
        config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);

        return new DefaultKafkaProducerFactory<>(config);
    }

    @Bean
    public KafkaTemplate<String, String> kafkaTemplateConsumer1() {
        return new KafkaTemplate<>(producerFactoryConsumer1());
    }

    @Bean
    public ProducerFactory<String, Land> producerFactoryLand(){
        Map<String, Object> config = new HashMap<>();

        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "172.23.239.116:9092");
        config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);

        return new DefaultKafkaProducerFactory<>(config);
    }

    @Bean
    public KafkaTemplate<String, Land> kafkaTemplateLand() {
        return new KafkaTemplate<>(producerFactoryLand());
    }


//    @Bean
//    public ProducerFactory<String, Crop> producerFactoryCrop()
//    {
//        Map<String, Object> config = new HashMap<>();
//        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
//        config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
//        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
//
//        return new DefaultKafkaProducerFactory<>(config);
//    }
//    @Bean
//    public KafkaTemplate<String, Crop> kafkaTemplateCrop()
//    {
//        return new KafkaTemplate<>(producerFactoryCrop());
//    }

    @Bean
    public ProducerFactory<String, String> producerFactory(){
        Map<String, Object> config = new HashMap<>();

        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "172.23.239.116:9092");
        config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);

        return new DefaultKafkaProducerFactory<>(config);
    }

    @Bean
    public KafkaTemplate<String, String> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }

    @Bean
    public ConsumerFactory<String, Crop> cropFactory() {
        Map<String, Object> config = new HashMap<>();
        JsonDeserializer<Crop> deserializer = new JsonDeserializer<>(Crop.class);
        deserializer.setRemoveTypeHeaders(false);
        deserializer.addTrustedPackages("*");
        deserializer.setUseTypeMapperForKey(true);

        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "172.23.239.116:9092");
        config.put(ConsumerConfig.GROUP_ID_CONFIG, "group_crop");
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, deserializer);
        return new DefaultKafkaConsumerFactory<>(config, new StringDeserializer(),deserializer);
    }
    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Crop> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, Crop> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(cropFactory());
        return factory;
    }
    @Bean
    public ConsumerFactory<String, Consumer> cropFactoryConsumer() {
        Map<String, Object> config = new HashMap<>();
        JsonDeserializer<Consumer> deserializer = new JsonDeserializer<>(Consumer.class);
        deserializer.setRemoveTypeHeaders(false);
        deserializer.addTrustedPackages("*");
        deserializer.setUseTypeMapperForKey(true);

        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "172.23.239.116:9092");
        config.put(ConsumerConfig.GROUP_ID_CONFIG, "group_consumer_booking");
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, deserializer);
        return new DefaultKafkaConsumerFactory<>(config, new StringDeserializer(),deserializer);
    }
    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Consumer> kafkaListenerContainerFactoryConsumer() {
        ConcurrentKafkaListenerContainerFactory<String, Consumer> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(cropFactoryConsumer());
        return factory;
    }



    @Bean
    public ConsumerFactory<String, Farmers> farmerConsumerFactory() {
        Map<String, Object> config = new HashMap<>();
        JsonDeserializer<Farmers> deserializer = new JsonDeserializer<>(Farmers.class);
        deserializer.setRemoveTypeHeaders(false);
        deserializer.addTrustedPackages("*");
        deserializer.setUseTypeMapperForKey(true);

        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "172.23.239.116:9092");
        config.put(ConsumerConfig.GROUP_ID_CONFIG, "recommendations");
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, deserializer);
        return new DefaultKafkaConsumerFactory<>(config, new StringDeserializer(),deserializer);
    }
    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Farmers> kafkaListenerContainerFactory1() {
        ConcurrentKafkaListenerContainerFactory<String, Farmers> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(farmerConsumerFactory());
        return factory;
    }


}

