package dat3;

import dat3.utils.ChuckJokeFetcher;
import io.javalin.Javalin;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        Javalin app = Javalin.create().start(7007);
        app.get("/hello", ctx -> ctx.result("Hello World"));
        app.get("/hello/{name}", ctx -> ctx.result("Hello " + ctx.pathParam("name")));
        app.get("/today", ctx -> ctx.result("Today is " + LocalDate.now().toString()));
        app.get("/chuck-joke", ctx -> ctx.result(ChuckJokeFetcher.getRandomJoke()));
    }
}