package com.hotel.cheapHotel.Service.ServiceImpl;

import com.hotel.cheapHotel.Dto.HotelPriceResponse;
import com.hotel.cheapHotel.Entities.Hotel;
import com.hotel.cheapHotel.Entities.Rate;
import com.hotel.cheapHotel.Repository.HotelRepository;
import com.hotel.cheapHotel.Repository.RateRepository;
import com.hotel.cheapHotel.Service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class HotelServiceImpl implements HotelService {

    @Autowired
    private RateRepository rateRepository;

    @Autowired
    private HotelRepository hotelRepository; // To fetch hotel details

    @Override
    public HotelPriceResponse calculateTotalPrices(List<LocalDate> dates, String customerType) {
        Map<Long, Double> totalPrices = new HashMap<>();

        for (LocalDate date : dates) {
            String dayType = (date.getDayOfWeek() == DayOfWeek.SATURDAY || date.getDayOfWeek() == DayOfWeek.SUNDAY) ? "weekend" : "weekday";
            List<Rate> rates = rateRepository.findRatesByDayTypeAndCustomerType(dayType, customerType);

            for (Rate rate : rates) {
                totalPrices.merge(rate.getHotel().getHotelId(), rate.getPrice(), Double::sum);
            }
        }
        // Find the hotel with the minimum price and highest rating in case of ties
        HotelPriceResponse bestHotel = null;

        for (Map.Entry<Long, Double> entry : totalPrices.entrySet()) {
            Long hotelId = entry.getKey();
            double totalPrice = entry.getValue();

            // Fetch hotel details
            Hotel hotel = hotelRepository.findById(hotelId).orElse(null);
            if (hotel != null) {
                // Create a HotelPriceResponse object for this hotel
                HotelPriceResponse hotelResponse = new HotelPriceResponse(hotel.getHotelId(), hotel.getName(), hotel.getRating(), totalPrice);

                // Determine if this hotel should be the best option
                if (bestHotel == null ||
                        (totalPrice < bestHotel.getTotalPrice()) ||
                        (totalPrice == bestHotel.getTotalPrice() && hotel.getRating() > bestHotel.getHotelRating())) {
                    bestHotel = hotelResponse;
                }
            }
        }

        return bestHotel;
    }
}