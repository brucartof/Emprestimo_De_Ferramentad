/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import model.Ferramenta;
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
public class FerramentaDAO {
 public void create(Ferramenta ferramenta) {
        String sql = "INSERT INTO ferramentas (nome, marca, preco) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, ferramenta.getNomeF());
            stmt.setString(2, ferramenta.getMarcaF());
            stmt.setDouble(3, ferramenta.getPrecoF());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Ferramenta read(int id) {
        String sql = "SELECT * FROM ferramentas WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Ferramenta(
                    rs.getString("nome"),
                    rs.getString("marca"),
                    rs.getDouble("preco")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Ferramenta> readAll() {
        List<Ferramenta> ferramentas = new ArrayList<>();
        String sql = "SELECT * FROM ferramentas";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                ferramentas.add(new Ferramenta(
                    rs.getString("nome"),
                    rs.getString("marca"),
                    rs.getDouble("preco")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ferramentas;
    }

    public void update(Ferramenta ferramenta) {
        String sql = "UPDATE ferramentas SET nome = ?, marca = ?, preco = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, ferramenta.getNomeF());
            stmt.setString(2, ferramenta.getMarcaF());
            stmt.setDouble(3, ferramenta.getPrecoF());
            stmt.setInt(4, ferramenta.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        String sql = "DELETE FROM ferramentas WHERE id = ?";
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
        FerramentaDAO ferramentaDAO = new FerramentaDAO();

        System.out.println("Digite o nome da ferramenta:");
        String nome = scanner.nextLine();

        System.out.println("Digite a marca da ferramenta:");
        String marca = scanner.nextLine();

        System.out.println("Digite o preço da ferramenta:");
        double preco = Double.parseDouble(scanner.nextLine());

        Ferramenta ferramenta = new Ferramenta(nome, marca, preco);
        ferramentaDAO.create(ferramenta);

        scanner.close();
    }
}

