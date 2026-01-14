package teamwork.validators;

import teamwork.models.Bus;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class FileHandler {
    private final Path outputPath;

    public FileHandler() {
        this.outputPath = findProjectRoot().resolve("target/output");
        createOutputDirectory();
    }

    private Path findProjectRoot() {
        Path currentPath = Paths.get("").toAbsolutePath();

        // Вверх, пока не найдем pom.xml
        Path path = currentPath;
        while (path != null && !Files.exists(path.resolve("pom.xml"))) {
            path = path.getParent();
        }

        return path != null ? path : currentPath;
    }

    private void createOutputDirectory() {
        try {
            Files.createDirectories(this.outputPath);
        } catch (IOException e) {
            ExceptionHandler.printError("Не удалось создать директорию: " + e);
        }
    }

    public void writeToFile(List<Bus> collection, String filename) {
        Path filePath = this.outputPath.resolve(filename + ".txt");

        try(BufferedWriter writer = Files.newBufferedWriter(
                filePath,
                StandardOpenOption.CREATE,
                StandardOpenOption.APPEND
                )) {

            for (Bus bus : collection) {
                writer.write(bus.toString());
                writer.newLine();
            }
            writer.write("=".repeat(80));
            writer.flush();
        } catch (IOException e) {
            ExceptionHandler.printError("Ошибка записи в файл: " + e);
        }
    }
}
