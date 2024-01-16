package com.gridnine.testing;

import java.util.*;

/**
 * Основной класс приложения для демонстрации фильтрации перелетов.
 */
public class Main {
    public static void main(String[] args) {
        // Создаем список перелетов с использованием FlightBuilder
        List<Flight> flights = FlightBuilder.createFlights();

        // Создаем список фильтров для применения к перелетам
        List<FlightFilter> filterList = Arrays.asList(
                new ArrivalBeforeDepartureFilter(),
                new DepartureBeforeCurrentTimeFilter(),
                new ExcessiveGroundTimeFilter()
        );

        // Выводим весь список рейсов перед применением фильтров
        System.out.println("Весь список рейсов:");
        System.out.println(flights);

        // Применяем каждый фильтр к списку перелетов и выводим результаты
        filterList.forEach(flightFilter -> {
            List<Flight> filteredFlights = flightFilter.filter(flights);
            System.out.println(flightFilter.filterText());
            System.out.println(filteredFlights);
        });
    }
}
