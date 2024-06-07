/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author mariahaeming
 */
public class Emprestimo extends visao.FrmRegistrarEmprestimo {
    private int id_emprestimo;
    private int dataE;
    
      public Emprestimo() {
        this(0, 0);
    }

    public Emprestimo(int id_emprestimo, int dataE) {
        this.id_emprestimo = id_emprestimo;
        this.dataE = dataE;
    }

    public int getId_emprestimo() {
        return id_emprestimo;
    }

    public void setId_emprestimo(int id_emprestimo) {
        this.id_emprestimo = id_emprestimo;
    }

    public int getDataE() {
        return dataE;
    }

    public void setDataE(int dataE) {
        this.dataE = dataE;
    }
    
}
