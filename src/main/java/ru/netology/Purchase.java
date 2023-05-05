package ru.netology;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class Purchase implements Serializable {
    private static final long serialVersionUID = 1L;
    private String title;
    private String date;
    private int sum;
    private String category;
    private Calendar calendar;
    private int year;
    private int month;
    private int day;

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

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
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
    public Purchase specify() throws ParseException {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new SimpleDateFormat("yyyy.MM.dd").parse(date));
        this.calendar = cal;
        this.year = calendar.get(Calendar.YEAR);
        this.month = calendar.get(Calendar.MONTH);
        this.day = calendar.get(Calendar.DAY_OF_MONTH);
        return this;
    }
}