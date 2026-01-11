package teamwork.utils;

import java.util.InputMismatchException;
import java.util.Scanner;

import static teamwork.utils.CollectionUtils.*;
import static teamwork.utils.CollectionUtils.clearCollection;
import static teamwork.utils.CollectionUtils.saveToFile;

public class ConsoleUtils {

    private static final Scanner scanner = new Scanner(System.in);

    public static void entryPoint() {
        boolean running = true;
        while (running) {
            MenuUtils.showMainMenu();

            String choice = getChoiceFromConsole();

            switch (choice) {
                case "1":
                    fillCollection();
                    break;
                case "2":
                    displayCollection();
                    break;
                case "3":
                    sortCollection();
                    break;
                case "4":
                    saveToFile();
                    break;
                case "5":
                    break;
                case "6":
                    clearCollection();
                    break;
                case "0":
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

    public static String getChoiceFromConsole() {
        System.out.print("Выберите действие: ");
        return scanner.next();
    }

    public static int getNumberFromConsole(String message) {
        int number;
        while (true) {
            try {
                System.out.print(message);
                number = scanner.nextInt();
                if (number > 0)
                    return number;
                else {
                    System.out.println("Ошибка! Число должно быть положительным");
                }
            } catch (InputMismatchException e) {
                System.out.println("Ошибка! Введено не число");
                scanner.nextLine();
            }
        }
    }
}
