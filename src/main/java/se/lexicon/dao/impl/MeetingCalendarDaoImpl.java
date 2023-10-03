package se.lexicon.dao.impl;

import se.lexicon.dao.MeetingCalendarDao;
import se.lexicon.exception.MySQLException;
import se.lexicon.model.MeetingCalendar;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class MeetingCalendarDaoImpl implements MeetingCalendarDao {
    private Connection connection;

    public MeetingCalendarDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public MeetingCalendar createMeetingCalendar(String title, String username) {
        try (
                PreparedStatement stmt = connection.prepareStatement("INSERT INTO MEETING_CALENDARS(USERNAME, TITLE) VALUES(?, ?)", PreparedStatement.RETURN_GENERATED_KEYS)
        ) {
            stmt.setString(1, username);
            stmt.setString(2, title);

            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted == 0) {
                throw new MySQLException("Creation of meeting calendar failed, no rows affected.");
            }

            try (ResultSet generated = stmt.getGeneratedKeys()) {
                if (generated.next()) {
                    return new MeetingCalendar(generated.getInt(1), username, title);
                } else {
                    throw new MySQLException("No generated keys returned from database while inserting new meeting calendar.");
                }
            }
        } catch (SQLException e) {
            throw new MySQLException("Error occurred while creating meeting calendar: username= " + username + ", title=" + title, e);

        }
    }

    @Override
    public Optional<MeetingCalendar> findById(int id) {
        try (
                PreparedStatement stmt = connection.prepareStatement("SELECT * FROM MEETING_CALENDARS WHERE ID = ?")
        ) {
            stmt.setInt(1, id);

            try(ResultSet result = stmt.executeQuery()) {
                if (result.next()) {
                    String title = result.getString("TITLE");
                    String username = result.getString("USERNAME");
                    return Optional.of(new MeetingCalendar(id, username, title));
                } else {
                    return Optional.empty();
                }
            }
        } catch (SQLException e) {
            throw new MySQLException("Error occurred while finding meeting calendar by id: " + id, e);
        }
    }

    @Override
    public Collection<MeetingCalendar> findByUsername(String username) {
        List<MeetingCalendar> result = new ArrayList<>();

        try (
                PreparedStatement stmt = connection.prepareStatement("SELECT * FROM MEETING_CALENDARS WHERE USERNAME = ?")
        ) {
            stmt.setString(1, username);
            try(ResultSet resultSet = stmt.executeQuery()) {
                while (resultSet.next()) {
                    String title = resultSet.getString("TITLE");
                    String usernameFromDB = resultSet.getString("USERNAME");
                    int id = resultSet.getInt("ID");

                    result.add(new MeetingCalendar(id, usernameFromDB, title));
                }
            }
        } catch (SQLException e) {
            throw new MySQLException("Error occurred while finding meeting calendar by username: " + username, e);
        }

        return result;
    }

    @Override
    public Optional<MeetingCalendar> findByTitle(String title) {
        try (
                PreparedStatement stmt = connection.prepareStatement("SELECT * FROM MEETING_CALENDARS WHERE TITLE = ?")
        ) {
            stmt.setString(1, title);

            try(ResultSet result = stmt.executeQuery()) {
                if (result.next()) {
                    String titleFromDB = result.getString("TITLE");
                    String username = result.getString("USERNAME");
                    int id = result.getInt("ID");

                    return Optional.of(new MeetingCalendar(id, username, titleFromDB));
                } else {
                    return Optional.empty();
                }
            }
        } catch (SQLException e) {
            throw new MySQLException("Error occurred while finding meeting calendar by title: " + title, e);
        }
    }

    @Override
    public boolean deleteCalendar(int id) {
        return false;
    }
}
