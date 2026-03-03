package com.helalferrari.taskmanager.dto;

import lombok.Data;
import java.util.UUID;

@Data
public class TaskUpdateDto {
    private UUID userId;
    private String title;
    private Boolean completed;
    private Boolean deleted;
}
