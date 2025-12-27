/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.bus;

import java.util.List;

public class BubbleSort {

    // Сортировка по номеру
    public static void sortByNumber(List<Bus> buses) {
        for (int i = 0; i < buses.size() - 1; i++) {
            for (int j = 0; j < buses.size() - 1 - i; j++) {
                if (buses.get(j).getNumber() > buses.get(j + 1).getNumber()) {
                    swap(buses, j, j + 1);
                }
            }
        }
    }

    // Сортировка по модели (по алфавиту)
    public static void sortByModel(List<Bus> buses) {
        for (int i = 0; i < buses.size() - 1; i++) {
            for (int j = 0; j < buses.size() - 1 - i; j++) {
                if (buses.get(j).getModel()
                        .compareTo(buses.get(j + 1).getModel()) > 0) {
                    swap(buses, j, j + 1);
                }
            }
        }
    }

    // Сортировка по пробегу
    public static void sortByMileage(List<Bus> buses) {
        for (int i = 0; i < buses.size() - 1; i++) {
            for (int j = 0; j < buses.size() - 1 - i; j++) {
                if (buses.get(j).getMileage() > buses.get(j + 1).getMileage()) {
                    swap(buses, j, j + 1);
                }
            }
        }
    }

    private static void swap(List<Bus> buses, int i, int j) {
        Bus temp = buses.get(i);
        buses.set(i, buses.get(j));
        buses.set(j, temp);
    }
}

