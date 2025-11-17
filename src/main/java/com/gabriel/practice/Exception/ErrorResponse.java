package com.gabriel.practice.Exception;

import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/* Dto estándar de error  */
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@Builder
@Schema(description = "Estructura de respuesta para errores")
public class ErrorResponse {
    
    @Schema(description = "Código numérico HTTP", example = "400")
    private int status;
    @Schema(description = "Error HTTP", example = "Bad Request")
    private String error;
    @Schema(description = "Mensaje de error internacionalizado")
    private String message;
    @Schema(description = "Ruta del endpoint", example = "/api/v1/users")
    private String path;
    @Schema(description = "Código interno de error", example = "USER_ALREADY_EXISTS")
    private ErrorCode code;
    @Schema(description = "Timestamp ISO", example = "2025-01-14T22:23:12")
    private LocalDateTime timestamp;

}
