package com.EthicalClothingShop.EthicalClothing.ClothingLine;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ClothingDataAccessServicePsql implements ClothingDAO{

    private JdbcTemplate jdbcTemplate;

    // constructor
    public ClothingDataAccessServicePsql(JdbcTemplate jdbcTemplate) {

        this.jdbcTemplate = jdbcTemplate;
    }

    //methods
    @Override
    public void addClothingItem(ClothingItem clothingItem) {
        int material_id = jdbcTemplate.queryForObject("SELECT material_id FROM materials WHERE material_name LIKE 'mango leather'", Integer.class);
        System.out.println(material_id);
//        String insertSQL = """
//                INSERT INTO clothing_item()
//                """
    }

    @Override
    public void removeClothingItem(ClothingItem clothingItem) {

    }
}
