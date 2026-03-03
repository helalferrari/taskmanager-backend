package com.helalferrari.taskmanager.dto;

import lombok.Data;
import java.util.UUID;

@Data
public class TaskPatchDto {
    private UUID userId;
}
