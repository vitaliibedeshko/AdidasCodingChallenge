package com.adidas.public_service.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Map;

@Configuration
@ConfigurationProperties
@Getter
public class KeyValueApplicationConfiguration {

    private Map<String, String> keyValueMapping;

    private Map<String, List<String>> keyValueListMapping;

    public void setKeyValueMapping(@Value("${key-value-mapping}") Map<String, String> keyValueMapping) {
        this.keyValueMapping = keyValueMapping;
    }

    public void setKeyValueListMapping(@Value("${key-value-list-mapping}") Map<String, List<String>> keyValueListMapping) {
        this.keyValueListMapping = keyValueListMapping;
    }
}
