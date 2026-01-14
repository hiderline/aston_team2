package teamwork.services;

import teamwork.models.Bus;
import teamwork.validators.ExceptionHandler;
import teamwork.utils.FileHandler;
import teamwork.validators.Validators;

import java.util.List;

public class FileService {

    public void saveToFile(InputService inputService, List<Bus> buses) {
        if (!Validators.validateListSize(buses))
            return;
        String filename = inputService.getStringInput("Введите название файла (макс. 255 символов)");

        if (filename.isEmpty()) {
            ExceptionHandler.printError("Не указано имя файла...");
        }

        saveToFile(buses, filename);
    }

    public void saveToFile(List<Bus> buses, String filename) {

        if (Validators.validateFilename(filename)) {
            FileHandler fileHandler = new FileHandler();
            fileHandler.writeToFile(buses, filename);
        }
    }
}
