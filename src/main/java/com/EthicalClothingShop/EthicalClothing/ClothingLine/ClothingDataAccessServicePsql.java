package com.EthicalClothingShop.EthicalClothing.ClothingLine;

import org.springframework.jdbc.core.JdbcTemplate;

public class ClothingDataAccessServicePsql implements ClothingDAO{

    private JdbcTemplate jdbcTemplate;

    // constructor
    public ClothingDataAccessServicePsql(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void addClothingItem(ClothingItem clothingItem) {
        String insertSQL = """
                INSERT INTO clothing_item()
                """
    }

    @Override
    public void removeClothingItem(ClothingItem clothingItem) {

    }
}
