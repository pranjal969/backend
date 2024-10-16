package com.hotel.cheapHotel.Repository;

import com.hotel.cheapHotel.Entities.Rate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RateRepository extends JpaRepository<Rate, Long> {

    @Query("SELECT r FROM Rates r JOIN r.hotel h JOIN r.customerType ct " +
            "WHERE r.dayType = :dayType AND ct.customerType = :customerType")
    List<Rate> findRatesByDayTypeAndCustomerType(@Param("dayType") String dayType,
                                                 @Param("customerType") String customerType);
}
