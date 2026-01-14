package teamwork.services;

import teamwork.models.Bus;
import teamwork.utils.FindByCollection;
import teamwork.validators.ExceptionHandler;

import java.util.List;

public class SearchService {

    public void searchByCollection(InputService inputService, List<Bus> buses) {
        boolean repeat = true;
        int threadsAmount = 0;
        int busesSize = buses.size();
        if (busesSize > 0) {
            while (repeat) {
                threadsAmount = inputService.getIntInput(
                        String.format("Укажите кол-во потоков (от %d до %d)", 1, busesSize));
                if (threadsAmount > 1 && threadsAmount <= busesSize) {
                    repeat = false;
                } else {
                    ExceptionHandler.printError("Неверное количество потоков.");
                }
            }
            FindByCollection.findByValue(buses, threadsAmount);
        } else {
            ExceptionHandler.printWarning("Для поиска заполните коллекцию");
        }
    }
}
