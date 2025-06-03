package com.fullstackalex.TaskManagementSystem.config;

import com.fullstackalex.TaskManagementSystem.dto.TaskResponse;
import com.fullstackalex.TaskManagementSystem.model.Task;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        // Task to TaskResponse mapping configuration
        modelMapper.typeMap(Task.class, TaskResponse.class).addMappings(mapper -> {
            mapper.map(src -> src.getUser().getUsername(), TaskResponse::setCreatedBy);
            mapper.map(Task::getCreatedAt, TaskResponse::setCreatedAt);
            mapper.map(Task::getUpdatedAt, TaskResponse::setUpdatedAt);
        });

        return modelMapper;
    }
}
