/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MineSweeper;

import java.util.ArrayList;
import java.util.List;

public class HistoricoComandos {
    private List<String> comandos;
    private List<String> tempos;

    public HistoricoComandos() {
        this.comandos = new ArrayList<>();
        this.tempos = new ArrayList<>();
    }

    public void adicionar(String comando,String tempoFormatado) {
        comandos.add(comando);
        tempos.add(tempoFormatado);
    }

    public void mostrar() {
        System.out.println("Histórico de comandos:");
        for (int i = 0; i < comandos.size(); i++) {
            System.out.println(comandos.get(i) + " \t " + tempos.get(i));
        }
    }

}

