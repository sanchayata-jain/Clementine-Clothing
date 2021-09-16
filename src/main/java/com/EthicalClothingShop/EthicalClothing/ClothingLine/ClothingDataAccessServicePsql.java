package com.EthicalClothingShop.EthicalClothing.ClothingLine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


@Repository
public class ClothingDataAccessServicePsql implements ClothingDAO {

    private JdbcTemplate jdbcTemplate;

    // constructor
    @Autowired
    public ClothingDataAccessServicePsql(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    //methods
    public void createClothingInventoryViewTable() {
        String dropViewTableQuery = """
                DROP VIEW IF EXISTS clothing_inventory;
                """;
        jdbcTemplate.execute(dropViewTableQuery);

        String createClothingInventoryView = """
                CREATE TEMP VIEW clothing_inventory
                AS
                SELECT clothing_items_inventory.clothing_id, type_name, subtype_name, size_name,
                color_name, material_name, quantity, unit_retail_price
                FROM clothing_items_inventory
                INNER JOIN clothing_types
                ON clothing_types.type_id = clothing_items_inventory.type_id
                INNER JOIN clothing_subtypes
                ON clothing_subtypes.subtype_id = clothing_items_inventory.subtype_id
                INNER JOIN sizes
                ON sizes.size_id = clothing_items_inventory.size_id
                INNER JOIN colors
                ON colors.color_id = clothing_items_inventory.color_id
                INNER JOIN materials
                ON materials.material_id = clothing_items_inventory.material_id
                """;

        jdbcTemplate.execute(createClothingInventoryView);

    }

    public List<ClothingItem> getClothingItems() {
        createClothingInventoryViewTable();
        List<ClothingItem> allClothingItems = jdbcTemplate.query("SELECT * FROM clothing_inventory", new ClothingItemMapper());

        return allClothingItems;
    }

    private static final class ClothingItemMapper implements RowMapper<ClothingItem> {
        @Override
        public ClothingItem mapRow(ResultSet rs, int rowNum) throws SQLException {
            ClothingItem clothingItem = new ClothingItem(rs.getInt("clothing_id"), rs.getString("type_name"),
                                                         rs.getString("subtype_name"), rs.getString("size_name"),
                                                         rs.getString("color_name"), rs.getString("material_name"),
                                                         rs.getInt("quantity"), rs.getInt("unit_retail_price"));
            return clothingItem;
        }
    }

    @Override
    public void addClothingItem(ClothingItem clothingItem) {
        // Adding a clothing item record into the database
        String get_type_id_query = ("SELECT type_id FROM clothing_types WHERE type_name LIKE " + "'" + clothingItem.getType() + "'");
        int type_id = jdbcTemplate.queryForObject(get_type_id_query, Integer.class);
        System.out.println("type id " + type_id);

        String get_subtype_id_query = ("SELECT subtype_id FROM clothing_subtypes WHERE subtype_name LIKE " + "'" + clothingItem.getSubtype() + "'");
        int subtype_id = jdbcTemplate.queryForObject(get_subtype_id_query, Integer.class);
        System.out.println("subtype id " + subtype_id);

        int size_id = 0;
        try {
            String get_size_id_query = ("SELECT size_id FROM sizes WHERE size_name LIKE " + "'" + clothingItem.getSize() + "'");
            size_id = jdbcTemplate.queryForObject(get_size_id_query, Integer.class);
            System.out.println("size id " + size_id);
        } catch (Exception e) {
            System.out.println("Our Store does not currently store the size entered");
        }

        String get_color_id_query = ("SELECT color_id FROM colors WHERE color_name LIKE " + "'" + clothingItem.getColor() + "'");
        int color_id = jdbcTemplate.queryForObject(get_color_id_query, Integer.class);
        System.out.println("color id " + color_id);

        String get_material_id_query = ("SELECT material_id FROM materials WHERE material_name LIKE " + "'" + clothingItem.getMaterial() + "'");
        int material_id = jdbcTemplate.queryForObject(get_material_id_query, Integer.class);
        System.out.println("material id " + material_id);

        String addNewClothingItemRecord = """
        INSERT INTO clothing_items_inventory(clothing_id, type_id, subtype_id,
                                              size_id, color_id, material_id,
                                              quantity, unit_retail_price) 
                                              VALUES(?, ?, ?, ?, ?, ?, ?, ?)
        """;
        jdbcTemplate.update(addNewClothingItemRecord,clothingItem.getId(), type_id, subtype_id,
                                        size_id, color_id, material_id, clothingItem.getQuantity(),
                                        clothingItem.getPrice());
    }


    @Override
    public void removeClothingItem(ClothingItem clothingItem) {
        jdbcTemplate.update("DELETE FROM clothing_items_inventory WHERE clothing_id = " + clothingItem.getId());
    }


    public void updateClothingItem(double price, int quantity, int clothingId) {

        String updateClothingItemQuantity = "UPDATE clothing_items_inventory SET quantity = " + "" + quantity + "" + " WHERE " +
                "clothing_id = " + "" + clothingId + "";

        jdbcTemplate.update(updateClothingItemQuantity);

        String updateClothingItemPrice = "UPDATE clothing_items_inventory SET price = " + "" + price + "" + " WHERE " +
                "clothing_id = " + "" + clothingId + "";

        jdbcTemplate.update(updateClothingItemPrice);
    }
}
