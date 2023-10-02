package se.lexicon.model;

import java.time.LocalDateTime;

public class Meeting {
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
        return "Meeting{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", endTime=" + endTime +
                ", startTime=" + startTime +
                '}';
    }
}
