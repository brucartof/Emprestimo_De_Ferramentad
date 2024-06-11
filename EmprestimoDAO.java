/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import model.Emprestimo;
import model.Ferramenta;
import dao.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;

/**
 *
 * @author samue
 */
public class EmprestimoDAO  {
 public void create(Emprestimo emprestimo) {
        String sql = "INSERT INTO emprestimos (ferramenta_id, nome_amigo, data_emprestimo, data_devolucao) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, emprestimo.getFerramenta().getId());
            stmt.setString(2, emprestimo.getNomeAmigo());
            stmt.setLong(3, emprestimo.getDataEmprestimo());
            if (emprestimo.getDataDevolucao() != null) {
                stmt.setLong(4, emprestimo.getDataDevolucao());
            } else {
                stmt.setNull(4, Types.BIGINT);
            }
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Emprestimo read(int id) {
        String sql = "SELECT * FROM emprestimos WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                FerramentaDAO ferramentaDAO = new FerramentaDAO();
                Ferramenta ferramenta = ferramentaDAO.read(rs.getInt("ferramenta_id"));
                return new Emprestimo(
                    ferramenta,
                    rs.getString("nome_amigo"),
                    rs.getLong("data_emprestimo"),
                    rs.getLong("data_devolucao")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Emprestimo> readAll() {
        List<Emprestimo> emprestimos = new ArrayList<>();
        String sql = "SELECT * FROM emprestimos";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                FerramentaDAO ferramentaDAO = new FerramentaDAO();
                Ferramenta ferramenta = ferramentaDAO.read(rs.getInt("ferramenta_id"));
                emprestimos.add(new Emprestimo(
                    ferramenta,
                    rs.getString("nome_amigo"),
                    rs.getLong("data_emprestimo"),
                    rs.getLong("data_devolucao")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return emprestimos;
    }

    public void update(Emprestimo emprestimo) {
        String sql = "UPDATE emprestimos SET ferramenta_id = ?, nome_amigo = ?, data_emprestimo = ?, data_devolucao = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, emprestimo.getFerramenta().getId());
            stmt.setString(2, emprestimo.getNomeAmigo());
            stmt.setLong(3, emprestimo.getDataEmprestimo());
            if (emprestimo.getDataDevolucao() != null) {
                stmt.setLong(4, emprestimo.getDataDevolucao());
            } else {
                stmt.setNull(4, Types.BIGINT);
            }
            stmt.setInt(5, emprestimo.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        String sql = "DELETE FROM emprestimos WHERE id = ?";
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
        EmprestimoDAO emprestimoDAO = new EmprestimoDAO();

        System.out.println("Digite o nome da ferramenta:");
        String nomeFerramenta = scanner.nextLine();

        System.out.println("Digite a marca da ferramenta:");
        String marcaFerramenta = scanner.nextLine();

        System.out.println("Digite o preço da ferramenta:");
        double precoFerramenta = Double.parseDouble(scanner.nextLine());

        Ferramenta ferramenta = new Ferramenta(nomeFerramenta, marcaFerramenta, precoFerramenta);
        FerramentaDAO ferramentaDAO = new FerramentaDAO();
        ferramentaDAO.create(ferramenta);

        System.out.println("Digite o nome do amigo:");
        String nomeAmigo = scanner.nextLine();

        System.out.println("Digite a data do empréstimo:");
        long dataEmprestimo = Long.parseLong(scanner.nextLine());

        Emprestimo emprestimo = new Emprestimo(ferramenta, nomeAmigo, dataEmprestimo);
        emprestimoDAO.create(emprestimo);

        scanner.close();
    }
}