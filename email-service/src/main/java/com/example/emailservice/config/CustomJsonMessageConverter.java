package com.example.emailservice.config;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConversionException;

public class CustomJsonMessageConverter extends Jackson2JsonMessageConverter {
    public CustomJsonMessageConverter() {
        super();
    }

    @Override
    public Object fromMessage(Message message, Object conversionHint) throws MessageConversionException {
        MessageProperties properties = message.getMessageProperties();
        if (properties != null) {
            String typeIdHeader = (String) properties.getHeaders().get("__TypeId__");
            if (typeIdHeader != null) {
                if (typeIdHeader.equals("com.example.emailproducer.dto.EmailRequestDTO")) {
                    typeIdHeader = "com.example.emailservice.dto.EmailRequestDTO";
                    properties.getHeaders().put("__TypeId__", typeIdHeader);
                }
            }
        }
        return super.fromMessage(message, conversionHint);
    }
}