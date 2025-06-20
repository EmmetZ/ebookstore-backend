package com.sjtu.se2321.backend.config;

import org.springframework.format.FormatterRegistry;
import org.springframework.lang.NonNull;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.sjtu.se2321.backend.converter.StringToRoleConverter;

public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addFormatters(@NonNull FormatterRegistry registry) {
        registry.addConverter(new StringToRoleConverter());
    }

}
