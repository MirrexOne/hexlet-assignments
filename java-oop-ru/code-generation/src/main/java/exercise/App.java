package exercise;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;

public class App {
    public static void save(Path pathToFile, Car carObject) throws IOException {
        String serializedObject = carObject.serialize();

        Files.writeString(pathToFile, serializedObject, StandardOpenOption.CREATE);
    }

    public static Car extract(Path pathToFile) throws IOException {
        String fileContent = Files.readString(pathToFile);
        return Car.unserialize(fileContent);
    }
}
