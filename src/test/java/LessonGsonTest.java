
import com.fatboyindustrial.gsonjavatime.Converters;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.junit.jupiter.api.Test;
import renatius.GSON.Lesson;

import java.lang.reflect.Type;
import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class LessonGsonTest {

    @Test
    void testSerializeDeserializeLessonList() {
        Gson gson = Converters.registerLocalTime(new GsonBuilder()).create();

        Lesson lesson = new Lesson();
        lesson.setSubject("Математика");
        lesson.setDuration(LocalTime.of(0,45));
        lesson.setTopics(List.of("Дискриминант", "Логарифмы"));

        List<Lesson> list = List.of(lesson, lesson);

        String json = gson.toJson(list);

        Type listType = new TypeToken<List<Lesson>>() {}.getType();
        List<Lesson> read = gson.fromJson(json, listType);

        assertEquals(2, read.size());
        assertEquals(lesson.getSubject(), read.get(0).getSubject());
        assertEquals(lesson.getDuration(), read.get(0).getDuration());
        assertEquals(lesson.getTopics(), read.get(0).getTopics());
    }
}

