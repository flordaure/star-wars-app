package com.conexaproject.star_wars_app.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ErrorResponse {

    @Schema(description = "Código del error", example = "400003")
    private Integer code;
    @Schema(description = "Mensaje del error", example = "The title parameter is required")
    private String description;
    @Schema(description = "Fecha y hora del error", example = "13-01-2025 05:29:41")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime timestamp;

    public ErrorResponse(Integer code, String description) {
        this.code = code;
        this.description = description;
        this.timestamp = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return "ErrorResponse{" +
                "code=" + code +
                ", description='" + description + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}
