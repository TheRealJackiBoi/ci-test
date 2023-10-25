package dat3;

import io.javalin.apibuilder.EndpointGroup;
import io.javalin.http.Handler;

import java.util.HashMap;
import java.util.Map;

public class PersonController {

    Map<Integer, Person> persons = new HashMap<>();

    public PersonController() {
        persons.put(1, new Person("Kurt", "Wonnegut", 1922));
        persons.put(2, new Person("Hanne", "Wonnegut", 1952));
        persons.put(3, new Person("Jan", "Olsen", 1942));
        persons.put(4, new Person("Ib", "Udsen", 1932));
        persons.put(5, new Person("Jan", "Udsen", 1942));
    }

    public Handler getAllPersons() {
        return ctx -> {
            ctx.json(persons);
        };
    }

    public Handler getPersonById() {
        return ctx -> {
            int id = Integer.parseInt(ctx.pathParam("id"));

            if (persons.containsKey(id)) {
                ctx.json(persons.get(id));
            } else {
                ctx.status(404).json("{\"message\": \"No person with id " + id + " found\"}");
            }
        };
    }
}
