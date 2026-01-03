package teamwork;

import teamwork.utils.ConsoleUtils;
import teamwork.utils.MenuUtils;

import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);

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

    }
    private static void sortCollection() {
        MenuUtils.showSortCollectionMenu();
    }

    private static void fillCollection() {
        MenuUtils.showFillCollectionMenu();
        int choice = getIntInput("Выберите способ: ");
        switch (choice) {
            case 1:
                fillManually();
                break;
            case 2:
                fillRandomly();
                break;
            case 3:
                fillFromFile();
                break;
            default:
                System.out.println("Неверный выбор.");
        }
    }

    private static void fillManually() {
        //Call ManualDaraFactory()
    }
    private static void fillRandomly() {
        //Call RandomDaraFactory()
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
            catch (NumberFormatException e) {
                System.out.println("Ошибка: введите номер пункта из меню");
            }
        }
    }

    private static void clearCollection() {

    }
}