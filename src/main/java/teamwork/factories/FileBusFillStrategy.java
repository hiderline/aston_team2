package teamwork.factories;

import teamwork.models.Bus;
import teamwork.validators.ExceptionHandler;
import teamwork.validators.Validators;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileBusFillStrategy implements BusFillStrategy {
    public final static String FILENAME = "data.csv";

    @Override
    public List<Bus> fillBuses(int size) {
        List<Bus> buses = new ArrayList<>();

        try (InputStream is = getClass().getClassLoader().getResourceAsStream(FILENAME)) {
            if (is == null) {
                throw new FileNotFoundException("Файл " + FILENAME + " не найден в ресурсах");
            }
            Scanner scanner = new Scanner(is, "UTF-8");

            while (buses.size() < size && scanner.hasNextLine()) {
                String line = scanner.nextLine();

                if (Validators.isCsvHeader(line) || line.isEmpty())
                    continue;

                Bus bus = BusCreator.createBusFromLine(line); // true - пропускать заголовки
                if (bus != null) {
                    buses.add(bus);
                }

            }
        } catch (FileNotFoundException e) {
            ExceptionHandler.printError("Файл data.csv не найден");
        } catch (IOException e) {
            ExceptionHandler.handleException(e, "Непредвиденная ошибка");
        }
        ExceptionHandler.printInfo(buses.size() + " добавлено из файла");
        return buses;
    }
}