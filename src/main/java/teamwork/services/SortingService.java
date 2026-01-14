package teamwork.services;

import teamwork.models.Bus;
import teamwork.strategies.*;
import teamwork.utils.MenuUtils;
import teamwork.validators.ExceptionHandler;

import java.util.List;

public class SortingService {

    private final FileService fileService;

    public SortingService(FileService fileService) {
        this.fileService = fileService;
    }

    public void sortCollection(InputService inputService, List<Bus> buses) {
        boolean menuActive = true;
        SortManager sortManager = new SortManager();

        while (menuActive) {
            MenuUtils.showSortCollectionMenu();
            int choice = inputService.getIntInput("Выберите способ сортировки: ", 0, 5);

            switch (choice) {
                case 1:
                    sortManager.setStrategy(new BusNumberSortStrategy());
                    performSorting(sortManager, buses, inputService);
                    menuActive = false;
                    break;
                case 2:
                    sortManager.setStrategy(new BusModelSortStrategy());
                    performSorting(sortManager, buses, inputService);
                    menuActive = false;
                    break;
                case 3:
                    sortManager.setStrategy(new BusOdometerSortStrategy());
                    performSorting(sortManager, buses, inputService);
                    menuActive = false;
                    break;
                case 4:
                    sortManager.setStrategy(new MultiFieldSortStrategy());
                    performSorting(sortManager, buses, inputService);
                    menuActive = false;
                    break;
                case 5:
                    evenSorting(sortManager, buses, inputService);
                    inputService.waitForEnter();
                    menuActive = false;
                    break;
                case 0:
                    menuActive = false;
                    break;
            }
        }
    }

    private void evenSorting(SortManager sortManager, List<Bus> buses, InputService inputService) {
        boolean menuActive = true;
        while (menuActive) {
            MenuUtils.showEvenSortingMenu();
            int choice = inputService.getIntInput("Выберите способ сортировки: ", 1, 2);

            switch (choice) {
                case 1:
                    sortManager.setStrategy(
                            new EvenFieldSortStrategy(
                                    Bus::getNumber,
                                    Bus::isNumberEven
                            )
                    );
                    performSorting(sortManager, buses, inputService);
                    menuActive = false;
                    break;
                case 2:
                    sortManager.setStrategy(
                            new EvenFieldSortStrategy(
                                    Bus::getOdometer,
                                    Bus::isOdometerEven
                            )
                    );
                    performSorting(sortManager, buses, inputService);
                    menuActive = false;
                    break;
            }
        }
    }

    private void performSorting(SortManager manager, List<Bus> buses, InputService inputService) {
        // 1. Проверка коллекции
        if (isCollectionEmpty(buses)) {
            return;
        }
        // 2. Получение направления сортировки
        boolean ascending = getSortingDirection(inputService);

        // 3. Выполнение сортировки
        manager.sort(buses, ascending);

        // 4. Отображение результата
        displaySortedCollection(buses);

        // 5. Сохранение в файл
        fileService.saveToFile(buses, manager.getStrategyName());

        // 6. Ожидание подтверждения
        inputService.waitForEnter();
    }

    private boolean isCollectionEmpty(List<Bus> buses) {
        if (buses.isEmpty()) {
            ExceptionHandler.printError("Список пуст");
            return true;
        }
        return false;
    }

    private boolean getSortingDirection(InputService inputService) {
        MenuUtils.showSortingDirectionMenu();
        int choice = inputService.getIntInput("Ваш выбор:", 1, 2);
        return choice == 1;
    }

    private void displaySortedCollection(List<Bus> buses) {
        if (buses.isEmpty()) {
            System.out.println("Коллекция пуста");
        } else {
            for (Bus bus : buses) {
                System.out.println(bus.toString());
            }
            System.out.println("Всего автобусов: " + buses.size());
        }
    }
}
