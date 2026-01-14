package teamwork.validators;

import teamwork.models.Bus;

import java.util.Arrays;
import java.util.List;

public class Validators {

    private enum FieldName {
        NUMBER("Номер автобуса"),
        MODEL("Модель"),
        ODOMETER("Пробег");

        private final String displayName;

        FieldName(String displayName) {
            this.displayName = displayName;
        }

        public String getDisplayName() {
            return displayName;
        }
    }

    // Константы для ограничений
    public static final int MAX_MODEL_LENGTH = 32;
    public static final int MAX_ODOMETER = 1_000_000;
    public static final String CSV_DELIMITER = ",";

    static {
        ExceptionHandler.setErrorStream(System.out);
    }


    /**
     * Валидирует строку CSV формата
     * @return true если строка валидна, false в противном случае
     */
    public static boolean validateCsvLine(String csvLine) {
        String trimmedLine = csvLine.trim();
        if (trimmedLine.isEmpty()) {
            ExceptionHandler.printError("Строка не может быть пустой");
            return false;
        }

        String[] parts = trimmedLine.split(CSV_DELIMITER, -1);

        if (parts.length != 3) {
            ExceptionHandler.printError(
                    String.format("Неверный формат. Ожидается 3 значения через запятую, получено %d. Строка: '%s'",
                            parts.length, csvLine)
            );
            return false;
        }
        return true;
    }

    /**
     * Парсит строку и возвращает объекты с данными
     * @return объект String[]
     */
    public static String[] parseCsvLine(String csvLine) {
        String trimmedLine = csvLine.trim();
        String[] parts = trimmedLine.split(CSV_DELIMITER, -1);
        return Arrays.stream(parts).map(String::trim).toArray(String[]::new);
    }

    /**
     * Проверяет отдельные данные и возвращает результат
     */
    public static ValidationResult validateAllFields(String[] parsedData) {

        ValidationResult result = new ValidationResult();
        result.setNumberValid(validateNumber(parsedData[0]));
        result.setModelValid(validateModel(parsedData[1]));
        result.setOdometerValid(validateOdometer(parsedData[2]));

        if (result.isAllValid()) {
            result.setNumber(Integer.parseInt(parsedData[0]));
            result.setModel(parsedData[1]);
            result.setOdometer(Integer.parseInt(parsedData[2]));
        }

        return result;
    }
    public static boolean isFieldEmpty(FieldName fieldName, String str) {
        if (str == null || str.trim().isEmpty()) {
            ExceptionHandler.handleValidationException(
                    fieldName.getDisplayName(),
                    "Поле не может быть пустым"
            );
            return false;
        }
        return true;
    }

    /**
     * Валидирует номер автобуса
     */
    public static boolean validateNumber(String numberStr) {
        if (!isFieldEmpty(FieldName.NUMBER, numberStr))
            return false;

        try {
            int number = Integer.parseInt(numberStr);
            if (number <= 0) {
                ExceptionHandler.handleValidationException(
                        FieldName.NUMBER.displayName,
                        String.format("Номер автобуса должен быть положительным числом. Получено: %d", number));
                return false;
            }
            return true;
        } catch (NumberFormatException e) {
            ExceptionHandler.handleValidationException(
                    FieldName.NUMBER.displayName,
                    String.format("Номер должен быть целым числом. Получено: '%s'", numberStr));
            return false;
        }
    }

    /**
     * Валидирует модель автобуса
     */
    public static boolean validateModel(String model) {
        if (!isFieldEmpty(FieldName.MODEL, model))
            return false;

        String trimmedModel = model.trim();
        if (trimmedModel.length() > MAX_MODEL_LENGTH) {
            ExceptionHandler.handleValidationException(
                    FieldName.MODEL.displayName,
                    String.format("Название модели слишком длинное (макс. %d символов). Получено: %d символов. Модель: '%s'",
                            MAX_MODEL_LENGTH, trimmedModel.length(), model)
            );
            return false;
        }

        if (trimmedModel.matches(".*\\d+.*") && !trimmedModel.matches(".*\\D+.*")) {
            ExceptionHandler.handleValidationException(
                    FieldName.MODEL.displayName,
                    String.format("Модель не может состоять только из цифр. Получено: '%s'", trimmedModel)
            );
            return false;
        }

        return true;
    }

    /**
     * Валидирует пробег автобуса
     */
    public static boolean validateOdometer(String odometerStr) {
        if (!isFieldEmpty(FieldName.ODOMETER, odometerStr))
            return false;

        try {
            int odometer = Integer.parseInt(odometerStr);
            if (odometer < 0) {
                ExceptionHandler.handleValidationException(
                        FieldName.ODOMETER.displayName,
                        String.format("Пробег не может быть отрицательным. Получено: %d", odometer)
                );
                return false;
            }

            if (odometer > MAX_ODOMETER) {
                ExceptionHandler.handleValidationException(
                        FieldName.ODOMETER.displayName,
                        String.format("Пробег слишком большой (макс. %d км). Получено: %d",
                                MAX_ODOMETER, odometer)
                );
                return false;
            }
            return true;

        } catch (NumberFormatException e) {
            ExceptionHandler.handleValidationException(
                    FieldName.ODOMETER.displayName,
                    String.format("Пробег должен быть целым числом. Получено: '%s'", odometerStr)
            );
            return false;
        }
    }

    /**
     * Проверяет, является ли строка заголовком CSV
     */
    public static boolean isCsvHeader(String line) {
        if (line == null || line.trim().isEmpty()) {
            return false;
        }

        String lowerLine = line.toLowerCase().trim();
        return lowerLine.contains("number") ||
                lowerLine.contains("model") ||
                lowerLine.contains("odometer") ||
                lowerLine.contains("номер") ||
                lowerLine.contains("модель") ||
                lowerLine.contains("пробег");
    }

    /**
     * Проверяет допустимость названия файла
     */
    public static boolean validateFilename(String filename) {
        if (filename.isEmpty()) {
            ExceptionHandler.printError("Имя файла не может быть пустым");
            return false;
        }

        if (filename.length() > 255) {
            ExceptionHandler.printError("Имя файла слишком длинное (макс. 255 символов)");
            return false;
        }

        // Проверка на запрещённые символы Windows
        String forbiddenChars = "[\\\\/:*?\"<>|]";
        if (filename.matches(".*" + forbiddenChars + ".*")) {
            ExceptionHandler.printError("Имя файла содержит запрещённые символы: \\ / : * ? \" < > |");
            return false;
        }
        // Проверка на точки в начале/конце
        if (filename.startsWith(".") || filename.endsWith(".") || filename.endsWith(" ")) {
            ExceptionHandler.printError("Имя файла не может начинаться или заканчиваться точкой или пробелом");
            return false;
        }
        return true;
    }

    public static boolean validateListSize(List<Bus> list) {
        if (list.isEmpty()) {
            ExceptionHandler.handleValidationException("Размер коллекции", "Коллекция пуста");
            return false;
        }
        return true;
    }
}
