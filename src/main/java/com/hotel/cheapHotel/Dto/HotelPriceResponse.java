package com.hotel.cheapHotel.Dto;

import lombok.Data;

@Data
public class HotelPriceResponse {
    private Long hotelId;
    private String hotelName;
    private int hotelRating;
    private double totalPrice;

    public HotelPriceResponse(Long hotelId, String hotelName, int hotelRating, double totalPrice) {
        this.hotelId = hotelId;
        this.hotelName = hotelName;
        this.hotelRating = hotelRating;
        this.totalPrice = totalPrice;
    }
}
