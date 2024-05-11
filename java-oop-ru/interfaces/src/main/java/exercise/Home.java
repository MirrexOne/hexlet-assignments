package exercise;

public interface Home {
    double getArea();

    default int compareTo(Home another) {
        if (getArea() > another.getArea()) {
            return 1;
        } else if (getArea() < another.getArea()) {
            return -1;
        }

        return 0;
    }
}
