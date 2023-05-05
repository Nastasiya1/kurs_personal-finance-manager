package ru.netology;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class TotalStatistics implements Statistics {
    private Map<String, Object> maxCategory;

    @Override
    public Map<String, Object> findMaxCategory(List<Purchase> list) {
        Map<String, Object> foundData = new HashMap<>();
        Map<String, Integer> mapOfSumsPaidInCategories = list.stream()
                .collect(Collectors.groupingBy(Purchase::getCategory, Collectors.summingInt(Purchase::getSum)));
        Optional<Map.Entry<String, Integer>> maxCategoryEntry = mapOfSumsPaidInCategories.entrySet().stream()
                .max(Map.Entry.comparingByValue());

        String value1 = maxCategoryEntry.get()
                .getKey();
        int value2 = maxCategoryEntry.get()
                .getValue();

        foundData.put("category", value1);
        foundData.put("sum", value2);

        this.maxCategory = foundData;
        return foundData;
    }
}