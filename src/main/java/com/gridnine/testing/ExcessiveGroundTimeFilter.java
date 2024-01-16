package com.gridnine.testing;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Фильтр для исключения перелетов, у которых общее время, проведенное на земле между сегментами,
 * превышает два часа.
 */
public class ExcessiveGroundTimeFilter implements FlightFilter {

    /**
     * Фильтрует список перелетов, оставляя только те, у которых общее время на земле между сегментами
     * не превышает двух часов.
     *
     * @param flights Список перелетов, который нужно отфильтровать.
     * @return Список перелетов, у которых общее время на земле между сегментами не превышает двух часов.
     */
    @Override
    public List<Flight> filter(List<Flight> flights) {
        return flights.stream()
                .filter(flight -> calcTotalTime(flight) <= 2)
                .collect(Collectors.toList());
    }

    /**
     * Рассчитывает общее время на земле между сегментами перелета.
     *
     * @param flight Перелет, для которого нужно рассчитать общее время на земле.
     * @return Общее время на земле между сегментами перелета в часах.
     */
    private long calcTotalTime(Flight flight) {
        List<Segment> segments = flight.getSegments();
        long totalTime = 0;

        for (int i = 0; i < segments.size() - 1; i++) {
            LocalDateTime currentArrival = segments.get(i).getArrivalDate();
            LocalDateTime nextDeparture = segments.get(i + 1).getDepartureDate();

            totalTime += currentArrival.until(nextDeparture, ChronoUnit.HOURS);
        }

        return totalTime;
    }

    /**
     * Возвращает текстовое описание фильтра для отображения в выводе.
     *
     * @return Текстовое описание фильтра.
     */
    @Override
    public String filterText() {
        return "\nПолеты после фильтрации, где проведенное на земле время превышает два часа: ";
    }
}
