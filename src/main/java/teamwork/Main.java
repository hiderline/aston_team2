package teamwork;

import teamwork.factories.BusManager;
import teamwork.factories.FileBusFillStrategy;
import teamwork.factories.ManualBusFillStrategy;
import teamwork.factories.RandomBusFillStrategy;
import teamwork.models.Bus;
import teamwork.utils.FindByCollection;
import teamwork.utils.MenuUtils;
import teamwork.validators.ExceptionHandler;
import teamwork.validators.FileHandler;
import teamwork.validators.Validators;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import static teamwork.utils.MenuUtils.showManualFillMenu;

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
                    FindByCollection.findByValue(buses, 3);
                    break;
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
        if (buses.isEmpty()) {
            System.out.println("Коллекция пуста");
        } else {
            for (Bus bus : buses) {
                System.out.println(bus.toString());
            }
            System.out.println("Всего автобусов: " + buses.size());
        }
    }
    private static void sortCollection() {
        MenuUtils.showSortCollectionMenu();
    }

    private static void fillCollection() {
        BusManager busManager = new BusManager();
        boolean menuActive = true;

        while (menuActive) {
            MenuUtils.showFillCollectionMenu();
            int choice = getIntInput("Выберите способ: ");
            int amount = 0;

            switch (choice) {
                case 1:
                    amount = getFillingAmount();
                    showManualFillMenu();

                    busManager.setStrategy(new ManualBusFillStrategy());
                    buses.addAll(busManager.createBuses(amount));
                    menuActive = false;
                    //fillManually();
                    break;
                case 2:
                    amount = getFillingAmount();

                    busManager.setStrategy(new RandomBusFillStrategy());
                    buses.addAll(busManager.createBuses(amount));
                    menuActive = false;
                    //fillRandomly();
                    break;
                case 3:
                    amount = getFillingAmount();

                    busManager.setStrategy(new FileBusFillStrategy());
                    buses.addAll(busManager.createBuses(amount));
                    menuActive = false;
                    //fillFromFile();
                    break;
                case 0:
                    menuActive = false;
                    break;
                default:
                    System.out.println("Неверный выбор.");
            }
        }
    }

    private static void fillManually() {
        //call ManualBusFillStrategy()
    }
    private static void fillRandomly() {
        //Call RandomDataFactory()
    }
    private static void fillFromFile() {
        //Call FileDaraFactory()
    }
    private static void saveToFile() {
        System.out.println("Введите название файла (без расширения)");
        String filename = scanner.nextLine().trim();

        if (Validators.validateFilename(filename)) {
            FileHandler fileHandler = new FileHandler();
            fileHandler.writeToFile(buses, filename);
        }


    }

    private static int getFillingAmount() {
        return getIntInput("Введите кол-во элементов для заполнения:");
    }

    private static int getIntInput(String message) {
        while (true) {
            try {
                System.out.println(message);
                return Integer.parseInt(scanner.nextLine());
            }
            catch (InputMismatchException e) {
                ExceptionHandler.printError("Ошибка: введите номер пункта из меню");
            }
            catch (NumberFormatException e) {
                ExceptionHandler.printError("Введено недопустимое значение меню");
            }
        }
    }

    private static void clearCollection() {
        buses.clear();
    }
}