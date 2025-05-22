// SpringDataAddressRepository.java
package com.microservice.user.infrastructure.persistence.springdata;

import com.microservice.user.infrastructure.persistence.entity.AddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataAddressRepository extends JpaRepository<AddressEntity, Long> {
}