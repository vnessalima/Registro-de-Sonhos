import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Sonho{
    private String data;
    private String tema;
    private String descricao;
    private String hora;
    private String emocoes;

    private static int quantidadeEntidades = 0;

    public static int getQuantidadeEntidades() {
        return quantidadeEntidades;
    }

    public static void incrementarQuantidadeEntidades() {
        quantidadeEntidades++;
    }

    public static int contarEntidadesNoCSV(String arquivoCSV){ //le arquivo csv e conta o nmr de entidades 
        int count = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(arquivoCSV))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().equals("Data:")) {
                    count++;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return count;
    }
    
    public Sonho(String data, String hora, String tema, String descricao, String emocoes){
        this.data = data;
        this.tema = tema;
        this.descricao = descricao;
        this.hora = hora;
        this.emocoes = emocoes;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getTema() {
        return tema;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getEmocoes() {
        return emocoes;
    }

    public void setEmocoes(String emocoes) {
        this.emocoes = emocoes;
    }

    @Override
    public String toString() {
        return "Sonho [Data=" + data + ", Tema=" + tema + ", Descrição=" + descricao + ", Horario/Turno= " + hora + ", Emoções= " + emocoes + "]";
    }
}