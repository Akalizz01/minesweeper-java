/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MineSweeper;

/**
 *
 * @author tmsan
 */
public class Linha extends Energizador {
    public Linha() {
        super("Linha");
    }

    @Override
    public void usar(Jogo jogo, String argumento) {
        if (argumento != null && argumento.length() == 1) {
            jogo.abrirLinha(argumento);
        } else {
            System.out.println("Uso correto: /usar Linha <letra da linha>");
        }
    }
}


