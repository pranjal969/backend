package com.hotel.cheapHotel.Repository;

import com.hotel.cheapHotel.Entities.CustomerType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerTypeRepository extends JpaRepository<CustomerType, Long> {
}
