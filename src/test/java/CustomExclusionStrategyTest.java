
import com.fatboyindustrial.gsonjavatime.Converters;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.jupiter.api.Test;
import renatius.GSON.CustomExclusionStrategy;
import renatius.GSON.ExcludeField;

import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CustomExclusionStrategyTest {

    static class Container {
        public String name;
        @ExcludeField
        public String secret;
        public List<String> topics;
    }

    @Test
    void testExclusionByAnnotationAndFieldName() {
        Gson gson = Converters.registerLocalTime(new GsonBuilder())
                .setPrettyPrinting()
                .addSerializationExclusionStrategy(new CustomExclusionStrategy(null))
                .create();

        Container c = new Container();
        c.name = "X";
        c.secret = "TOPSECRET";
        c.topics = List.of("t1","t2");

        String json = gson.toJson(c);
        assertTrue(json.contains("\"name\""));
        assertFalse(json.contains("TOPSECRET"));          // secret должно быть исключено
        assertFalse(json.contains("topics"));            // topics по имени тоже исключается в strategy
    }
}
