/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MineSweeper;

import java.util.Map;

/**
 *
 * @author tmsan
 */
import java.util.HashMap;
import java.util.Map;

public class Inventario {
    private Map<String, Integer> energizadores;

    public Inventario() {
        this.energizadores = new HashMap<>();
    }

    public void adicionarEnergizador(Energizador energizador) {
        String nome = energizador.getNome();
        energizadores.put(nome, energizadores.getOrDefault(nome, 0) + 1);
    }

    public boolean temEnergizador(String nome) {
        return energizadores.getOrDefault(nome, 0) > 0;
    }

    public Energizador obterEnergizador(String nome) {
        switch (nome) {
            case "Escudo":
                return new Escudo();
            case "Gelo":
                return new Gelo();
            case "Linha":
                return new Linha();
            case "Coluna":
                return new Coluna();
            case "Dica":
                return new Dica();
            default:
                return null;
        }
    }
    
    public void limpar() {
        energizadores.clear();
    }


    public void removerEnergizador(String nome) {
        if (temEnergizador(nome)) {
            energizadores.put(nome, energizadores.get(nome) - 1);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (String nome : energizadores.keySet()) {
            sb.append(nome).append(": ").append(energizadores.get(nome)).append("  ");
        }
        return sb.toString();
    }
}

