/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MineSweeper;

/**
 *
 * @author tmsan
 */
import java.util.Scanner;

public class Jogo {
    private Jogador jogador;
    private Tabuleiro tabuleiro;
    private Cronometro cronometro;
    private HistoricoComandos historico;
    private HistoricoTop3 top3;
    private boolean jogoAtivo;
    private int escudosAtivos = 0;
    private int jogadasCongeladas = 0;
    private boolean modoBatota = false;


    public Jogo(Jogador jogador, Tabuleiro tabuleiro, HistoricoTop3 top3) {
        this.jogador = jogador;
        this.tabuleiro = tabuleiro;
        this.top3 = top3;
        this.cronometro = new Cronometro();
        this.historico = new HistoricoComandos();
        this.jogoAtivo = true;
        this.cronometro.iniciar();
    }

    public void iniciar(Scanner scanner) {
        while (jogoAtivo) {
            tabuleiro.mostrar(modoBatota);
            System.out.println("Tempo: " + cronometro.getSegundosDecorridos() + " Segundos");
            System.out.println("Bandeiras disponíveis: " + tabuleiro.getBandeirasDisponiveis());
            System.out.println("Inventário: " + jogador.getInventario());
            System.out.print("Comando>: ");
            String comando = scanner.nextLine();
            processarComando(comando);
        }
    }


    public void processarComando(String comando) {
        historico.adicionar(comando, cronometro.getTempoFormatado());


        if (comando.startsWith("/abrir")) {
            String[] partes = comando.split(" ");
            if (partes.length != 3) {
                System.out.println("Uso: /abrir <linha> <coluna>");
                return;
            }
            int linha = partes[1].toUpperCase().charAt(0) - 'A';
            int coluna = Integer.parseInt(partes[2]) - 1; 
            boolean perdeu = tabuleiro.abrirCelula(linha, coluna, escudosAtivos > 0, true, jogador);
            if (escudosAtivos > 0) escudosAtivos--;
            if (perdeu) {
                cronometro.parar();
                tabuleiro.revelarMinas();
                tabuleiro.mostrar(modoBatota);
                System.out.println("Perdeste o jogo!");
                jogoAtivo = false;
            } else if (tabuleiro.jogoGanho()) {
                cronometro.parar();
                System.out.println("Parabéns, ganhaste!");
                System.out.println("Tempo total: " + cronometro.getTempoFormatado());
                top3.adicionar(jogador.getNome(), cronometro.getTempoFormatado());
                jogoAtivo = false;
            }
        } else if (comando.startsWith("/usar")) {
            String[] partes = comando.split(" ");
            if (partes.length < 2) {
                System.out.println("Uso: /usar <Energizador> [argumento]");
                return;
            }
            String nomeEnergizador = partes[1];
            String argumento = partes.length >= 3 ? partes[2] : null;
            if (!jogador.getInventario().temEnergizador(nomeEnergizador)) {
                System.out.println("Não tens esse energizador no inventário.");
                return;
            }
            Energizador energizador = jogador.getInventario().obterEnergizador(nomeEnergizador);
            energizador.usar(this, argumento);
            jogador.getInventario().removerEnergizador(nomeEnergizador);
            System.out.println(nomeEnergizador + " usado com sucesso.");
        } else if (comando.startsWith("/bandeira")) {
            String[] partes = comando.split(" ");
            if (partes.length != 3) {
                System.out.println("Uso: /bandeira <linha> <coluna>");
                return;
            }
            int linha = partes[1].toUpperCase().charAt(0) - 'A';
            int coluna = Integer.parseInt(partes[2]) - 1;
            tabuleiro.trocarBandeira(linha, coluna);
            
            
            if (tabuleiro.jogoGanho()) {
                cronometro.parar();
                tabuleiro.mostrar(modoBatota); // opcional, para ver o tabuleiro final
                System.out.println("Parabéns, ganhaste!");
                System.out.println("Tempo total: " + cronometro.getTempoFormatado());
                top3.adicionar(jogador.getNome(), cronometro.getTempoFormatado());
                jogoAtivo = false;
            }
            
        } else if (comando.equals("/inventario")) {
            System.out.println(jogador.getInventario());
            
        } else if (comando.equals("/historico")) {
            historico.mostrar();
            
        } else if (comando.equals("/vitorias")) {
            top3.mostrarTop3();
            
        } else if (comando.equals("/batota")) {
            modoBatota = true;
            System.out.println("Modo batota ativado. Todas as minas e energizadores estao visiveis.");
            
        } else if (comando.equals("/ajuda")) {
            System.out.println("Comandos disponíveis: /abrir, /usar, /bandeira, /inventario, /historico, /vitorias, /ajuda, /sair");
            
        } else if (comando.equals("/sair")) {
            System.out.println("Tens a certeza que queres sair? (s/n)");
            Scanner scanner = new Scanner(System.in);
            String resposta = scanner.nextLine();
            if (resposta.equals("s")) {
                jogoAtivo = false;
                jogador.getInventario().limpar();
                System.out.println("Jogo terminado.Inventário Limpo");
            }
            
        } else {
            System.out.println("Comando inválido. Escreve /ajuda para ajuda.");
        }

        if (jogadasCongeladas > 0) {
            jogadasCongeladas--;
        } else {
            cronometro.continuar();
        }
    }

    public void incrementarEscudo() {
        escudosAtivos++;
    }

    public void congelarTempo() {
        jogadasCongeladas += 3;
        cronometro.pausar();
    }

    public void abrirLinha(String letra) {
        int linha = letra.toUpperCase().charAt(0) - 'A';
        tabuleiro.abrirLinha(linha, jogador);
    }


    public void abrirColuna(int coluna) {
        tabuleiro.abrirColuna(coluna, jogador);
    }


    public void sugerirCelula() {
        tabuleiro.sugerirJogada();
    }
}


