package com.hotel.cheapHotel.Dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class HotelRequest {

    private List<LocalDate> dates;
    private String customerType;
}
