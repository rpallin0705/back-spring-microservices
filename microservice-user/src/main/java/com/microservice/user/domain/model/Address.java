package com.microservice.user.domain.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Address {
    private Long id;
    private String street;
    private String city;
    private String postalCode;
    private String country;
    private Long userId;
}