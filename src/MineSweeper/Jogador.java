/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MineSweeper;

/**
 *
 * @author tmsan
 */
public class Jogador {
    private String nome;
    private Inventario inventario;

    public Jogador(String nome) {
        this.nome = nome;
        this.inventario = new Inventario();
        distribuirEnergizadoresIniciais();
    }

    public String getNome() {
        return nome;
    }

    public Inventario getInventario() {
        return inventario;
    }

    private void distribuirEnergizadoresIniciais() {
        String[] tipos = {"Escudo", "Gelo", "Linha", "Coluna", "Dica"};
        for (int i = 0; i < 3; i++) {
            int indice = (int) (Math.random() * tipos.length);
            inventario.adicionarEnergizador(criarEnergizador(tipos[indice]));
        }
    }
    
    public void receberEnergizadoresAdicionais() {
        distribuirEnergizadoresIniciais();
    }


    private Energizador criarEnergizador(String tipo) {
        switch (tipo) {
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
}

