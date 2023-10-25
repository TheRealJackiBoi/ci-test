package dat3.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.IOException;

public class ChuckJokeFetcher {
    public static String getRandomJoke() throws IOException {
        JsonObject joke = gson.fromJson(HTTPUtils.fetchData("https://api.chucknorris.io/jokes/random"), JsonObject.class);
        return joke.get("value").getAsString();
    }

    static Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .create();
}


