package teamwork;

import teamwork.factories.BusManager;
import teamwork.factories.FileBusFillStrategy;
import teamwork.factories.ManualBusFillStrategy;
import teamwork.factories.RandomBusFillStrategy;
import teamwork.models.Bus;
import teamwork.utils.MenuUtils;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static List<Bus> buses = new ArrayList<>();

    public static void main(String[] args) {
        entryPoint();
    }

    public static void entryPoint() {
        boolean running = true;
        while (running) {
            MenuUtils.showMainMenu();
            int choice = getIntInput("Выберите действие: ");

            switch (choice) {
                case 1 ->
                    fillCollection();
                case 2 ->
                    displayCollection();
                case 3 ->
                    sortCollection();
                case 4 ->
                    saveToFile();
                case 5 ->
                    findByValue();
                case 6 ->
                    clearCollection();
                case 0 -> {
                    running = false;
                    System.out.println("Выход из программы...");
                }

                default ->
                    System.out.println("Неверный выбор, повторите попытку.");
            }

        }
        scanner.close();
        System.exit(0);
    }

    private static void findByValue() {
    }

    private static void displayCollection() {
        //System.out.println("size()=" + buses.size());
        for (Bus bus: buses) {
            System.out.println(bus.toString());
        }
    }
    private static void sortCollection() {
        MenuUtils.showSortCollectionMenu();
    }

    private static void fillCollection() {
        BusManager busManager = new BusManager();
        boolean running = true;
        int size = 0;

        while (running) {
            size = getIntInput("Введите размера массива(1-10): ");

            if( size >= 1 && size <= 10) {
                running = false;
            } else {
                System.out.println("Указан неверный размер массива");
            }
        }

        running = true;

        while (running) {
            MenuUtils.showFillCollectionMenu();
            int choice = getIntInput("Выберите способ: ");

            switch (choice) {
                case 1 -> {
                    busManager.setStrategy(new ManualBusFillStrategy());
                    buses.addAll(busManager.createBuses(size));
                    running = false;
                    //fillManually();
                }
                case 2 -> {
                    busManager.setStrategy(new RandomBusFillStrategy());
                    buses.addAll(busManager.createBuses(size));
                    running = false;
                    //fillRandomly();
                }
                case 3 -> {
                    busManager.setStrategy(new FileBusFillStrategy());
                    buses.addAll(busManager.createBuses(size));
                    running = false;

                }
                case 0 ->
                    running = false;
                default ->
                    System.out.println("Неверный выбор.");
            }
        }
    }

    private static void fillManually() {
        new ManualBusFillStrategy();
    }
    private static void fillRandomly() {
        //Call RandomDataFactory()
    }
    private static void fillFromFile() {
        //Call FileDaraFactory()
    }
    private static void saveToFile() {

    }



    private static int getIntInput(String message) {
        while (true) {
            try {
                System.out.println(message);
                return scanner.nextInt();
            }
            catch (InputMismatchException e) {
                System.out.println("Ошибка: введите номер пункта из меню");
                scanner.next();
            }
        }
    }

    private static void clearCollection() {
        buses.clear();
    }
}