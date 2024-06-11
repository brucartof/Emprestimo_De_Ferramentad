/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import model.RegistrarDevolucao;

/**
 *
 * @author samue
 */
public class RegistrarDevolucaoDAO {
     public void create(RegistrarDevolucao devolucao) {
        String sql = "INSERT INTO devolucoes (data_devolucao) VALUES (?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, devolucao.getDataD());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public RegistrarDevolucao read(int id) {
        String sql = "SELECT * FROM devolucoes WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new RegistrarDevolucao(
                    rs.getInt("data_devolucao")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<RegistrarDevolucao> readAll() {
        List<RegistrarDevolucao> devolucoes = new ArrayList<>();
        String sql = "SELECT * FROM devolucoes";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                devolucoes.add(new RegistrarDevolucao(
                    rs.getInt("data_devolucao")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return devolucoes;
    }

    public void update(RegistrarDevolucao devolucao) {
        String sql = "UPDATE devolucoes SET data_devolucao = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, devolucao.getDataD());
            stmt.setInt(2, devolucao.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        String sql = "DELETE FROM devolucoes WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        RegistrarDevolucaoDAO devolucaoDAO = new RegistrarDevolucaoDAO();

        System.out.println("Digite a data de devolução:");
        int dataDevolucao = Integer.parseInt(scanner.nextLine());

        RegistrarDevolucao devolucao = new RegistrarDevolucao(dataDevolucao);
        devolucaoDAO.create(devolucao);

        scanner.close();
    }
}