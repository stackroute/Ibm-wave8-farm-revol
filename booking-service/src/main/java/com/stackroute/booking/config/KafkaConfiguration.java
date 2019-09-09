package com.stackroute.booking.config;

import com.stackroute.booking.model.Consumer;
import com.stackroute.booking.model.Crop;
import com.stackroute.booking.model.Farmer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.*;
import org.springframework.kafka.support.serializer.*;
import java.util.HashMap;
import java.util.Map;

@EnableKafka
@Configuration
public class KafkaConfiguration {

    @Bean
    public ProducerFactory<String, String> producerFactory()
    {
        Map<String,Object> config = new HashMap<>();
        config.put(JsonSerializer.ADD_TYPE_INFO_HEADERS, false);
        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"localhost:9092");
        config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return new DefaultKafkaProducerFactory<>(config);
    }

    @Bean
    public KafkaTemplate<String, String> kafkaTemplate()
    {
        return new KafkaTemplate<>(producerFactory());
    }

    @Bean
    public ProducerFactory<String, Farmer> producerFactoryFarmer()
    {
        Map<String,Object> config = new HashMap<>();
        config.put(JsonSerializer.ADD_TYPE_INFO_HEADERS, false);
        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"localhost:9092");
        config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        return new DefaultKafkaProducerFactory<>(config);
    }
    @Bean
    public KafkaTemplate<String, Farmer> kafkaTemplateLand()
    {
        return new KafkaTemplate<>(producerFactoryFarmer());
    }

    @Bean
    public ProducerFactory<String, Consumer> producerFactoryConsumer()
    {
        Map<String,Object> config = new HashMap<>();
        config.put(JsonSerializer.ADD_TYPE_INFO_HEADERS, false);
        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"localhost:9092");
        config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return new DefaultKafkaProducerFactory<>(config);
    }
    @Bean
    public KafkaTemplate<String, Consumer> kafkaTemplateConsumer()
    {
        return new KafkaTemplate<>(producerFactoryConsumer());
    }
    @Bean
    public ConsumerFactory<String, Consumer> consumerFactoryConsumer() {
        Map<String, Object> config = new HashMap<>();
        JsonDeserializer<Consumer> deserializer = new JsonDeserializer<>(Consumer.class);
        deserializer.setRemoveTypeHeaders(false);
        deserializer.addTrustedPackages("*");
        deserializer.setUseTypeMapperForKey(true);

        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        config.put(ConsumerConfig.GROUP_ID_CONFIG, "group_consumer");
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, deserializer);
        return new DefaultKafkaConsumerFactory<>(config, new StringDeserializer(),deserializer);
    }
    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Consumer> kafkaListenerContainerFactoryConsumer() {
        ConcurrentKafkaListenerContainerFactory<String, Consumer> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactoryConsumer());
        return factory;
    }

    @Bean
    public ConsumerFactory<String, Crop> consumerFactoryCrop() {
        Map<String, Object> config = new HashMap<>();
        JsonDeserializer<Crop> deserializer = new JsonDeserializer<>(Crop.class);
        deserializer.setRemoveTypeHeaders(false);
        deserializer.addTrustedPackages("*");
        deserializer.setUseTypeMapperForKey(true);

        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        config.put(ConsumerConfig.GROUP_ID_CONFIG, "group_crop");
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, deserializer);
        return new DefaultKafkaConsumerFactory<>(config, new StringDeserializer(),deserializer);
    }
    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Crop> kafkaListenerContainerFactoryCrop() {
        ConcurrentKafkaListenerContainerFactory<String, Crop> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactoryCrop());
        return factory;
    }

    @Bean
    public ConsumerFactory<String, Farmer> consumerFactoryFarmer() {
        Map<String, Object> config = new HashMap<>();
        JsonDeserializer<Farmer> deserializer = new JsonDeserializer<>(Farmer.class);
        deserializer.setRemoveTypeHeaders(false);
        deserializer.addTrustedPackages("*");
        deserializer.setUseTypeMapperForKey(true);

        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        config.put(ConsumerConfig.GROUP_ID_CONFIG, "group_farmer");
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, deserializer);
        return new DefaultKafkaConsumerFactory<>(config, new StringDeserializer(),deserializer);
    }
    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Farmer> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, Farmer> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactoryFarmer());
        return factory;
    }
}
