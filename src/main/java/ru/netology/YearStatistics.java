package ru.netology;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class YearStatistics implements Statistics {
    private Map<String, Object> maxYearCategory;

    @Override
    public Map<String, Object> findMaxCategory(List<Purchase> list) {
        Map<String, Object> foundData = new HashMap<>();

        Map<String, Integer> mapPerYear = list.stream()
                .filter(p -> p.getYear() == list.get(list.size() - 1).getYear())
                .collect(Collectors.groupingBy(Purchase::getCategory, Collectors.summingInt(Purchase::getSum)));

        Optional<Map.Entry<String, Integer>> maxYearCategoryEntry = mapPerYear.entrySet().stream()
                .max(Map.Entry.comparingByValue());


        String value1 = maxYearCategoryEntry.get()
                .getKey();
        int value2 = maxYearCategoryEntry.get()
                .getValue();

        foundData.put("category", value1);
        foundData.put("sum", value2);

        this.maxYearCategory = foundData;
        return foundData;
    }
}