package dat3;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import io.javalin.*;
import io.javalin.apibuilder.*;

import java.util.HashMap;
import java.util.Map;

import static io.javalin.apibuilder.ApiBuilder.get;
import static io.javalin.apibuilder.ApiBuilder.path;

public class PersonResource {

    private static Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private static PersonController pc = new PersonController();
    static Map<Integer, Person> persons = new HashMap<>() {
        {
            put(1, new Person("Kurt", "Wonnegut", 1922));
            put(2, new Person("Hanne", "Wonnegut", 1952));
            put(3, new Person("Jan", "Olsen", 1942));
            put(4, new Person("Ib", "Udsen", 1932));
            put(5, new Person("Jan", "Udsen", 1942));
        }
    };
    public static void main(String[] args) {

        Javalin app = Javalin.create().start(7007);

        app.routes(getPersonResource());
        app.error(404, ctx -> {
            ctx.result("404 - Not found");
        });
        app.exception(Exception.class, (e, ctx) -> {
            ctx.status(500);
            JsonObject exception = new JsonObject();
            exception.addProperty("msg", e.getMessage());
            ctx.json("500 - " + gson.toJson(exception));
        });
    }

    public static EndpointGroup getPersonResource() {
        return () -> {
            path("/persons", () -> {
                get("/", pc.getAllPersons());
                get("/query-param-demo", ctx -> {
                    String name = ctx.queryParam("name");
                    int age = Integer.parseInt(ctx.queryParam("age"));
                    ctx.json("{\"name\": \"" + name + "\", \"age\": " + age + "}");
                });
                get("/headers-demo", ctx -> {
                    String header = ctx.header("x-my-header");
                    ctx.json("{\"x-my-header\": \"" + header + "\"}");
                });
                get("/{id}", pc.getPersonById());

            });
        };
    }
}
