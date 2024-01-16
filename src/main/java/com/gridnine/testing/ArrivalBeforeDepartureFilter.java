package com.gridnine.testing;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Фильтр для исключения перелетов, у которых время прибытия раньше времени вылета в любом сегменте.
 */
public class ArrivalBeforeDepartureFilter implements FlightFilter {

    /**
     * Фильтрует список перелетов, оставляя только те, у которых время прибытия в каждом сегменте
     * идет после времени вылета.
     *
     * @param flights Список перелетов, который нужно отфильтровать.
     * @return Список перелетов, у которых время прибытия в каждом сегменте идет после времени вылета.
     */
    @Override
    public List<Flight> filter(List<Flight> flights) {
        return flights.stream()
                .filter(flight -> flight.getSegments()
                        .stream()
                        .allMatch(segment -> segment.getArrivalDate()
                                .isAfter(segment.getDepartureDate())))
                .collect(Collectors.toList());
    }

    /**
     * Возвращает текстовое описание фильтра для отображения в выводе.
     *
     * @return Текстовое описание фильтра.
     */
    @Override
    public String filterText() {
        return "\nПерелеты после фильтрации по времени прибытия: ";
    }
}
