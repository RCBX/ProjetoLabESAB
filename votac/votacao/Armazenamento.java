package votacao;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Armazenamento {
    private int voto;
    private int id;
    
    public Armazenamento(int voto, int id) {
        this.voto = voto;
        this.id = id;
    }
    
    
    public int getVoto() {
        return voto;
    }
    
    public void setVoto(int voto) {
        this.voto = voto;
    }
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public void armazenarVotoId() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("votos.txt", true))) {
            writer.write(voto + "," + id);
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Erro ao armazenar o voto e ID: " + e.getMessage());
        }
    }
}

