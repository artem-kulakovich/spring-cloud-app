package by.bntu.fitr.accountservice.config.kafka;

import by.bntu.fitr.accountservice.entity.dto.EmailDTO;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
@PropertySource("classpath:kafka.properties")
public class KafkaProducerConfig {
    @Value("${server}")
    private String kafkaServer;

    @Bean
    public Map<String, Object> producerConfigs() {
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaServer);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return props;
    }

    @Bean
    public ProducerFactory<String, EmailDTO> producerEmailFactory() {
        return new DefaultKafkaProducerFactory<>(producerConfigs());
    }

    @Bean(name = "emailKafkaTemplate")
    public KafkaTemplate<String, EmailDTO> emailKafkaTemplate() {
        return new KafkaTemplate<>(producerEmailFactory());
    }
}
