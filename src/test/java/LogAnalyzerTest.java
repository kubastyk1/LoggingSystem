import model.Event;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.concurrent.ConcurrentHashMap;

class LogAnalyzerTest {

    private final static String PATH = "src/main/resources/logfile.txt";
    private ConcurrentHashMap<String, Event> eventMap;
    LogAnalyzer logAnalyzer = new LogAnalyzer();

    @BeforeEach
    public void beforeEach() {
        eventMap = logAnalyzer.analyzeLogFromFile(PATH);
    }

    @Test
    public void verifyIfAlertRaisedAfterDurationExceeded() {
        Event event = eventMap.get("B");

        assertTrue(event.getDuration() > event.DURATION_LIMIT_MS);
        assertTrue(event.isTimeLimitExceeded());
    }

    @Test
    public void verifyDurationAndAlertWhenFinishedStateBeforeStarted() {
        Event event = eventMap.get("C");

        assertTrue(event.getDuration() > 0);
        assertFalse(event.isTimeLimitExceeded());
    }

    @Test
    public void verifyIfNotRequiredFieldsFilledProperly() {
        Event event = eventMap.get("A");

        assertEquals(event.getHost(), "HOST-1");
        assertEquals(event.getType(), "APPLICATION_LOG");
    }

    @Test
    public void verifyIfSingleEventDoNotHaveInitOfDBSpecificFields() {
        Event event = eventMap.get("D");

        assertEquals(event.getDuration(), 0);
        assertFalse(event.isTimeLimitExceeded());
    }
}