package teamwork.factories;

import teamwork.models.Bus;
import teamwork.validators.BusExceptionHandler;
import teamwork.validators.BusValidator;
import teamwork.validators.ValidationResult;

public abstract class BaseBusFillStrategy implements BusFillStrategy {
    /**
     * Общий метод для создания автобуса из строки данных
     */
    protected Bus createBusFromLine(String line) {
        // Валидируем строку
        if (!BusValidator.validateCsvLine(line)) {
            BusExceptionHandler.printError("Строка не валидна, пропуск");
            return null;
        }

        // Парсим данные
        String[] parsedData = BusValidator.parseCsvLine(line);

        // Валидируем данные по отдельности
        ValidationResult validation = BusValidator.validateAllFields(parsedData);

        if (validation.isAllValid()) {
            // Создаем объект Bus
            Bus bus = new Bus.Builder(validation.getNumber(), validation.getModel(), validation.getOdometer()).build();
            BusExceptionHandler.printSuccess("Автобус успешно добавлен: " + bus);
            return bus;
        } else {
            BusExceptionHandler.printError("Данные для полей не валидны, пропуск");
            return null;
        }
    }
}
