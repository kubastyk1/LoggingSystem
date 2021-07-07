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
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class LogAnalyzer {

    private static final int THREAD_POOL_NUMBER = 5;

    private ConcurrentHashMap<String, Event> eventMap = new ConcurrentHashMap();
    private DatabaseService<Event> databaseService = new DatabaseService();

    public void analyzeLogFromFile(String filePath) {

        ExecutorService executorService = Executors.newFixedThreadPool(THREAD_POOL_NUMBER);

        try (
                InputStream inputStream = Files.newInputStream(Path.of(filePath));
                JsonReader reader = new JsonReader(new InputStreamReader(inputStream))
        ) {
            reader.beginArray();
            while (reader.hasNext()) {
                Event event = new Gson().fromJson(reader, Event.class);
                executorService.execute(() -> {
                    Event originalEvent = mapEvent(event);
                    if(originalEvent != null)
                        databaseService.save(event);
                });
            }
            reader.endArray();
        } catch (IOException e) {
            e.printStackTrace();
        }

        databaseService.printRecords(Event.TABLE_NAME);
    }

    private Event mapEvent(Event event) {
        if(eventMap.containsKey(event.getId())) {
            updateEventInformation(event);
        }
        return eventMap.put(event.getId(), event);
    }

    private void updateEventInformation(Event event) {
        long savedTimestamp = eventMap.get(event.getId()).getTimestamp();
        long currentTimestamp = event.getTimestamp();
        int duration = (int) Math.abs(savedTimestamp - currentTimestamp);
        event.setDuration(duration);
        event.setAlert(duration > event.DURATION_LIMIT_MS);
    }
}
