package votacao;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Eleitor {
    private int titulo;
    
    public Eleitor(int titulo) {
        this.titulo = titulo;
    }
    
    public void vota() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite o número do candidato para presidente: ");
        int num = scanner.nextInt();
        armazenarVotosPresidente(num);
    }
    
    public int getTitulo() {
        return titulo;
    }
    
    private void armazenarVotosPresidente(int num) {
        String arquivo = "votos_presidente.txt";
        
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(arquivo, true))) {
            writer.write(Integer.toString(num));
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Ocorreu um erro ao armazenar o voto para o presidente.");
        }
    }
}

