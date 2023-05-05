package ru.netology;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Purchase {
    private String title;
    private String date;
    private int sum;
    private String category;

    public int getSum() {
        return sum;
    }

    public String getTitle() {
        return title;
    }

    public String getCategory() {
        return this.category = addTypeOfCategory(this);
    }

    private String addTypeOfCategory(Purchase purchase) {
        try (Scanner scanner = new Scanner(new File("categories.tsv"))) {
            while (scanner.hasNextLine()) {
                String[] parts = scanner.nextLine().split("\t");
                if (purchase.getTitle().equals(parts[0])) {
                    return parts[1];
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Файл с категориями не найден");
            e.printStackTrace();
        }
        return "другое";
    }
}