package exercise;

import java.util.stream.Collectors;
import java.util.Map;

public abstract class Tag {

    private String tagTitle;
    private Map<String, String> attributes;

    Tag(String tagTitle, Map<String, String> attributes) {
        this.tagTitle = tagTitle;
        this.attributes = attributes;
    }

    public String attributesToString() {
        return attributes.entrySet().stream()
                .map(el -> " %s=\"%s\"".formatted(el.getKey(), el.getValue()))
                .collect(Collectors.joining());
    }

    public String getTagTitle() {
        return tagTitle;
    }
}
