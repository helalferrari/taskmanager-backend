package com.helalferrari.taskmanager.dto;

import lombok.Data;
import java.util.UUID;

@Data
public class TaskCreateDto {
    private String title;
    private UUID userId;
}
