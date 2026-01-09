/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.gradleproject1;

import java.util.List;
import java.util.Scanner;

/**
 *
 * @author User
 */
public class Tests {
    public static void main(String[] args) {
        String profile = "Ivan";
        System.out.println("Hello " + profile);
        int size = 2;

        BusManager manager = new BusManager();
        manager.setStrategy(new FileBusFillStrategy());

        System.out.println("File strategy");
        List<Bus> buses = manager.createBuses(size);
        System.out.println("User size = 2 strategy size " + buses.size());



        System.out.println("Sort before ");

        BubbleSort.sortByModel(buses);
        BubbleSort.sortByMileage(buses);
        BubbleSort.sortByNumber(buses);

        System.out.println("Sort fter");

        System.out.println("Random strategy");

        manager.setStrategy(new RandomBusFillStrategy());
        size = 3;

        buses = manager.createBuses(size);

        System.out.println("User size = 3 strategy size " + buses.size());
        System.out.println("before sort");

        for (Bus bus : buses) {
            System.out.println(bus);
        }
        BubbleSort.sortByNumber(buses);
        BubbleSort.sortByMileage(buses);
        BubbleSort.sortByModel(buses);

        System.out.println("after sort");

        for (Bus bus : buses) {
            System.out.println(bus);
        }

        size = 2;

        manager.setStrategy(new ManualBusFillStrategy());

        buses = manager.createBuses(size);

        System.out.println("User size = 2 strategy size " + buses.size());

        System.out.println("before sort");

        for (Bus bus : buses) {
            System.out.println(bus);
        }
        BubbleSort.sortByNumber(buses);
        BubbleSort.sortByMileage(buses);
        BubbleSort.sortByModel(buses);

        System.out.println("after sort");

        for (Bus bus : buses) {
            System.out.println(bus);
        }
    }
}
