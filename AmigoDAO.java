/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import model.Amigo;
import dao.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
/**
 *
 * @author samue
 */
public class AmigoDAO  {
    public void create(Amigo amigo) {
        String sql = "INSERT INTO amigos (nome, telefone) VALUES (?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, amigo.getNomeA());
            stmt.setInt(2, amigo.getTelefA());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Amigo read(int id) {
        String sql = "SELECT * FROM amigos WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Amigo(
                    rs.getString("nome"),
                    rs.getInt("telefone")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Amigo> readAll() {
        List<Amigo> amigos = new ArrayList<>();
        String sql = "SELECT * FROM amigos";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                amigos.add(new Amigo(
                    rs.getString("nome"),
                    rs.getInt("telefone")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return amigos;
    }

    public void update(Amigo amigo) {
        String sql = "UPDATE amigos SET nome = ?, telefone = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, amigo.getNomeA());
            stmt.setInt(2, amigo.getTelefA());
            stmt.setInt(3, amigo.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        String sql = "DELETE FROM amigos WHERE id = ?";
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
        AmigoDAO amigoDAO = new AmigoDAO();

        System.out.println("Digite o nome do amigo:");
        String nome = scanner.nextLine();

        System.out.println("Digite o telefone do amigo:");
        int telefone = Integer.parseInt(scanner.nextLine());

        Amigo amigo = new Amigo(nome, telefone);
        amigoDAO.create(amigo);

        scanner.close();
    }
}