package org.example.db;

import org.example.entities.User;

import javax.swing.*;
import java.sql.*;


public class UserDB {
    private Connection conexao;

    public UserDB() {
        try {
            conexao = DriverManager.getConnection("jdbc:mysql://localhost:3306/prova", "user", "root");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insert(User user) {
        String sql = """
            INSERT INTO users (
                full_name, address, phone, cpf, blood_type, rh_factor,
                course, emergency_contact, emergency_phone, height, weight, bmi
            ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
        """;

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, user.getFullName());
            stmt.setString(2, user.getAddress());
            stmt.setString(3, user.getPhone());
            stmt.setString(4, user.getCpf());
            stmt.setString(5, user.getBloodType());
            stmt.setString(6, user.getRhFactor());
            stmt.setString(7, user.getCourse());
            stmt.setString(8, user.getEmergencyContact());
            stmt.setString(9, user.getEmergencyPhone());
            stmt.setDouble(10, user.getHeight());
            stmt.setDouble(11, user.getWeight());
            stmt.setDouble(12, user.getBmi());
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Usuário inserido com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(int id, User user) {
        String sql = """
        UPDATE users SET 
            full_name=?, address=?, phone=?, cpf=?, blood_type=?, rh_factor=?,
            course=?, emergency_contact=?, emergency_phone=?, height=?, weight=?, bmi=?
        WHERE id=?
    """;

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, user.getFullName());
            stmt.setString(2, user.getAddress());
            stmt.setString(3, user.getPhone());
            stmt.setString(4, user.getCpf());
            stmt.setString(5, user.getBloodType());
            stmt.setString(6, user.getRhFactor());
            stmt.setString(7, user.getCourse());
            stmt.setString(8, user.getEmergencyContact());
            stmt.setString(9, user.getEmergencyPhone());
            stmt.setDouble(10, user.getHeight());
            stmt.setDouble(11, user.getWeight());
            stmt.setDouble(12, user.getBmi());
            stmt.setInt(13, id);

            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Usuário atualizado com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        try {
            PreparedStatement stmt = conexao.prepareStatement("DELETE FROM users WHERE id=?");
            stmt.setInt(1, id);
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Usuário removido com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String list() {
        StringBuilder result = new StringBuilder();
        try {
            Statement stmt = conexao.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM users ORDER BY id ASC");

            while (rs.next()) {
                result.append("ID: ").append(rs.getInt("id"))
                        .append(" | Nome: ").append(rs.getString("full_name"))
                        .append(" | Telefone: ").append(rs.getString("phone"))
                        .append(" | Curso: ").append(rs.getString("course"))
                        .append(" | IMC: ").append(rs.getFloat("bmi"))
                        .append("\n");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result.toString();
    }

    public UserReport report() {
        String sql = """
        SELECT 
            (SELECT MAX(weight) FROM users) AS max_weight,
            (SELECT full_name FROM users WHERE weight = (SELECT MAX(weight) FROM users) LIMIT 1) AS max_weight_name,
            (SELECT blood_type FROM users WHERE weight = (SELECT MAX(weight) FROM users) LIMIT 1) AS max_weight_blood,
            (SELECT rh_factor FROM users WHERE weight = (SELECT MAX(weight) FROM users) LIMIT 1) AS max_weight_rh_factor,

            (SELECT MIN(weight) FROM users) AS min_weight,
            (SELECT full_name FROM users WHERE weight = (SELECT MIN(weight) FROM users) LIMIT 1) AS min_weight_name,
            (SELECT blood_type FROM users WHERE weight = (SELECT MIN(weight) FROM users) LIMIT 1) AS min_weight_blood,
            (SELECT rh_factor FROM users WHERE weight = (SELECT MIN(weight) FROM users) LIMIT 1) AS min_weight_rh_factor,

            (SELECT AVG(weight) FROM users) AS avg_weight,

            (SELECT MAX(height) FROM users) AS max_height,
            (SELECT full_name FROM users WHERE height = (SELECT MAX(height) FROM users) LIMIT 1) AS max_height_name,
            (SELECT course FROM users WHERE height = (SELECT MAX(height) FROM users) LIMIT 1) AS max_height_course,

            (SELECT MIN(height) FROM users) AS min_height,
            (SELECT full_name FROM users WHERE height = (SELECT MIN(height) FROM users) LIMIT 1) AS min_height_name,
            (SELECT course FROM users WHERE height = (SELECT MIN(height) FROM users) LIMIT 1) AS min_height_course,

            (SELECT AVG(height) FROM users) AS avg_height,

            (SELECT AVG(bmi) FROM users) AS avg_bmi,

            (SELECT MAX(bmi) FROM users) AS max_bmi,
            (SELECT full_name FROM users WHERE bmi = (SELECT MAX(bmi) FROM users) LIMIT 1) AS max_bmi_name,

            (SELECT MIN(bmi) FROM users) AS min_bmi,
            (SELECT full_name FROM users WHERE bmi = (SELECT MIN(bmi) FROM users) LIMIT 1) AS min_bmi_name
        """;

        try (PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            if (rs.next()) {
                return new UserReport(
                        rs.getFloat("max_weight"),
                        rs.getString("max_weight_name"),
                        rs.getString("max_weight_blood"),
                        rs.getString("max_weight_rh_factor"),

                        rs.getFloat("min_weight"),
                        rs.getString("min_weight_name"),
                        rs.getString("min_weight_blood"),
                        rs.getString("min_weight_rh_factor"),

                        rs.getFloat("avg_weight"),

                        rs.getFloat("max_height"),
                        rs.getString("max_height_name"),
                        rs.getString("max_height_course"),

                        rs.getFloat("min_height"),
                        rs.getString("min_height_name"),
                        rs.getString("min_height_course"),

                        rs.getFloat("avg_height"),

                        rs.getFloat("avg_bmi"),
                        rs.getFloat("max_bmi"),
                        rs.getString("max_bmi_name"),
                        rs.getFloat("min_bmi"),
                        rs.getString("min_bmi_name")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

}
