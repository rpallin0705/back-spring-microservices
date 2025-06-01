package com.microservice.user.domain.model;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    private Long id;
    private String name;
    private String authEmail;
    private String phone;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private List<Address> addresses;
}