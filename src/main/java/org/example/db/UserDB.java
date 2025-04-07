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

    public void remover(int id) {
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

    public String listarUsuarioPorId(int id) {
        StringBuilder resultado = new StringBuilder();
        try {
            PreparedStatement stmt = conexao.prepareStatement("SELECT * FROM users WHERE id=?");
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                resultado.append("ID: ").append(rs.getInt("id"))
                        .append("\nNome: ").append(rs.getString("full_name"))
                        .append("\nEndereço: ").append(rs.getString("address"))
                        .append("\nTelefone: ").append(rs.getString("phone"))
                        .append("\nCPF: ").append(rs.getString("cpf"))
                        .append("\nTipo Sanguíneo: ").append(rs.getString("blood_type"))
                        .append(rs.getString("rh_factor"))
                        .append("\nCurso: ").append(rs.getString("course"))
                        .append("\nContato Emergência: ").append(rs.getString("emergency_contact"))
                        .append("\nTelefone Emergência: ").append(rs.getString("emergency_phone"))
                        .append("\nAltura: ").append(rs.getFloat("height"))
                        .append("\nPeso: ").append(rs.getFloat("weight"))
                        .append("\nIMC: ").append(rs.getFloat("bmi"))
                        .append("\n");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultado.toString();
    }
}
