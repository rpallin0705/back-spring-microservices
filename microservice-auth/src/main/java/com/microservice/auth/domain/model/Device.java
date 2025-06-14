package com.microservice.auth.domain.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Device {
    private Long id;
    private String deviceId;
    private String secret;
}