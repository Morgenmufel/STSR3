package renatius.GSON;

import com.fatboyindustrial.gsonjavatime.Converters;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.Reader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

public class ReadExample {

    public static void main(String[] args) {
        Gson gson = Converters
                .registerLocalTime(new GsonBuilder())
                .create();
        try (Reader reader = new FileReader("lesson.json")) {
            Type listType = new TypeToken<List<Lesson>>() {}.getType();
            List<Lesson> lessons = gson.fromJson(reader, listType);
            System.out.println(lessons);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}