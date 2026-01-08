package com.example.gradleproject1;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ManualBusFillStrategy implements BusFillStrategy {

    @Override
    public List<Bus> fillBuses(int size) {
        Scanner scanner = new Scanner(System.in);
        List<Bus> buses = new ArrayList<>();
        try{
            for (int i = 0; i < size; i++) {
                System.out.println("Bus #" + (i + 1));

                System.out.print("number: ");
                int number = Integer.parseInt(scanner.nextLine().trim()); // Чтение строки и конвертация в int

                System.out.print("model: ");
                String model = scanner.nextLine().trim(); // Чтение строки

                System.out.print("mileage: ");
                int mileage = Integer.parseInt(scanner.nextLine().trim()); // Чтение строки и конвертация в int

                Bus bus = new Bus.Builder()
                        .setNumber(number)
                        .setModel(model)
                        .setMileage(mileage)
                        .build();

                buses.add(bus);
            }
        }catch (NumberFormatException e) {
            System.out.println("Error: Please enter a valid number.");
        }

        return buses;
    }
}