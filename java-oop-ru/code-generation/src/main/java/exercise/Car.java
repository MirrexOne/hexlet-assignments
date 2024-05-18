package exercise;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;
import lombok.Value;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

@Value
@AllArgsConstructor
class Car {
    int id;
    String brand;
    String model;
    String color;
    User owner;

    public String serialize() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();

        return mapper.writeValueAsString(this);
    }

    public static Car unserialize(String stringJson) throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        return mapper.readValue(stringJson, Car.class);
    }
}
