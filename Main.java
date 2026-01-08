/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.gradleproject1;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main{
    public static void main(String[] args) {
        try{
            Scanner scanner = new Scanner(System.in);
            BusManager manager = new BusManager();

            boolean running = true;
            System.out.print("what is your name: ");
            String name = scanner.nextLine();
            System.out.println("hello, " + name + "!");

            while (running) {


                System.out.println("\nMenu:");
                System.out.println("1 - file");
                System.out.println("2 - random");
                System.out.println("3 - hand");
                System.out.println("0 - exit");

                System.out.print("Choose an option: ");

                String choiceStr = scanner.nextLine(); // Читаем строку целиком
                if (choiceStr.isEmpty()) {
                    continue;
                }
                int choice = Integer.parseInt(choiceStr.trim()); // Преобразуем строку в число
                if (choice == 0) {
                    running = false;
                    break;
                }

                System.out.print("Enter number of buses: ");
                String sizeStr = scanner.nextLine(); // Снова читаем строку целиком
                int size = Integer.parseInt(sizeStr.trim()); // Преобразование строки в число

                switch (choice) {
                    case 1 -> manager.setStrategy(new FileBusFillStrategy());
                    case 2 -> manager.setStrategy(new RandomBusFillStrategy());
                    case 3 ->{
                        manager.setStrategy(new ManualBusFillStrategy());
                    }
                    default -> {
                        System.out.println("error");
                        continue;
                    }
                }

                List<Bus> buses = manager.createBuses(size);

                System.out.println("\bus list:");
                BubbleSort.sortByModel(buses);
                BubbleSort.sortByMileage(buses);
                BubbleSort.sortByNumber(buses);
                for (Bus bus : buses) {
                    System.out.println(bus);
                }

            }

        }catch (NumberFormatException e) {
            System.out.println("Error: Please enter a valid number.");
        }
    }
}