package exercise;

import java.util.Map;
import java.util.Set;

public class App {
    public static void swapKeyValue(KeyValueStorage storage) {
        Map<String, String> copiedStorage = storage.toMap();
        Set<Map.Entry<String, String >> storageDataSet = copiedStorage.entrySet();
        storageDataSet.forEach(key -> storage.unset(key.getKey()));
        storageDataSet.forEach(value -> storage.set(value.getValue(), value.getKey()));
    }
}
