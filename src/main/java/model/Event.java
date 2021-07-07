package model;

import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity
@Table(name = "event")
public class Event {

    public static final int DURATION_LIMIT_MS = 4;
    public static final String TABLE_NAME = "Event";

    @Id
    @Column(name = "id")
    @NotNull
    private String id;

    @Column(name = "type")
    private String type;

    @Column(name = "host")
    private String host;

    @Column(name = "duration")
    @NotNull
    private int duration;

    @Column(name = "timeLimitExceeded")
    @NotNull
    private boolean timeLimitExceeded;

    private String state;
    private long timestamp;

    public String getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getHost() {
        return host;
    }

    public int getDuration() {
        return duration;
    }

    public boolean isTimeLimitExceeded() {
        return timeLimitExceeded;
    }

    public String getState() {
        return state;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void setTimeLimitExceeded(boolean timeLimitExceeded) {
        this.timeLimitExceeded = timeLimitExceeded;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id='" + id + '\'' +
                ", type='" + type + '\'' +
                ", host='" + host + '\'' +
                ", duration=" + duration +
                ", alert=" + timeLimitExceeded +
                ", state='" + state + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}
