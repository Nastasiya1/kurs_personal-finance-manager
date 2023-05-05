package ru.netology;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class DayStatistics implements Statistics {
    private Map<String, Object> maxDayCategory;

    @Override
    public Map<String, Object> findMaxCategory(List<Purchase> list) {
        Map<String, Object> foundData = new HashMap<>();

        Map<String, Integer> mapPerDay = list.stream()
                .filter(p -> p.getDay() == list.get(list.size() - 1).getDay())
                .collect(Collectors.groupingBy(Purchase::getCategory, Collectors.summingInt(Purchase::getSum)));

        Optional<Map.Entry<String, Integer>> maxDayCategoryEntry = mapPerDay.entrySet().stream()
                .max(Map.Entry.comparingByValue());


        String value1 = maxDayCategoryEntry.get()
                .getKey();
        int value2 = maxDayCategoryEntry.get()
                .getValue();

        foundData.put("category", value1);
        foundData.put("sum", value2);

        this.maxDayCategory = foundData;
        return foundData;
    }
}