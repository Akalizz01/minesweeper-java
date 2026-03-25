/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MineSweeper;

/**
 *
 * @author tmsan
 */
public class Celula {
    private boolean aberta = false;
    private boolean mina = false;
    private boolean bandeira = false;
    private boolean bandeiraPermanente = false;
    private boolean explodida = false;
    private int minasAdjacentes = 0;
    private Energizador energizador;


    public void abrir() {
        this.aberta = true;
    }

    public boolean estaAberta() {
        return aberta;
    }

    public void colocarMina() {
        this.mina = true;
    }

    public boolean temMina() {
        return mina;
    }

    public void setExplodida(boolean valor) {
        this.explodida = valor;
    }
    
    public void setEnergizador(Energizador energizador) {
        this.energizador = energizador;
    }
    
    public Energizador getEnergizador() {
        return energizador;
    }


    public boolean estaExplodida() {
        return explodida;
    }

    public void setMinasAdjacentes(int n) {
        this.minasAdjacentes = n;
    }

    public int getMinasAdjacentes() {
        return minasAdjacentes;
    }

    public void colocarBandeira() {
        this.bandeira = true;
    }

    public void removerBandeira() {
        if (!bandeiraPermanente) this.bandeira = false;
    }

    public boolean temBandeira() {
        return bandeira;
    }

    public void colocarBandeiraPermanente() {
        this.bandeira = true;
        this.bandeiraPermanente = true;
    }


    @Override
    public String toString() {
        if (!aberta) {
            return bandeira ? "B" : "#";
        } else if (mina) {
            return explodida ? "*" : "*";
        } else {
            return minasAdjacentes == 0 ? "." : Integer.toString(minasAdjacentes);
        }
    }
    
    public String toString(boolean batota) {
        if (!aberta) {
            if (bandeira) 
                return "B";
            if (batota) {
                if (mina) return "*";
                if (energizador != null) {
                    String nome = energizador.getNome();
                    return nome.isEmpty() ? "?" : nome.substring(0,1).toUpperCase();
                }
            }
            return "#";
        }
        return toString();
    }

    
}

