package exercise;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

public class App {
    public static void swapKeyValue(KeyValueStorage storage) {
        Map<String, String> copiedStorage = storage.toMap();

        for (Entry<String, String> entry : copiedStorage.entrySet()) {
            String newValue = entry.getKey();
            String newKey = entry.getValue();
            storage.unset(entry.getKey());
            storage.set(newKey, newValue);
        }

//        storage.toMap()
//                .entrySet()
//                .stream()
//                .collect(Collectors.toMap(Map.Entry::getValue, Map.Entry::getKey));

    }
}
