package ru.netology;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class MonthStatistics implements Statistics {
    private Map<String, Object> maxMonthCategory;

    @Override
    public Map<String, Object> findMaxCategory(List<Purchase> list) {
        Map<String, Object> foundData = new HashMap<>();

        Map<String, Integer> mapPerMonth = list.stream()
                .filter(p -> p.getMonth() == list.get(list.size() - 1).getMonth())
                .collect(Collectors.groupingBy(Purchase::getCategory, Collectors.summingInt(Purchase::getSum)));

        Optional<Map.Entry<String, Integer>> maxMonthCategoryEntry = mapPerMonth.entrySet().stream()
                .max(Map.Entry.comparingByValue());


        String value1 = maxMonthCategoryEntry.get()
                .getKey();
        int value2 = maxMonthCategoryEntry.get()
                .getValue();

        foundData.put("category", value1);
        foundData.put("sum", value2);

        this.maxMonthCategory = foundData;
        return foundData;
    }
}