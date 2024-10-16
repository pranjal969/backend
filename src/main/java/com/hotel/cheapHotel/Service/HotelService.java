package com.hotel.cheapHotel.Service;

import com.hotel.cheapHotel.Dto.HotelPriceResponse;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface HotelService {

    HotelPriceResponse calculateTotalPrices(List<LocalDate> dates, String customerType);

}
