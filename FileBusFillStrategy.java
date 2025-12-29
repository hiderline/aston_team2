package com.example.bus;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileBusFillStrategy implements BusFillStrategy {

    @Override
    public List<Bus> fillBuses(int size) {
        List<Bus> buses = new ArrayList<>();

        try (Scanner scanner = new Scanner(new File("buses.txt"))) {
            while (scanner.hasNext() && buses.size() < size) {

                int number = scanner.nextInt();
                String model = scanner.next();
                int mileage = scanner.nextInt();

                Bus bus = new Bus.Builder()
                        .setNumber(number)
                        .setModel(model)
                        .setMileage(mileage)
                        .build();

                buses.add(bus);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Файл buses.txt не найден");
        }

        return buses;
    }
}