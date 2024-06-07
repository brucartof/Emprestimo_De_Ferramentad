/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author mariahaeming
 */
public class Ferramenta extends visao.FrmCadastrarFerramentas{

    private String NomeF;
    private String MarcaF;
    private double PrecoF;

    public Ferramenta() {
        this("", "", 0);
    }

    public Ferramenta(String NomeF, String MarcaF, double PrecoF) {
        this.NomeF = NomeF;
        this.MarcaF = MarcaF;
        this.PrecoF = PrecoF;
    }

    public String getNomeF() {
        return NomeF;
    }

    public void setNomeF(String NomeF) {
        this.NomeF = NomeF;
    }

    public String getMarcaF() {
        return MarcaF;
    }

    public void setMarcaF(String MarcaF) {
        this.MarcaF = MarcaF;
    }

    public double getPrecoF() {
        return PrecoF;
    }

    public void setPrecoF(double PrecoF) {
        this.PrecoF = PrecoF;
    }
}
