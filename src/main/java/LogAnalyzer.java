import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import model.Event;
import services.DatabaseService;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.ConcurrentHashMap;

public class LogAnalyzer {

    private static final int DURATION_LIMIT_MS = 4;
    private ConcurrentHashMap<String, Event> eventMap = new ConcurrentHashMap();
    private DatabaseService<Event> databaseService = new DatabaseService();

    public void analyzeLogFromFile(String filePath) {
        try (
                InputStream inputStream = Files.newInputStream(Path.of(filePath));
                JsonReader reader = new JsonReader(new InputStreamReader(inputStream))
        ) {
            reader.beginArray();
            while (reader.hasNext()) {
                Event event = new Gson().fromJson(reader, Event.class);
                System.out.println(event.toString());

                mapNewEvent(event);
            }
            reader.endArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void mapNewEvent(Event event) {
        if(eventMap.containsKey(event.getId())) {
            long savedTimestamp = eventMap.get(event.getId()).getTimestamp();
            long currentTimestamp = event.getTimestamp();
            int duration = (int) Math.abs(savedTimestamp - currentTimestamp);
            event.setDuration(duration);
            event.setAlert(duration > DURATION_LIMIT_MS);

            databaseService.save(event);
        }
        eventMap.put(event.getId(), event);
    }
}
