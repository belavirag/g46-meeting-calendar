package se.lexicon.model;

import java.util.List;

public class MeetingCalendar {
    private int id;
    private String title, username;
    private List<Meeting> meetings;

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getUsername() {
        return username;
    }

    public List<Meeting> getMeetings() {
        return meetings;
    }

    public void addMeeting(Meeting meeting) {
        this.meetings.add(meeting);
    }

    public void removeMeeting(Meeting meeting) {
        this.meetings.remove(meeting);
    }

    public void displayCalendarInfo() {
        System.out.println("Meeting calendar info: " + this.toString());
        System.out.println("Meeting list:");
        this.meetings.forEach(System.out::println);
    }

    @Override
    public String toString() {
        return "MeetingCalendar{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}
