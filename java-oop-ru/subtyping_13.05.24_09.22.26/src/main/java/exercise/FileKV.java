package exercise;

import java.util.Map;

public class FileKV implements KeyValueStorage {

    private String pathToFile;

    public FileKV(String pathToFile, Map<String, String> initialData) {
        this.pathToFile = pathToFile;
        initialData.entrySet()
                .stream()
                .forEach(entry -> set(entry.getKey(), entry.getValue()));
    }

    @Override
    public void set(String key, String value) {
        String content = Utils.readFile(pathToFile);
        Map<String,String> data = Utils.unserialize(content);
        data.put(key, value);
        Utils.writeFile(pathToFile, Utils.serialize(data));
    }

    @Override
    public void unset(String key) {
        String content = Utils.readFile(pathToFile);
        Map<String, String> data = Utils.unserialize(content);
        data.remove(key);
        Utils.writeFile(pathToFile, Utils.serialize(data));
    }

    @Override
    public String get(String key, String defaultValue) {
        String content = Utils.readFile(pathToFile);
        Map<String, String> data = Utils.unserialize(content);
        return data.containsKey(key) ? data.get(key) : defaultValue;
    }

    @Override
    public Map<String, String> toMap() {
        String content = Utils.readFile(pathToFile);
        Map<String, String> data = Utils.unserialize(content);
        return data;
    }
}
