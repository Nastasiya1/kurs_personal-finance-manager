package ru.netology;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.Scanner;

public class Purchase implements Serializable {
    private static final long serialVersionUID = 1L;
    private String title;
    private String date;
    private int sum;
    private String category;
    public Purchase(String title, String date, int sum) {
        this.title = title;
        this.date = date;
        this.sum = sum;
    }

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