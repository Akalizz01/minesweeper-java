/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MineSweeper;

/**
 *
 * @author tmsan
 */
public class Escudo extends Energizador {

    public Escudo() {
        super("Escudo");
    }

    @Override
    public void usar(Jogo jogo, String argumento) {
        jogo.incrementarEscudo();
    }
}

