package teamwork;

import teamwork.factories.BusManager;
import teamwork.factories.FileBusFillStrategy;
import teamwork.factories.ManualBusFillStrategy;
import teamwork.factories.RandomBusFillStrategy;
import teamwork.models.Bus;
import teamwork.strategies.*;
import teamwork.utils.BubbleSorter;
import teamwork.utils.FindByCollection;
import teamwork.utils.MenuUtils;
import teamwork.utils.Sorter;
import teamwork.validators.ExceptionHandler;
import teamwork.validators.FileHandler;
import teamwork.validators.Validators;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import static teamwork.utils.MenuUtils.*;

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
                    displayCollection(buses);
                    break;
                case 3:
                    sortCollection();
                    break;
                case 4:
                    saveToFile();
                    break;
                case 5:
                    searchByCollection();
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
    private static void displayCollection(List<Bus> busesSort) {
        if (busesSort.isEmpty()) {
            System.out.println("Коллекция пуста");
        } else {
            for (Bus bus : busesSort) {
                System.out.println(bus.toString());
            }
            System.out.println("Всего автобусов: " + busesSort.size());
        }
        waitingEmptyLine();
    }
    private static void sortCollection() {
        boolean menuActive = true;

        while (menuActive) {
            MenuUtils.showSortCollectionMenu();
            int choice = getIntInput("Выберите способ сортировки: ");

            switch (choice) {
                case 1:
                    buses = performSorting(new BusNumberSortStrategy(), buses);
                    break;
                case 2:
                    buses = performSorting(new BusModelSortStrategy(), buses);
                    break;
                case 3:
                    buses = performSorting(new BusOdometerSortStrategy(), buses);
                    break;
                case 4:
                    buses = performSorting(new MultiFieldSortStrategy(), buses);
                    break;
                case 5:
                    performEvenSorting();
                    break;
                case 0:
                    menuActive = false;
                    break;
            }
        }
    }
    private static List<Bus> performSorting(SortStrategy strategy, List<Bus> busesSort) {
        if (busesSort.isEmpty()){
            ExceptionHandler.printError("Список пуст");
            return new ArrayList<Bus>();
        }
        boolean ascending = getSortingDirection();
        Sorter sorter = new BubbleSorter();
        sorter.sort(busesSort, strategy, ascending);
        displayCollection(busesSort);
        return busesSort;
    }
    private static boolean getSortingDirection() {
        showSortingDirection();
        //int choice = getIntInput("Ваш выбор: ", 1, 2);
        int choice = getIntInput("Ваш выбор:");
        return choice == 1;
    }

    private static void performEvenSorting() {
        if (buses.isEmpty()){
            ExceptionHandler.printError("Список пуст");
            return ;
        }
        showSortingField();
        int choiceField = getIntInput("Ваш выбор:");
        List<Bus> copyBus = new ArrayList<>();
        if (choiceField == 1){
            for (Bus bus: buses){
                if(bus.isNumberEven()){
                    copyBus.add(bus);
                }
            }
            copyBus = performSorting(new BusNumberSortStrategy(), copyBus);
            int indexCopeBus = 0;
            for (int i = 0; i < buses.size(); i++) {
                if (buses.get(i).isNumberEven()){
                    buses.set(i, copyBus.get(indexCopeBus++));
                }
            }

        } else {
            for (Bus bus: buses){
                if(bus.isOdometerEven()){
                    copyBus.add(bus);
                }
            }
            copyBus = performSorting(new BusOdometerSortStrategy(), copyBus);
            int indexCopeBus = 0;
            for (int i = 0; i < buses.size(); i++) {
                if (buses.get(i).isOdometerEven()){
                    buses.set(i, copyBus.get(indexCopeBus++));
                }
            }
        }
        displayCollection(buses);
    }

    private static void fillCollection() {
        BusManager busManager = new BusManager();
        boolean menuActive = true;

        while (menuActive) {
            MenuUtils.showFillCollectionMenu();
            int choice = getIntInput("Выберите способ: ");

            switch (choice) {
                case 1:

                    fillManually(busManager);
                    menuActive = false;
                    break;
                case 2:
                    fillRandomly(busManager);
                    menuActive = false;
                    break;
                case 3:
                    fillFromFile(busManager);
                    menuActive = false;
                    break;
                case 0:
                    menuActive = false;
                    break;
                default:
                    System.out.println("Неверный выбор.");
            }
        }
    }

    private static void fillManually(BusManager busManager) {
        int amount = getIntInput("Введите кол-во элементов для заполнения:");
        showManualFillMenu();
        busManager.setStrategy(new ManualBusFillStrategy());
        buses.addAll(busManager.createBuses(amount));

    }
    private static void fillRandomly(BusManager busManager) {
        int amount = getIntInput("Введите кол-во элементов для заполнения:");
        busManager.setStrategy(new RandomBusFillStrategy());
        buses.addAll(busManager.createBuses(amount));
        waitingEmptyLine();
    }
    private static void fillFromFile(BusManager busManager) {
        int amount = getIntInput("Введите кол-во элементов для заполнения:");
        busManager.setStrategy(new FileBusFillStrategy());
        buses.addAll(busManager.createBuses(amount));
        waitingEmptyLine();
    }
    private static void searchByCollection() {
        boolean repeat = true;
        int threadsAmount = 0;
        int busesSize = buses.size();
        if (busesSize > 0) {
            while (repeat) {
                threadsAmount = getIntInput("Укажите кол-во потоков (от 1 до " + busesSize + ")");
                if (threadsAmount > 1 && threadsAmount <= busesSize) {
                    repeat = false;
                } else {
                    ExceptionHandler.printError("Неверное количество потоков.");
                }
            }
            FindByCollection.findByValue(buses, threadsAmount);
            waitingEmptyLine();
        } else {
            ExceptionHandler.printWarning("Для поиска заполните коллекцию");
        }
    }

    private static void saveToFile() {
        System.out.println("Введите название файла (без расширения)");
        String filename = scanner.nextLine().trim();

        if (Validators.validateFilename(filename)) {
            FileHandler fileHandler = new FileHandler();
            fileHandler.writeToFile(buses, filename);
        }


    }

    private static int getIntInput(String message) {
        while (true) {
            try {
                System.out.println(message);
                int value = Integer.parseInt(scanner.nextLine());
                if (value < 0) {
                    ExceptionHandler.printWarning("Значение не может быть отрицательным");
                    continue;
                }
                return value;
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
        ExceptionHandler.printInfo("Коллекция очищена");
        waitingEmptyLine();
    }
    private static void waitingEmptyLine() {
        System.out.println("\n" + "=".repeat(50));
        ExceptionHandler.printInfo("Enter для продолжения...");
        scanner.nextLine();

    }
}