/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MineSweeper;

/**
 *
 * @author tmsan
 */
public class Cronometro {
    private long inicio;
    private long pausaTotal;
    private long pausaInicio;
    private boolean pausado;

    public void iniciar() {
        this.inicio = System.currentTimeMillis();
        this.pausaTotal = 0;
        this.pausado = false;
    }

    public void pausar() {
        if (!pausado) {
            this.pausaInicio = System.currentTimeMillis();
            pausado = true;
        }
    }

    public void continuar() {
        if (pausado) {
            pausaTotal += System.currentTimeMillis() - pausaInicio;
            pausado = false;
        }
    }

    public void parar() {
        if (pausado) continuar();
    }
    
    public long getSegundosDecorridos() {
        return getTempoDecorridoMillis() / 1000;
    }


    public String getTempoFormatado() {
        long tempo = getTempoDecorridoMillis();
        long segundos = tempo / 1000 % 60;
        long minutos = tempo / 1000 / 60 % 60;
        long horas = tempo / 1000 / 3600;
        return String.format("%02d:%02d:%02d", horas, minutos, segundos);
    }

    public String getTempoDecorrido() {
        return getTempoFormatado();
    }

    public long getTempoDecorridoMillis() {
        long agora = System.currentTimeMillis();
        if (pausado) {
            return (pausaInicio - inicio - pausaTotal);
        } else {
            return (agora - inicio - pausaTotal);
        }
    }
}

