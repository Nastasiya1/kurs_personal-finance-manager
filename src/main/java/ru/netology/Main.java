package ru.netology;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws ParseException {
        try (ServerSocket serverSocket = new ServerSocket(8989)) {
            List <Purchase> allPurchases = new ArrayList<>();
            File file = new File("data.bin");
            if (file.exists()) {
                try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
                    allPurchases = (List<Purchase>) in.readObject();
                } catch (ClassNotFoundException | IOException e) {
                    throw new RuntimeException(e);
                }
            }

            while (true) {
                try (
                        Socket socket = serverSocket.accept();
                        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                ) {
                    out.println("connected");

                    Gson gson1 = new Gson();
                    String json = in.readLine();
                    Purchase purchase = gson1.fromJson(json, Purchase.class);
                    purchase.specify();
                    allPurchases.add(purchase);

                    try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("data.bin"))) {
                        oos.writeObject(allPurchases);
                    }
                    List<Statistics> overallStatistics = new ArrayList<>();

                    Statistics stat1 = new TotalStatistics();
                    stat1.findMaxCategory(allPurchases);
                    overallStatistics.add(stat1);

                    Statistics stat2 = new YearStatistics();
                    stat2.findMaxCategory(allPurchases);
                    overallStatistics.add(stat2);

                    Statistics stat3 = new MonthStatistics();
                    stat3.findMaxCategory(allPurchases);
                    overallStatistics.add(stat3);

                    Statistics stat4 = new DayStatistics();
                    stat4.findMaxCategory(allPurchases);
                    overallStatistics.add(stat4);

                    Gson gson = new GsonBuilder().setPrettyPrinting().create();
                    String jsonResponse = gson.toJson(overallStatistics);
                    out.println(jsonResponse);
                }
            }
        } catch (IOException e) {
            System.out.println("Не могу стартовать сервер");
            e.printStackTrace();
        }
    }
}