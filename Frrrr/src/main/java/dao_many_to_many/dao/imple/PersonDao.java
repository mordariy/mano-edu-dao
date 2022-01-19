package dao_many_to_many.dao.imple;

import dao_many_to_many.dao.Dao;
import dao_many_to_many.entities.Person;
import dao_many_to_many.managers.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PersonDao implements Dao<Person> {

    private final Connection con;


    public PersonDao() {
        con = ConnectionManager.getConnection();
    }

    @Override
    public Optional<Person> get(int id) {
        String GET_PERSON_BY_ID_QUERY = "SELECT * FROM person WHERE id=?";

        try (PreparedStatement pStatement = con.prepareStatement(GET_PERSON_BY_ID_QUERY)) {
            pStatement.setInt(1, id);

            ResultSet selection = pStatement.executeQuery();
            if (selection.next()) {
                return Optional.of(new Person(selection.getInt("id"),
                        selection.getString("name"),
                        selection.getString("surname")));
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Can't get person by id", ex);
        }
        return Optional.empty();
    }

    @Override
    public List<Person> getAll() {
        String GET_ALL_PEOPLE_QUERY = "SELECT * FROM person";
        List<Person> people = new ArrayList<>();

        try (PreparedStatement pStatement = con.prepareStatement(GET_ALL_PEOPLE_QUERY)) {
            ResultSet selection = pStatement.executeQuery();
            while (selection.next()) {
                people.add(new Person(selection.getInt("id"),
                        selection.getString("name"),
                        selection.getString("surname")));
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Can't get all people", ex);
        }
        return people;
    }

    @Override
    public void save(Person person) {
        String SAVE_PERSON_QUERY = "INSERT INTO person (id, name, surname) VALUES (?, ?, ?)";

        try (PreparedStatement pStatement = con.prepareStatement(SAVE_PERSON_QUERY)) {
            pStatement.setInt(1, person.getID());
            pStatement.setString(2, person.getName());
            pStatement.setString(3, person.getSurname());

            pStatement.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException("Can't save person", ex);
        }
    }

    @Override
    public void update(Person person, String[] params) {
        String UPDATE_PERSON_QUERY = "UPDATE person SET name = ?, surname = ? WHERE id = ?";
        try (PreparedStatement pStatement = con.prepareStatement(UPDATE_PERSON_QUERY)) {
            pStatement.setString(1, person.getName());
            pStatement.setString(2, person.getSurname());
            pStatement.setInt(3, person.getID());

            pStatement.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException("Can't update person", ex);
        }
    }

    @Override
    public void delete(Person person) {
        String DELETE_PERSON_QUERY = "DELETE FROM person WHERE id = ?";
        try (PreparedStatement pStatement = con.prepareStatement(DELETE_PERSON_QUERY)) {
            pStatement.setInt(1, person.getID());

            pStatement.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException("Can't delete person", ex);
        }
    }
}
