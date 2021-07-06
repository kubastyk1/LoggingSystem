import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import model.Event;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;

public class LogAnalyzer {

    public void analyzeLogFromFile(String filePath) {
        try (
                InputStream inputStream = Files.newInputStream(Path.of(filePath));
                JsonReader reader = new JsonReader(new InputStreamReader(inputStream));
        ) {
            reader.beginArray();
            while (reader.hasNext()) {
                Event event = new Gson().fromJson(reader, Event.class);
                System.out.println(event.toString());
            }
            reader.endArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
