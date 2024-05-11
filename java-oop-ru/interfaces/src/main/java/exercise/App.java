package exercise;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class App {
    public static List<String> buildApartmentsList(List<Home> apartments, int outputAmount) {
        List<Home> sortedList = new ArrayList<>(apartments);
        List<String> newList;
        sortedList.sort(Home::compareTo);
        newList = sortedList.stream()
                .map(Object::toString)
                .limit(outputAmount)
                .toList();


        return newList;
    }
}
