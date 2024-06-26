package exercise;

public class LabelTag implements TagInterface {

    private String tagValue;
    private TagInterface subsidiaryTag;

    public LabelTag(String tagValue, TagInterface subsidiaryTag) {
        this.tagValue = tagValue;
        this.subsidiaryTag = subsidiaryTag;
    }

    @Override
    public String render() {
        return "<label>" + tagValue
                + subsidiaryTag.render()
                + "</label>";
    }
}
