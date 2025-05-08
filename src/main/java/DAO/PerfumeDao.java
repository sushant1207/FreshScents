package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import config.DBConfig;
import model.Perfume;

public class PerfumeDao {
    public List<Perfume> getAllPerfumes() {
        List<Perfume> perfumes = new ArrayList<>();
        
        // Database connection and query to fetch perfumes
        try (Connection conn = DBConfig.getConnection()) {
            String query = "SELECT PerfumeID, PerfumeName, Brand, Price, Stock, Image FROM perfume";  
            try (PreparedStatement stmt = conn.prepareStatement(query);
                 ResultSet rs = stmt.executeQuery()) {

                while (rs.next()) {
                    Perfume perfume = new Perfume();
                    perfume.setPerfumeID(rs.getInt("PerfumeID"));
                    perfume.setPerfumeName(rs.getString("PerfumeName"));
                    perfume.setBrand(rs.getString("Brand"));
                    perfume.setPrice(rs.getDouble("Price"));
                    perfume.setStock(rs.getInt("Stock"));
                    
                    // Handle null values for the image
                    String image = rs.getString("Image");
                    perfume.setImage(image != null ? image : "default.jpg"); 
                    
                    perfumes.add(perfume);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return perfumes;
    }
}
