package exercise;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class Validator {
    public static List<String> validate(Address classInstance) {
        List<String> nullContainingFields = new ArrayList<>();

        Field[] fields = classInstance.getClass().getDeclaredFields();
        try {
            for (Field field : fields) {
                boolean isPresent = field.isAnnotationPresent(NotNull.class);
                field.setAccessible(true);
                String fieldValue = (String) field.get(classInstance);

                if (isPresent && fieldValue == null) {
                    nullContainingFields.add(field.getName());
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    return nullContainingFields;
    }
}