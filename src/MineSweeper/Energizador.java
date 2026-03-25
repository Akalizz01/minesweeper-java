/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MineSweeper;

/**
 *
 * @author tmsan
 */
public class Energizador {
    private String nome;

    public Energizador(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void usar(Jogo jogo, String argumento) {
    }

    @Override
    public String toString() {
        return nome;
    }
}


