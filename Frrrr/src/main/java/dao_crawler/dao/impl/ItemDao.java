package dao_crawler.dao.impl;

import dao_crawler.dao.Dao;
import dao_crawler.entities.Item;
import dao_crawler.managers.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ItemDao implements Dao<Item> {

    private final Connection con;

    public ItemDao() {
        con = ConnectionManager.getConnection();
    }


    @Override
    public Optional<Item> get(int id) {
        String GET_ITEM_BY_ID_QUERY = "SELECT * FROM item WHERE id = ?";
        try (PreparedStatement pStatement = con.prepareStatement(GET_ITEM_BY_ID_QUERY)) {
            pStatement.setInt(1, id);

            ResultSet selection = pStatement.executeQuery();
            if (selection.next()) {
                return Optional.of(new Item(selection.getInt("id"),
                        selection.getString("name"),
                        selection.getDouble("price")));
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Can't get item by id from DB" + ex);
        }
        return Optional.empty();
    }

    @Override
    public List<Item> getAll() {
        List<Item> items = new ArrayList<>();
        String GET_ALL_ITEMS_QUERY = "SELECT * FROM item";
        try (PreparedStatement pStatement = con.prepareStatement(GET_ALL_ITEMS_QUERY)) {
            ResultSet selection = pStatement.executeQuery();
            while (selection.next()) {
                items.add(new Item(selection.getInt("id"),
                        selection.getString("name"),
                        selection.getDouble("price")));
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Can't get all items from DB" + ex);
        }
        return items;
    }

    @Override
    public void save(Item item) {
        String SAVE_ITEM_QUERY = "INSERT INTO item (id, name, price) VALUES (?, ?, ?)";
        try (PreparedStatement pStatement = con.prepareStatement(SAVE_ITEM_QUERY)) {
            pStatement.setInt(1, item.getId());
            pStatement.setString(2, item.getName());
            pStatement.setDouble(3, item.getPrice());

            pStatement.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException("Can't save item in DB" + ex);
        }
    }

    @Override
    public void update(Item item, String[] params) {
        String UPDATE_ITEM_QUERY = "UPDATE item SET name = ?, price = ? WHERE id = ?";
        try (PreparedStatement pStatement = con.prepareStatement(UPDATE_ITEM_QUERY)) {
            pStatement.setString(1, item.getName());
            pStatement.setDouble(2, item.getPrice());
            pStatement.setInt(3, item.getId());

            pStatement.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException("Can't update item in DB" + ex);
        }
    }

    @Override
    public void delete(Item item) {
        String DELETE_ITEM_QUERY = "DELETE FROM item WHERE id = ?";
        try (PreparedStatement pStatement = con.prepareStatement(DELETE_ITEM_QUERY)) {
            pStatement.setInt(1, item.getId());

            pStatement.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException("Can't delete item from DB" + ex);
        }
    }
}
