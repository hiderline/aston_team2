package teamwork.services;

import teamwork.validators.ExceptionHandler;

import java.util.Scanner;

public class InputService {
    private final Scanner scanner;

    public InputService() {
        this.scanner = new Scanner(System.in);
    }

    public int getIntInput(String message) {
        while (true) {
            try {
                System.out.println(message);
                String input = scanner.nextLine().trim();
                if (input.isEmpty()) {
                    ExceptionHandler.printError("Пустой ввод. Попробуйте снова.");
                    continue;
                }

                int value = Integer.parseInt(input);
                if (value < 0) {
                    ExceptionHandler.printWarning("Значение не может быть отрицательным");
                    continue;
                }
                return value;
            } catch (NumberFormatException e) {
                ExceptionHandler.printError("Введено недопустимое значение меню");
            }
        }
    }

    public int getIntInput(String message, int min, int max) {
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

    public String getStringInput(String message) {
        System.out.println(message);
        return scanner.nextLine().trim();
    }

    public void waitForEnter() {
        System.out.println("\n" + "=".repeat(50));
        ExceptionHandler.printInfo("Enter для продолжения...");
        scanner.nextLine();
    }

    public void closeScanner() {
        scanner.close();
    }
}
