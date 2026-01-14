package teamwork.services;

import teamwork.models.Bus;
import teamwork.validators.ExceptionHandler;
import teamwork.utils.FileHandler;
import teamwork.validators.Validators;

import java.util.List;

public class FileService {
    public void saveToFile(InputService inputService, List<Bus> buses) {
        String filename = inputService.getStringInput("Введите название файла (без расширения)");

        if (filename.isEmpty()) {
            ExceptionHandler.printError("Не указано имя файла...");
        }

        if (Validators.validateFilename(filename)) {
            FileHandler fileHandler = new FileHandler();
            fileHandler.writeToFile(buses, filename);
        }
    }

    public void saveToFile(List<Bus> buses, String filename) {
        if (filename.isEmpty()) {
            ExceptionHandler.printError("Не указано имя файла...");
        }

        if (Validators.validateFilename(filename)) {
            FileHandler fileHandler = new FileHandler();
            fileHandler.writeToFile(buses, filename);
        }
    }
}
