package exercise;

import java.util.Map;
import java.util.List;
import java.util.stream.Collectors;

class PairedTag extends Tag {

    private String tagBody;
    private List<Tag> singleTags;

    PairedTag(String tagTitle, Map<String, String> attributes, String tagBody, List<Tag> singleTags) {
        super(tagTitle, attributes);
        this.tagBody = tagBody;
        this.singleTags = singleTags;
    }

    @Override
    public String toString() {
        return String.format("<%s%s>%s%s</%1$s>", getTagTitle(), attributesToString(), getTagBody(), getSingleTags());
    }

    public String getTagBody() {
        return tagBody;
    }

    public String getSingleTags() {
        return singleTags.stream()
                .map(String::valueOf)
                .collect(Collectors.joining());
    }
}