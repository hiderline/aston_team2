package teamwork.factories;

import teamwork.models.Bus;
import teamwork.validators.ExceptionHandler;
import teamwork.validators.Validators;
import teamwork.validators.ValidationResult;

public abstract class BaseBusFillStrategy implements BusFillStrategy {
    /**
     * Общий метод для создания автобуса из строки данных
     */
    protected Bus createBusFromLine(String line) {
        // Валидируем строку
        if (!Validators.validateCsvLine(line)) {
            ExceptionHandler.printError("Строка не валидна, пропуск");
            return null;
        }

        // Парсим данные
        String[] parsedData = Validators.parseCsvLine(line);

        // Валидируем данные по отдельности
        ValidationResult validation = Validators.validateAllFields(parsedData);

        if (validation.isAllValid()) {
            // Создаем объект Bus
            Bus bus = new Bus.Builder(
                    validation.getNumber(),
                    validation.getModel(),
                    validation.getOdometer())
                    .build();
            ExceptionHandler.printSuccess("Автобус успешно добавлен: " + bus);
            return bus;
        } else {
            ExceptionHandler.printError("Данные для полей не валидны, пропуск");
            return null;
        }
    }
}
