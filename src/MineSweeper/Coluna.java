/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MineSweeper;

/**
 *
 * @author tmsan
 */
public class Coluna extends Energizador {
    
    public Coluna() {
        super("Coluna");
    }

    @Override
    public void usar(Jogo jogo, String argumento) {
        try {
            int coluna = Integer.parseInt(argumento);
            jogo.abrirColuna(coluna);
        } catch (Exception e) {
            System.out.println("Uso correto: /usar Coluna <número da coluna>");
        }
    }
}


