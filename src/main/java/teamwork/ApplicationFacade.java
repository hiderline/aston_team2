package teamwork;

import teamwork.models.Bus;
import teamwork.services.*;
import teamwork.utils.MenuUtils;

import java.util.List;

public class ApplicationFacade {
    private final InputService inputService;
    private final CollectionService collectionService;
    private final SortingService sortingService;
    private final FileService fileService;
    private final SearchService searchService;

    private List<Bus> buses;
    private boolean running;

    public ApplicationFacade() {
        this.inputService = new InputService();
        this.collectionService = new CollectionService();
        this.fileService = new FileService();
        this.sortingService = new SortingService(fileService);
        this.searchService = new SearchService();
        this.buses = collectionService.getBuses();
        this.running = true;
    }

    public void start() {
        while (running) {
            MenuUtils.showMainMenu();
            int choice = inputService.getIntInput("Выберите действие: ", 0, 6);

            handleMainMenuChoice(choice);
        }
        inputService.closeScanner();
        System.exit(0);
    }

    private void handleMainMenuChoice(int choice) {
        switch (choice) {
            case 1:
                collectionService.fillCollection(inputService);
                break;
            case 2:
                collectionService.displayCollection();
                inputService.waitForEnter();
                break;
            case 3:
                sortingService.sortCollection(inputService, buses);
                break;
            case 4:
                fileService.saveToFile(inputService, buses);
                break;
            case 5:
                searchService.searchByCollection(inputService, buses);
                inputService.waitForEnter();
                break;
            case 6:
                collectionService.clearCollection();
                inputService.waitForEnter();
                break;
            case 0:
                running = false;
                System.out.println("Выход из программы...");
                break;
            default:
                System.out.println("Неверный выбор, повторите попытку.");
        }
    }
}
