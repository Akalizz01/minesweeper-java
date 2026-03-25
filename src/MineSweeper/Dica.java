/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MineSweeper;

/**
 *
 * @author tmsan
 */
public class Dica extends Energizador {
    
    public Dica() {
        super("Dica");
    }

    @Override
    public void usar(Jogo jogo, String argumento) {
        jogo.sugerirCelula();
    }
}


