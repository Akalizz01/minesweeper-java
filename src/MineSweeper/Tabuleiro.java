/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MineSweeper;

/**
 *
 * @author tmsan
 */
import java.util.Random;

public class Tabuleiro {
    private Celula[][] celulas;
    private int linhas;
    private int colunas;
    private int minasTotais;
    private int bandeirasDisponiveis;

    public Tabuleiro(int linhas, int colunas, int minas) {
        this.linhas = linhas;
        this.colunas = colunas;
        this.minasTotais = minas;
        this.bandeirasDisponiveis = minas;
        celulas = new Celula[linhas][colunas];
        inicializarCelulas();
        colocarMinas();
        calcularAdjacencias();
        colocarEnergizadores();
    }

    private void inicializarCelulas() {
        for (int i = 0; i < linhas; i++) {
            for (int j = 0; j < colunas; j++) {
                celulas[i][j] = new Celula();
            }
        }
    }

    private void colocarMinas() {
        Random random = new Random();
        int colocadas = 0;
        while (colocadas < minasTotais) {
            int i = random.nextInt(linhas);
            int j = random.nextInt(colunas);
            if (!celulas[i][j].temMina()) {
                celulas[i][j].colocarMina();
                colocadas++;
            }
        }
    }

    private void calcularAdjacencias() {
        for (int i = 0; i < linhas; i++) {
            for (int j = 0; j < colunas; j++) {
                if (!celulas[i][j].temMina()) {
                    int count = contarMinasAdjacentes(i, j);
                    celulas[i][j].setMinasAdjacentes(count);
                }
            }
        }
    }

    private int contarMinasAdjacentes(int x, int y) {
        int count = 0;
        for (int dx = -1; dx <= 1; dx++) {
            for (int dy = -1; dy <= 1; dy++) {
                int nx = x + dx;
                int ny = y + dy;
                if (nx >= 0 && ny >= 0 && nx < linhas && ny < colunas && celulas[nx][ny].temMina()) {
                    count++;
                }
            }
        }
        return count;
    }

    public boolean abrirCelula(int x, int y, boolean escudoAtivo, boolean aberturaDireta, Jogador jogador) {
        if (x < 0 || y < 0 || x >= linhas || y >= colunas || celulas[x][y].estaAberta() || celulas[x][y].temBandeira()) {
            return false;
        }

        Celula celula = celulas[x][y];
        celula.abrir();

        if (celula.temMina()) {
            if (escudoAtivo) {
                celula.colocarBandeiraPermanente();
                return false;
            } else {
                celula.setExplodida(true);
                return true;
            }
        }
        
        if (aberturaDireta && celula.getEnergizador() != null) {
            jogador.getInventario().adicionarEnergizador(celula.getEnergizador());
            System.out.println("Recolheste o energizador: " + celula.getEnergizador().getNome());
            celula.setEnergizador(null);
            return false;
        }
        
        if (celula.getMinasAdjacentes() == 0) {
            abrirAdjacentes(x, y, escudoAtivo, jogador);
        }

        return false;
    }



    private void abrirAdjacentes(int x, int y, boolean escudoAtivo, Jogador jogador) {
        for (int dx = -1; dx <= 1; dx++) {
            for (int dy = -1; dy <= 1; dy++) {
                int nx = x + dx;
                int ny = y + dy;
                if (nx >= 0 && ny >= 0 && nx < linhas && ny < colunas) {
                    Celula vizinha = celulas[nx][ny];
                    if (!vizinha.estaAberta() && !vizinha.temMina() && !vizinha.temBandeira()) {
                        abrirCelula(nx, ny, escudoAtivo, false,jogador);
                    }
                }
            }
        }
    }


    public void trocarBandeira(int x, int y) {
        Celula celula = celulas[x][y];

        if (celula.temBandeira()) {
            celula.removerBandeira();
            bandeirasDisponiveis++;
        } else if (!celula.estaAberta() && bandeirasDisponiveis > 0) {
            celula.colocarBandeira();
            bandeirasDisponiveis--;
        }
    }


