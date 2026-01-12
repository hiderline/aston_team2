package teamwork.factories;

import teamwork.models.Bus;
import teamwork.validators.BusExceptionHandler;
import teamwork.validators.BusValidator;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileBusFillStrategy extends BaseBusFillStrategy {
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

                if (BusValidator.isCsvHeader(line) || line.isEmpty())
                    continue;

                Bus bus = createBusFromLine(line); // true - пропускать заголовки
                if (bus != null) {
                    buses.add(bus);
                }

            }
        } catch (FileNotFoundException e) {
            BusExceptionHandler.printError("Файл data.csv не найден");
        } catch (IOException e) {
            BusExceptionHandler.handleException(e, "Непредвиденная ошибка");
        }
        BusExceptionHandler.printInfo(buses.size() + " добавлено из файла");
        return buses;
    }
}