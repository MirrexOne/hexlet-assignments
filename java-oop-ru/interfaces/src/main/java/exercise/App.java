package exercise;

import java.util.ArrayList;
import java.util.List;

public class App {
    public static List<String> buildApartmentsList(List<Home> apartments, int outputAmount) {
        List<Home> sortedList = new ArrayList<>(apartments);
        List<String> newList;
        sortedList.sort(Home::compareTo);
        newList = sortedList.stream()
                .map(Home::toString)
                .limit(outputAmount)
                .toList();

        return newList;
    }
}
