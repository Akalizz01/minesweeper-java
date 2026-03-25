/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MineSweeper;

/**
 *
 * @author tmsan
 */
public class ResultadoVitoria {
    private String nome;
    private String tempo;

    public ResultadoVitoria(String nome, String tempo) {
        this.nome = nome;
        this.tempo = tempo;
    }

    public long getTempoTotal() {
        try {
            String[] partes = tempo.split(":");
            int horas = Integer.parseInt(partes[0]);
            int minutos = Integer.parseInt(partes[1]);
            int segundos = Integer.parseInt(partes[2]);
            return horas * 3600 + minutos * 60 + segundos;
        } catch (Exception e) {
            return Long.MAX_VALUE; // Em caso de erro, envia para o fim da lista
        }
    }

    @Override
    public String toString() {
        return nome + " - " + tempo;
    }

    public String getNome() {
        return nome;
    }

    public String getTempo() {
        return tempo;
    }
}
