package com.gridnine.testing;

import java.util.*;

public class FlightFilterManager {
    public static List<Flight> applyFilters(List<Flight> flights, FlightFilter... filters) {
        for (FlightFilter filter : filters) {
            flights = filter.filter(flights);
        }
        return flights;
    }
}
