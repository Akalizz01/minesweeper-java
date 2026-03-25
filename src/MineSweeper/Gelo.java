/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MineSweeper;

/**
 *
 * @author tmsan
 */
public class Gelo extends Energizador {

    public Gelo() {
        super("Gelo");
    }

    @Override
    public void usar(Jogo jogo, String argumento) {
        jogo.congelarTempo();
    }
}

