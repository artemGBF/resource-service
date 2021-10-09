package ru.gbf.resourceserver.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;
import ru.gbf.resourceserver.dto.GoodDTO;
import ru.gbf.resourceserver.dto.emails.CreateOrderEmailDto;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class JmsConfig {

    @Bean
    public MessageConverter jsonMethodConverter() {
        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
        converter.setTargetType(MessageType.TEXT);
        converter.setTypeIdPropertyName("_type");

        Map<String, Class<?>> typeIdMappings = new HashMap<>();
        typeIdMappings.put("create-email-order", CreateOrderEmailDto.class);
        typeIdMappings.put("good", GoodDTO.class);
        converter.setTypeIdMappings(typeIdMappings);

        return converter;
    }


}
