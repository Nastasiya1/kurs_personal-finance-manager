package ru.netology;

import java.util.List;
import java.util.Map;

public interface Statistics {
    Map<String, Object> findMaxCategory(List<Purchase> list);
}