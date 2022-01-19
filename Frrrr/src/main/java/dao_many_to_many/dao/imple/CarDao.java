package dao_many_to_many.dao.imple;

import dao_many_to_many.dao.Dao;
import dao_many_to_many.entities.Car;
import dao_many_to_many.managers.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CarDao implements Dao<Car> {

    private final Connection con;


    public CarDao() {
        con = ConnectionManager.getConnection();
    }

    @Override
    public Optional<Car> get(int id) {
        String GET_CAR_BY_ID_QUERY = "SELECT * FROM car WHERE id=?";

        try (PreparedStatement pStatement = con.prepareStatement(GET_CAR_BY_ID_QUERY)) {
            pStatement.setInt(1, id);

            ResultSet selection = pStatement.executeQuery();
            if (selection.next()) {
                return Optional.of(new Car(selection.getInt("id"),
                        selection.getString("model"),
                        selection.getString("color")));
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Can't get car by id", ex);
        }
        return Optional.empty();
    }

    @Override
    public List<Car> getAll() {
        String GET_ALL_CARS_QUERY = "SELECT * FROM car";
        List<Car> cars = new ArrayList<>();

        try (PreparedStatement pStatement = con.prepareStatement(GET_ALL_CARS_QUERY)) {
            ResultSet selection = pStatement.executeQuery();
            while (selection.next()) {
                cars.add(new Car(selection.getInt("id"),
                        selection.getString("model"),
                        selection.getString("color")));
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Can't get all cars", ex);
        }
        return cars;
    }

    @Override
    public void save(Car car) {
        String SAVE_CAR_QUERY = "INSERT INTO car (id, model, color) VALUES (?, ?, ?)";

        try (PreparedStatement pStatement = con.prepareStatement(SAVE_CAR_QUERY)) {
            pStatement.setInt(1, car.getID());
            pStatement.setString(2, car.getModel());
            pStatement.setString(3, car.getColor());

            pStatement.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException("Can't save car", ex);
        }
    }

    @Override
    public void update(Car car, String[] params) {
        String UPDATE_CAR_QUERY = "UPDATE car SET model = ?, color = ? WHERE id = ?";
        try (PreparedStatement pStatement = con.prepareStatement(UPDATE_CAR_QUERY)) {
            pStatement.setString(1, car.getModel());
            pStatement.setString(2, car.getColor());
            pStatement.setInt(3, car.getID());

            pStatement.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException("Can't update car", ex);
        }
    }

    @Override
    public void delete(Car car) {
        String DELETE_CAR_QUERY = "DELETE FROM car WHERE id = ?";
        try (PreparedStatement pStatement = con.prepareStatement(DELETE_CAR_QUERY)) {
            pStatement.setInt(1, car.getID());

            pStatement.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException("Can't delete car", ex);
        }
    }
}
