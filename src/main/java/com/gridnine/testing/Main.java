package com.gridnine.testing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Flight> flights = FlightBuilder.createFlights();

        // Apply filters
        List<FlightFilter> filterList = Arrays.asList(
                new ArrivalBeforeDepartureFilter(),
                new DepartureBeforeCurrentTimeFilter(),
                new ExcessiveGroundTimeFilter()
        );

        System.out.println("Весь список рейсов:");
        System.out.println(flights);

        filterList.forEach(flightFilter -> {
            List<Flight> filteredFlights = flightFilter.filter(flights);
            System.out.println(flightFilter.filterText());
            System.out.println(flightFilter.filter(filteredFlights));
        });
    }
}