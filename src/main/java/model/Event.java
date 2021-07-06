package model;

import javax.persistence.*;

@Entity
@Table(name = "event")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    String id;

    @Column(name = "type")
    String type;

    @Column(name = "host")
    String host;

    @Column(name = "duration")
    int duration;

    @Column(name = "alert")
    boolean alert;

    String state;
    long timestamp;
}
