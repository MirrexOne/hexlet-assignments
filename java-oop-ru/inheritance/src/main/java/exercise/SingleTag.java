package exercise;

import java.util.Map;

public class SingleTag extends Tag {

    public SingleTag(String tagTitle, Map<String, String> attributes) {
        super(tagTitle, attributes);
    }

    @Override
    public String toString() {
        return String.format("<%s%s>", getTagTitle(), attributesToString());
    }
}
