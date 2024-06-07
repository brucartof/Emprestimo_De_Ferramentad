/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;


/**
 *
 * @author mariahaeming
 */
public class Amigo extends visao.FrmCadastrarAmigo {

    private String NomeA;
    private int TelefA;

    public Amigo() {
        this("", 0);
    }

    public Amigo(String NomeA, int TelefA) {
        this.NomeA = NomeA;
        this.TelefA = TelefA;
    }

    public String getNomeA() {
        return NomeA;
    }

    public void setNomeA(String NomeA) {
        this.NomeA = NomeA;
    }

    public int getTelefA() {
        return TelefA;
    }

    public void setTelefA(int TelefA) {
        this.TelefA = TelefA;
    }
}
