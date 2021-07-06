package model;

import javax.persistence.*;

@Entity
@Table(name = "event")
public class Event {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "type")
    private String type;

    @Column(name = "host")
    private String host;

    @Column(name = "duration")
    private int duration;

    @Column(name = "alert")
    private boolean alert;

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

    public boolean isAlert() {
        return alert;
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

    public void setAlert(boolean alert) {
        this.alert = alert;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id='" + id + '\'' +
                ", type='" + type + '\'' +
                ", host='" + host + '\'' +
                ", duration=" + duration +
                ", alert=" + alert +
                ", state='" + state + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}
