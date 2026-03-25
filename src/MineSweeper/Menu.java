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
import java.util.HashMap;
import java.util.Map;


public class Menu {
    private static int anonimoContador = 1;
    private HistoricoTop3 top3 = new HistoricoTop3();
    private Map<String, Jogador> jogadores = new HashMap<>();


    public void executarMenu() {
        Scanner scanner = new Scanner(System.in);
        boolean sair = false;

        while (!sair) {
            System.out.println("MineSweeper");
            System.out.println("-----------------");
            System.out.println("1 – Novo Jogo");
            System.out.println("2 – Top 3");
            System.out.println("3 – Sair da aplicacao");
            System.out.print("Opcao>: ");
            String opcao = scanner.nextLine();

            switch (opcao) {
                case "1":
                    Jogador jogador = criarJogador(scanner);
                    int[] parametros = escolherDificuldade(scanner);
                    int linhas = parametros[0];
                    int colunas = parametros[1];
                    int minas = parametros[2];

                    Tabuleiro tabuleiro = new Tabuleiro(linhas, colunas, minas);
                    Jogo jogo = new Jogo(jogador, tabuleiro, top3);
                    jogo.iniciar(scanner);
                    break;
                case "2":
                    top3.mostrarTop3();
                    break;
                case "3":
                    System.out.println("Tens a certeza que queres sair? (s/n)");
                    String resposta = scanner.nextLine();
                    if (resposta.equals("s")) {
                        sair = true;
                    }
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        }

        System.out.println("A sair da aplicacao");
    }

    private Jogador criarJogador(Scanner scanner) {
        System.out.print("Nickname (enter para Anónimo): ");
        String nickname = scanner.nextLine().trim();
        if (nickname.isEmpty()) {
            nickname = "Anónimo " + anonimoContador++;
            Jogador novo = new Jogador(nickname);
            jogadores.put(nickname, novo);
            System.out.println("Bem-vindo, " + nickname + "!");
            return novo;
        }
        Jogador jogadorExistente = jogadores.get(nickname);
        if (jogadorExistente != null) {
            System.out.println("Bem-vindo de volta, " + nickname + "!");
            jogadorExistente.receberEnergizadoresAdicionais();
            return jogadorExistente;
        } else {
            Jogador novo = new Jogador(nickname);
            jogadores.put(nickname, novo);
            System.out.println("Bem-vindo, " + nickname + "!");
            return novo;
        }
    }

    private int[] escolherDificuldade(Scanner scanner) {
        System.out.println("Escolhe o nível de dificuldade:");
        System.out.println("1 – Inicial (9x9, 10 minas)");
        System.out.println("2 – Médio (10x10, 16 minas)");
        System.out.println("3 – Profissional (12x12, 24 minas)");
        System.out.print("Opção>: ");

        String opcao = scanner.nextLine();
        switch (opcao) {
            case "1":
                return new int[]{9, 9, 10};
            case "2":
                return new int[]{10, 10, 16};
            case "3":
                return new int[]{12, 12, 24};
            default:
                System.out.println("Opção inválida. Será usado o nível Inicial por defeito.");
                return new int[]{9, 9, 10};
        }
    }
}

