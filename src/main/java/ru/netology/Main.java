package ru.netology;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(8989)) {
            List<Purchase> allPurchases = new ArrayList<>();
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
                    allPurchases.add(purchase);

                    Statistics stat = new TotalStatistics();
                    stat.findMaxCategory(allPurchases);
                    Gson gson = new GsonBuilder().setPrettyPrinting().create();
                    String jsonResponse = gson.toJson(stat);
                    out.println(jsonResponse);
                }
            }
        } catch (IOException e) {
            System.out.println("Не могу стартовать сервер");
            e.printStackTrace();
        }
    }
}