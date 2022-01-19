package dao_many_to_many.dao.imple;

import dao_many_to_many.dao.Dao;
import dao_many_to_many.entities.CarsDrivers;
import dao_many_to_many.managers.ConnectionManager;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public class CarsDriversDao implements Dao<CarsDrivers> {

    private final Connection con;

    public CarsDriversDao() {
        con = ConnectionManager.getConnection();
    }

    @Override
    public Optional<CarsDrivers> get(int id) {
        //try (PreparedStatement pStatement = con.prepareStatement())

        return Optional.empty();
    }

    @Override
    public List<CarsDrivers> getAll() {
        return null;
    }

    @Override
    public void save(CarsDrivers carsDrivers) {

    }

    @Override
    public void update(CarsDrivers carsDrivers, String[] params) {

    }

    @Override
    public void delete(CarsDrivers carsDrivers) {

    }
}
