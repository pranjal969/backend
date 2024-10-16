package com.hotel.cheapHotel.Controller;

import com.hotel.cheapHotel.Dto.HotelPriceResponse;
import com.hotel.cheapHotel.Dto.HotelRequest;
import com.hotel.cheapHotel.Service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/hotels")
@CrossOrigin("*")
public class HomeController {

    @Autowired
    private HotelService hotelService;

    @PostMapping("/calculate-prices")
    public ResponseEntity<HotelPriceResponse> calculatePrices(@RequestBody HotelRequest request) {
        List<LocalDate> dates = request.getDates();  // Assuming you have a DTO for the request
        String customerType = request.getCustomerType();

        HotelPriceResponse totalPrices = hotelService.calculateTotalPrices(dates, customerType);

        return ResponseEntity.ok(totalPrices);
    }
}
