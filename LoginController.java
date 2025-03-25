package controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JOptionPane;

import database.DatabaseConnection;

public class LoginController {
    public static void login(String email, String password) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "SELECT * FROM users WHERE email = ? AND password = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, email);  // Set email parameter
            stmt.setString(2, password);  // Set password parameter
            
            ResultSet rs = stmt.executeQuery();  // Execute query

            if (rs.next()) {  // If the user exists
                String role = rs.getString("role");  // Retrieve the role
                JOptionPane.showMessageDialog(null, "Logged in as: " + role);
            } else {
                JOptionPane.showMessageDialog(null, "Invalid credentials.");
            }
        } catch (Exception e) {
            System.out.println("❌ Connection Failed: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
