package com.gridnine.testing;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Фильтр для исключения перелетов, у которых время вылета раньше текущего времени в любом сегменте.
 */
public class DepartureBeforeCurrentTimeFilter implements FlightFilter {

    /**
     * Фильтрует список перелетов, оставляя только те, у которых время вылета в каждом сегменте
     * идет после текущего времени.
     *
     * @param flights Список перелетов, который нужно отфильтровать.
     * @return Список перелетов, у которых время вылета в каждом сегменте идет после текущего времени.
     */
    @Override
    public List<Flight> filter(List<Flight> flights) {
        LocalDateTime currentTime = LocalDateTime.now();

        return flights.stream()
                .filter(flight -> flight.getSegments()
                        .stream()
                        .allMatch(segment -> segment.getDepartureDate()
                                .isAfter(currentTime)))
                .collect(Collectors.toList());
    }

    /**
     * Возвращает текстовое описание фильтра для отображения в выводе.
     *
     * @return Текстовое описание фильтра.
     */
    @Override
    public String filterText() {
        return "\nПерелеты после фильтрации по времени вылета: ";
    }
}
