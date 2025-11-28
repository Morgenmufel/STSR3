package renatius.GSON;

import com.fatboyindustrial.gsonjavatime.Converters;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalTime;

public class WriteExample {
    public static void main(String[] args) {
        Gson gson = Converters.registerLocalTime(new GsonBuilder())
                .setPrettyPrinting().create();
        Lesson lesson = createLesson();
        List<Lesson> lessons = new ArrayList<>();
        lessons.add(lesson);
        lessons.add(lesson);
        lessons.add(lesson);
        lessons.add(lesson);
        final String jsonData = gson.toJson(lessons);
        try (FileWriter writer = new FileWriter("lesson.json")) {
            writer.write(jsonData);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("GG WP");
    }
    private static Lesson createLesson() {
        Lesson lesson = new Lesson();
        lesson.setSubject("Математика");
        lesson.setDuration(LocalTime.of(0, 45));
        lesson.setTopics(List.of("Дискриминант", "Логарифмы", "Теорема Пифагора"));
        return lesson;
    }
}
