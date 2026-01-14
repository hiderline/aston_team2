package teamwork;

import teamwork.factories.BusManager;
import teamwork.factories.FileBusFillStrategy;
import teamwork.factories.ManualBusFillStrategy;
import teamwork.factories.RandomBusFillStrategy;
import teamwork.models.Bus;
import teamwork.strategies.*;
import teamwork.utils.FindByCollection;
import teamwork.utils.MenuUtils;
import teamwork.validators.ExceptionHandler;
import teamwork.validators.FileHandler;
import teamwork.validators.Validators;

import java.util.*;

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
            int choice = getIntInput("Выберите действие: ", 0 , 6);

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
                    saveToFile(buses);
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

    private static void displayCollection(List<Bus> buses) {
        if (buses.isEmpty()) {
            System.out.println("Коллекция пуста");
        } else {
            for (Bus bus : buses) {
                System.out.println(bus.toString());
            }
            System.out.println("Всего автобусов: " + buses.size());
        }
        waitingEmptyLine();
    }

    private static void sortCollection() {
        boolean menuActive = true;
        SortManager sortManager = new SortManager();

        while (menuActive) {
            MenuUtils.showSortCollectionMenu();
            int choice = getIntInput("Выберите способ сортировки: ", 0, 5);

            switch (choice) {
                case 1:
                    sortManager.setStrategy(new BusNumberSortStrategy());
                    performSorting(sortManager, buses);
                    menuActive = false;
                    break;
                case 2:
                    sortManager.setStrategy(new BusModelSortStrategy());
                    performSorting(sortManager, buses);
                    menuActive = false;
                    break;
                case 3:
                    sortManager.setStrategy(new BusOdometerSortStrategy());
                    performSorting(sortManager, buses);
                    menuActive = false;
                    break;
                case 4:
                    sortManager.setStrategy(new MultiFieldSortStrategy());
                    performSorting(sortManager, buses);
                    menuActive = false;
                    break;
                case 5:
                    evenSorting(sortManager);
                    menuActive = false;
                    break;
                case 0:
                    menuActive = false;
                    break;
            }
        }
    }

    private static void evenSorting(SortManager sortManager) {
        boolean menuActive = true;
        while (menuActive) {
            showEvenSortingMenu();
            int choice = getIntInput("Выберите способ сортировки: ", 1, 2);

            switch (choice) {
                case 1:
                    sortManager.setStrategy(
                            new EvenFieldSortStrategy(
                                    Bus::getNumber,
                                    Bus::isNumberEven
                            )
                    );
                    performSorting(sortManager, buses);
                    menuActive = false;
                    break;
                case 2:
                    sortManager.setStrategy(
                            new EvenFieldSortStrategy(
                                    Bus::getOdometer,
                                    Bus::isOdometerEven
                            )
                    );
                    performSorting(sortManager, buses);
                    menuActive = false;
                    break;
            }
        }
    }

    private static void performSorting(SortManager manager, List<Bus> buses) {
        if (buses.isEmpty()){
            ExceptionHandler.printError("Список пуст");
            return;
        }
        boolean ascending = getSortingDirection();
        manager.sort(buses, ascending);
        displayCollection(buses);
        saveToFile(buses, manager.getStrategyName());
    }

    private static boolean getSortingDirection() {
        showSortingDirection();
        int choice = getIntInput("Ваш выбор:", 1, 2);
        return choice == 1;
    }


    private static void fillCollection() {
        BusManager busManager = new BusManager();
        boolean menuActive = true;

        while (menuActive) {
            MenuUtils.showFillCollectionMenu();
            int choice = getIntInput("Выберите способ: ", 0, 3);

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
        int amount = getIntInput("Введите кол-во элементов для заполнения (max 100):", 1, 100);
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
                threadsAmount = getIntInput(
                        String.format("Укажите кол-во потоков (от %d до %d)", 1, busesSize));
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
    private static void saveToFile(List<Bus> buses) {
        System.out.println("Введите название файла (без расширения)");
        String filename = scanner.nextLine().trim();

        saveToFile(buses, filename);
    }
    private static void saveToFile(List<Bus> buses, String filename) {
        if (filename.isEmpty()) {
            ExceptionHandler.printError("Не указано имя файла...");
        }

        if (Validators.validateFilename(filename)) {
            FileHandler fileHandler = new FileHandler();
            fileHandler.writeToFile(buses, filename);
        }
    }

    private static int getIntInput(String message) {
        while (true) {
            try {
                System.out.println(message);
                int value = Integer.parseInt(scanner.nextLine().trim());
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
    private static int getIntInput(String message, int min, int max) {
        while (true) {
            int value = getIntInput(message);
            if (value >= min && value <= max) {
                return value;
            }
            ExceptionHandler.printWarning(
                    String.format("Введите число от %d до %d", min, max)
            );
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