/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MineSweeper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author tmsan
 */
public class HistoricoTop3 {
    private List<ResultadoVitoria> vitorias;

    public HistoricoTop3() {
        vitorias = new ArrayList<>();
    }

    public void adicionar(String nome, String tempo) {
        vitorias.add(new ResultadoVitoria(nome, tempo));
        ordenarVitorias();
        if (vitorias.size() > 3) vitorias.remove(3);
    }

    private void ordenarVitorias() {
        Collections.sort(vitorias, Comparator.comparing(ResultadoVitoria::getTempoTotal));
    }

    public void mostrarTop3() {
        System.out.println("Top 3 vitórias:");
        for (ResultadoVitoria entrada : vitorias) {
            System.out.println(entrada);
        }
    }
}
