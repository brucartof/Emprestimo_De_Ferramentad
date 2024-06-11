/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import model.Ferramenta;
import model.RelatoriodeFerramenta;

/**
 *
 * @author samue
 */
public class RelatoriodeFerramentaDAO {
   private List<RelatoriodeFerramenta> relatorios = new ArrayList<>();

    public void create(RelatoriodeFerramenta relatorio) {
        relatorios.add(relatorio);
    }

    public RelatoriodeFerramenta read(int id) {
        return relatorios.get(id);
    }

    public List<RelatoriodeFerramenta> readAll() {
        return new ArrayList<>(relatorios);
    }

    public void update(RelatoriodeFerramenta relatorio) {
        int index = relatorios.indexOf(relatorio);
        if (index >= 0) {
            relatorios.set(index, relatorio);
        }
    }

    public void delete(int id) {
        relatorios.remove(id);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        RelatoriodeFerramentaDAO relatorioDAO = new RelatoriodeFerramentaDAO();

        System.out.println("Digite o nome da ferramenta:");
        String nomeFerramenta = scanner.nextLine();

        System.out.println("Digite a marca da ferramenta:");
        String marcaFerramenta = scanner.nextLine();

        System.out.println("Digite o preço da ferramenta:");
        double precoFerramenta = Double.parseDouble(scanner.nextLine());

        Ferramenta ferramenta = new Ferramenta(nomeFerramenta, marcaFerramenta, precoFerramenta);

        RelatoriodeFerramenta relatorio = new RelatoriodeFerramenta();
        relatorio.adicionarFerramenta(ferramenta);

        relatorioDAO.create(relatorio);

        scanner.close();
    }
}