    public void mostrar(boolean modoBatota) {
        System.out.print("   ");  
        for (int c = 1; c <= colunas; c++) {
            System.out.print(String.format("%3d", c)); 
        }
        System.out.println();

        for (int i = 0; i < linhas; i++) {
            System.out.printf("%-3s", (char) ('A' + i)); // Letra alinhada à esquerda
            for (int j = 0; j < colunas; j++) {
                String simbolo = celulas[i][j].toString(modoBatota);
                System.out.printf("%3s", simbolo);
            }
            System.out.println();
        }   
    }
    
    private void colocarEnergizadores() {
        String[] tipos = {"Escudo", "Gelo", "Linha", "Coluna", "Dica"};

        for (String tipo : tipos) {
            boolean colocado = false;
            while (!colocado) {
                int i = (int) (Math.random() * linhas);
                int j = (int) (Math.random() * colunas);
                Celula c = celulas[i][j];

                if (!c.temMina() && c.getEnergizador() == null) {
                    Energizador e = criarEnergizador(tipo);
                    c.setEnergizador(e);
                    colocado = true;
                }
            }
        }
    }
    
    private Energizador criarEnergizador(String tipo) {
        switch (tipo) {
            case "Escudo": return new Escudo();
            case "Gelo": return new Gelo();
            case "Linha": return new Linha();
            case "Coluna": return new Coluna();
            case "Dica": return new Dica();
            default: return null;
        }
    }




    public void revelarMinas() {
        for (int i = 0; i < linhas; i++) {
            for (int j = 0; j < colunas; j++) {
                if (celulas[i][j].temMina()) celulas[i][j].abrir();
            }
        }
    }

    public int getBandeirasDisponiveis() {
        return bandeirasDisponiveis;
    }

    public boolean jogoGanho() {
        boolean todasSemMinaAbertas = true;
        boolean todasMinasMarcadas = true;

        for (int i = 0; i < linhas; i++) {
            for (int j = 0; j < colunas; j++) {
                Celula c = celulas[i][j];

                // Verifica se há alguma célula sem mina não aberta
                if (!c.temMina() && !c.estaAberta()) {
                    todasSemMinaAbertas = false;
                }

                // Verifica se há alguma mina não marcada com bandeira
                if (c.temMina() && !c.temBandeira()) {
                    todasMinasMarcadas = false;
                }
            }
        }

        // Ganha se: todas sem mina abertas OU todas minas marcadas
        return todasSemMinaAbertas || todasMinasMarcadas;
    }



    public void abrirLinha(int linha, Jogador jogador) {
        for (int j = 0; j < colunas; j++) {
            Celula celula = celulas[linha][j];
            if (!celula.estaAberta()) {
                if (celula.temMina()) {
                    if (!celula.temBandeira()) {
                        celula.colocarBandeiraPermanente();
                        bandeirasDisponiveis--;
                    }
                } else {
                    celula.abrir(); // ← não ativa cascata
                    if (celula.getEnergizador() != null) {
                        jogador.getInventario().adicionarEnergizador(celula.getEnergizador());
                        System.out.println("Recolheste o energizador: " + celula.getEnergizador().getNome());
                        celula.setEnergizador(null);
                    }
                }
            }
        }
    }



    public void abrirColuna(int coluna, Jogador jogador) {
        for (int i = 0; i < linhas; i++) {
            Celula celula = celulas[i][coluna];

            if (!celula.estaAberta()) {
                if (celula.temMina()) {
                    if (!celula.temBandeira()) {
                        celula.colocarBandeiraPermanente();
                        bandeirasDisponiveis--;
                    }
                } else {
                    celula.abrir();

                    if (celula.getEnergizador() != null) {
                        jogador.getInventario().adicionarEnergizador(celula.getEnergizador());
                        System.out.println("Recolheste o energizador: " + celula.getEnergizador().getNome());
                        celula.setEnergizador(null);
                    }
                }
            }
        }
    }


    public void sugerirJogada() {
        for (int i = 0; i < linhas; i++) {
            for (int j = 0; j < colunas; j++) {
                if (!celulas[i][j].temMina() && !celulas[i][j].estaAberta()) {
                    System.out.println("Sugestão: " + (char) ('A' + i) + " " + j);
                    return;
                }
            }
        }
        System.out.println("Sem sugestões disponíveis.");
    }
}
