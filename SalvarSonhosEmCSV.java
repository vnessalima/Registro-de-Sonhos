import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class SalvarSonhosEmCSV {
    public static void salvarSonhosEmCSV(List<Sonho> sonhos, String arquivoCSV) {
        try (FileWriter writer = new FileWriter(arquivoCSV, true);
             BufferedWriter bufferedWriter = new BufferedWriter(writer)) {
            Sonho sonho = sonhos.get(sonhos.size() - 1);
            bufferedWriter.write("Data: " + sonho.getData() + "\n");
            bufferedWriter.write("Tema: " + sonho.getTema() + "\n");
            bufferedWriter.write("Descrição: " + sonho.getDescricao() + "\n");
            bufferedWriter.write("Hora: " + sonho.getHora() + "\n");
            bufferedWriter.write("Emoções: " + sonho.getEmocoes() + "\n");
            bufferedWriter.write("\n");
            System.out.println("Sonho inserido com sucesso e salvo no arquivo!");
        } catch (IOException e) {
            System.out.println("Erro ao salvar o arquivo CSV: " + e.getMessage());
        }
    }
}