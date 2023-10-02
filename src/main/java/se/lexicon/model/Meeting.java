package se.lexicon.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Meeting {
    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private int id;
    private String title, description;
    private LocalDateTime endTime, startTime;
    private MeetingCalendar calendar;

    public Meeting(int id, String title, String description, LocalDateTime endTime, LocalDateTime startTime, MeetingCalendar calendar) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.endTime = endTime;
        this.startTime = startTime;
        this.calendar = calendar;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public MeetingCalendar getCalendar() {
        return calendar;
    }

    public void displayMeetingInfo() {
        System.out.println(this.toString());
    }

    @Override
    public String toString() {
        return "Meeting from " + this.getStartTime().format(DATE_TIME_FORMATTER) + " to " + this.getEndTime().format(DATE_TIME_FORMATTER) + " title: " + this.getTitle() + ", desc = " + this.getDescription();
    }
}
