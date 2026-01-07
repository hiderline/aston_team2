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
                case 1:
                    fillCollection();
                    break;
                case 2:
                    displayCollection();
                    break;
                case 3:
                    sortCollection();
                    break;
                case 4:
                    saveToFile();
                    break;
                case 5:
                case 6:
                    clearCollection();
                    break;
                case 0:
                    running = false;
                    System.out.println("Выход из программы...");
                    break;
                default:
                    System.out.println("Неверный выбор, повторите попытку.");
            }

        }
        scanner.close();
        System.exit(0);
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

        while (running) {
            MenuUtils.showFillCollectionMenu();
            int choice = getIntInput("Выберите способ: ");

            switch (choice) {
                case 1:
                    busManager.setStrategy(new ManualBusFillStrategy());
                    buses.addAll(busManager.createBuses(-1));
                    running = false;
                    //fillManually();
                    break;
                case 2:
                    busManager.setStrategy(new RandomBusFillStrategy());
                    buses.addAll(busManager.createBuses(5));
                    running = false;
                    //fillRandomly();
                    break;
                case 3:
                    busManager.setStrategy(new FileBusFillStrategy());
                    buses.addAll(busManager.createBuses(-1));
                    running = false;
                    //fillFromFile();
                    break;
                case 0:
                    running = false;
                    break;
                default:
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