package votacao;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Mesario {
    public String nome;
    public int id;
    Eleitor eleitor;
    Armazenamento armazenamento;

    public static boolean libera(int cod, int titulo) {
        if (cod == 456879) {
            boolean tituloJaVotou = tituloJaVotou(titulo);
            if (!tituloJaVotou) {
                Eleitor eleitor = new Eleitor(titulo);
                eleitor.vota();
                return true;
            } else {
                System.out.println("Título de eleitor já votou. Votação não liberada.");
            }
        } else {
            System.out.println("Código do mesário inválido! Votação não liberada.");
        }
        return false;
    }

    public static boolean tituloJaVotou(int titulo) {
        try (BufferedReader reader = new BufferedReader(new FileReader("votos.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    int voto = Integer.parseInt(parts[0].trim());
                    int tituloVotado = Integer.parseInt(parts[1].trim());
                    if (tituloVotado == titulo) {
                        return true;
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("");
        }
        return false;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Armazenamento armazenamento = new Armazenamento(0, 0); // Armazenamento inicializado

        boolean continuar = true;

        while (continuar) {
            System.out.println("O que deseja fazer?");
            System.out.println("1 - Liberar votação");
            System.out.println("2 - Ver quem já votou");
            System.out.println("3 - Abrir arquivo de votos");
            System.out.println("0 - Encerrar programa");
            System.out.print("Opção: ");
            int opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    System.out.print("Digite o código do mesário: ");
                    int cod = scanner.nextInt();
                    System.out.print("Digite o título do eleitor: ");
                    int titulo = scanner.nextInt();

                    boolean liberado = Mesario.libera(cod, titulo);

                    if (liberado) {
                        System.out.println("Votação liberada!");
                        armazenamento.setVoto(armazenamento.getVoto() + 1);
                        armazenamento.setId(titulo);
                        armazenamento.armazenarVotoId();
                    }

                    break;
                case 2:
                    System.out.println("Quem já votou:");
                    System.out.println("Votos: " + armazenamento.getVoto());
                    System.out.println("ID do último eleitor: " + armazenamento.getId());
                    break;
                case 3:
                    System.out.print("Digite o código de liberação para abrir o arquivo: ");
                    int codigo = scanner.nextInt();
                    if (codigo == 456879) {
                        System.out.println("Abrindo o arquivo de votos...");
                        try (BufferedReader reader = new BufferedReader(new FileReader("votos.txt"))) {
                            String line;
                            while ((line = reader.readLine()) != null) {
                                System.out.println(line);
                            }
                        } catch (IOException e) {
                            System.out.println("");
                        }
                    } else {
                        System.out.println("Código de liberação incorreto! Acesso negado.");
                    }
                    break;
                case 0:
                    continuar = false;
                    break;
                default:
                    System.out.println("Opção inválida!");
                    break;
            }

            System.out.println();
        }

        System.out.println("Encerrando o programa...");
        scanner.close();
    }
}

