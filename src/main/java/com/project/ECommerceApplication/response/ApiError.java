package com.project.ECommerceApplication.response;



import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ApiError {
    @JsonProperty("message")
    private String message;

    @JsonProperty("details")
    private String details;

    @JsonProperty("timestamp")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime timestamp;

    public ApiError(String message, String details, LocalDateTime timestamp) {
        this.message = message;
        this.details = details;
        this.timestamp = timestamp;
    }

}
