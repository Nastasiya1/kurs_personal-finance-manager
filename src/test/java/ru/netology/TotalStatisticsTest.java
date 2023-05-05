package ru.netology;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
class TotalStatisticsTest {
//    Statistics testStat;
//    List<Purchase> testList;

    @Test
    void testFindMaxCategoryMethod() {
       Statistics testStat = new TotalStatistics();
       List<Purchase> testList = new ArrayList<>();
        Purchase purchase1 = new Purchase("колбаса", "2023.04.18", 250);
        testList.add(purchase1);
        Purchase purchase2 = new Purchase("акции", "2023.04.22", 50000);
        testList.add(purchase2);
        Integer expectedValue = 50000;
        Integer actualValue = (Integer) testStat.findMaxCategory(testList).get("sum");

        Assertions.assertEquals(expectedValue, actualValue);
    }
}