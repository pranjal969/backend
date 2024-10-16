package com.hotel.cheapHotel.Repository;

import com.hotel.cheapHotel.Entities.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelRepository extends JpaRepository<Hotel,Long> {


}
